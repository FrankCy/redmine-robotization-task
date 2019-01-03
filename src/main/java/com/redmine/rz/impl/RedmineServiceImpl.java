package com.redmine.rz.impl;

import com.redmine.rz.bean.IssueBean;
import com.redmine.rz.bean.ServiceResult;
import com.redmine.rz.controller.RedmineController;
import com.redmine.rz.issue.RedmineIssueManager;
import com.redmine.rz.service.RedmineService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
                    if(redmineIssueManager.getIssueTitle(issueBean.getTitle(), issueBean.getAssigneeId())) {
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
}
