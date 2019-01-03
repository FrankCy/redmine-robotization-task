package com.redmine.rz.impl;

import com.redmine.rz.bean.*;
import com.redmine.rz.issue.RedmineIssueManager;
import com.redmine.rz.service.RedmineService;
import com.taskadapter.redmineapi.RedmineException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
                logger.info("用户任务信息为"+ userIssue.toString());
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

}
