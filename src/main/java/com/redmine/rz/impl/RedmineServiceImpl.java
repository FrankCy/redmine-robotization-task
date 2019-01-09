package com.redmine.rz.impl;

import com.redmine.rz.bean.*;
import com.redmine.rz.issue.RedmineIssueManager;
import com.redmine.rz.service.RedmineService;
import com.taskadapter.redmineapi.RedmineException;
import com.xiaoleilu.hutool.date.DateTime;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: redmine-robotization-task
 * @package: com.redmine.rz.impl、
 * @email: cy880708@163.com
 * @date: 2018/12/28 上午10:57
 * @mofified By:
 */
@Service("redmineServiceImpl")
public class RedmineServiceImpl implements RedmineService {

    /**
     *打印日志
     */
    private static final Log logger = LogFactory.getLog(RedmineServiceImpl.class);

    @Override
    public ServiceResult createIssues(List<IssueBean> issueBeanList) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setResCode("400");
        RedmineIssueManager redmineIssueManager = new RedmineIssueManager();
        try {
            if(issueBeanList.size() > 0) {
                List duplicateIssueList = new ArrayList();
                for(IssueBean issueBean : issueBeanList) {
                    //查询是否添加过此任务
                    if(redmineIssueManager.getIssueForTitle(issueBean.getTitle(), issueBean.getAssigneeId())) {
                        redmineIssueManager.createIssueThings(issueBean);
                    } else {
                        String title = issueBean.getTitle();
                        logger.info("任务-" + title + "重复，无法进行添加!");
                        duplicateIssueList.add(title);
                    }
                }
                serviceResult.setResList(duplicateIssueList);
                serviceResult.setResCode("200");
                return serviceResult;
            }
            return serviceResult;
        } catch (Exception e) {
            e.printStackTrace();
            return serviceResult;
        }
    }

    @Override
    public int getIssuesCountByPro(String prokey){
        RedmineIssueManager redmineIssueManager = new RedmineIssueManager();
        int count = 0;
        try {
            count = redmineIssueManager.getIssuesCountByPro(prokey);
            logger.info("项目[" + prokey + "]任务总数为[" + count + "]个");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public int getIssuesCountByUserId(int userId) {
        RedmineIssueManager redmineIssueManager = new RedmineIssueManager();
        int count = 0;
        try {
            count = redmineIssueManager.getIssuesCountByUserId(userId);
            logger.info("用户[" + userId + "]任务总数为[" + count + "]个");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<UserIssue> getUsersIssue() {
        RedmineIssueManager redmineIssueManager = new RedmineIssueManager();
        List<UserIssue> issues = new ArrayList<>();
        try {
            //获取用户列表
            List<RedmineUser> redmineUsers = redmineIssueManager.getUsers();

            //根据用户，获取他的任务
            for(RedmineUser user : redmineUsers) {
                UserIssue userIssue = new UserIssue();
                userIssue.setUserIssues(redmineIssueManager.getIssuesByUserId(user.getUserId()));
                userIssue.setUserId(user.getUserId());
                userIssue.setUserName(user.getUserName());
                issues.add(userIssue);
            }

        } catch (RedmineException e) {
            e.printStackTrace();
        }
        return issues;
    }

    @Override
    public List<ProjectBean> getRedmineProjects() {
        RedmineIssueManager redmineIssueManager = new RedmineIssueManager();
        List<ProjectBean> list = new ArrayList<>();
        try {
            list = redmineIssueManager.getRedmineProjects();
            logger.info("平台项目一共有[" + list.size() + "]个");
        } catch (RedmineException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<LineChart> getRedmineIssueJson() throws Exception {

        RedmineIssueManager redmineIssueManager = new RedmineIssueManager();
        List<ProjectBean> projectBeans = redmineIssueManager.getRedmineProjects();
        List<LineChart> lineCharts = new ArrayList<>();
        for(ProjectBean pb : projectBeans) {
            LineChart lineChart = new LineChart();
            List<IssueBean> issueBeans = redmineIssueManager.getIssues(pb.getIdentifier());
            Map<Long, Integer> map = new HashMap();

            //将任务迭代并封装成燃尽图数据源
            for(IssueBean issueBean : issueBeans) {
                Date beanStartDate = issueBean.getStartDate();
                String sd = new SimpleDateFormat("yyyyMMdd").format(beanStartDate);
                Date startDate = new SimpleDateFormat("yyyyMMdd").parse(sd);
                //定义日期实例
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startDate);
                DateTime dateTime = new DateTime(calendar.getTime());
                Long time = dateTime.getTime();
                if(map.containsKey(time)) {
                    map.put(time, Integer.valueOf(map.get(time).toString()) + 1);
                } else {
                    map.put(time, 1);
                }
            }

            //迭代Map，并转成List
            List list = new ArrayList();
            for (Long key : map.keySet()) {
                List infoList = new ArrayList();
                infoList.add(key);
                infoList.add(map.get(key));
                list.add(infoList);
            }
            lineChart.setProName(pb.getProName());
            lineChart.setProData(list.toString());
            lineCharts.add(lineChart);
        }

        return lineCharts;
    }

    @Override
    public String getIssueProportion(String proName) throws Exception {

        RedmineIssueManager redmineIssueManager = new RedmineIssueManager();

        //获取任务总数
        int issueCount = redmineIssueManager.getIssuesCountByPro(proName);

        //获取所有任务
        List<IssueBean> issueBeans = redmineIssueManager.getIssues(proName);

        //获取用户信息
        Map userInfoMap = redmineIssueManager.getUsersInfo();

        //声明Map
        Map<Integer, BigDecimal> map = new HashMap();
        for(IssueBean issueBean : issueBeans) {
            int assigneeId = issueBean.getAssigneeId();
            if(map.containsKey(assigneeId)) {
                map.put(assigneeId, new BigDecimal(map.get(assigneeId).toString()).add(new BigDecimal(1)));
            } else {
                map.put(assigneeId, new BigDecimal(1));
            }
        }

        //计算百分比，并返回String
        List list = new ArrayList();
        for (int key : map.keySet()) {
            List infoList = new ArrayList();
            infoList.add("\"" + userInfoMap.get(key) + "\"");
            //除法并保留2位消数，四舍五入
            infoList.add(map.get(key).divide(new BigDecimal(issueCount), 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)));
            list.add(infoList);
        }
        return list.toString();
    }

}
