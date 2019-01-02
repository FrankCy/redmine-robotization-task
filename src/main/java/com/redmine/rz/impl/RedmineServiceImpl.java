package com.redmine.rz.impl;

import com.redmine.rz.bean.IssueBean;
import com.redmine.rz.controller.RedmineController;
import com.redmine.rz.issue.RedmineIssueManager;
import com.redmine.rz.service.RedmineService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

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
    public boolean createIssues(List<IssueBean> issueBeanList) {
        RedmineIssueManager redmineIssueManager = new RedmineIssueManager();
        try {
            if(issueBeanList.size() > 0) {
                for(IssueBean issueBean : issueBeanList) {
                    //查询是否添加过此任务
                    if(redmineIssueManager.getIssueTitle(issueBean.getTitle(), issueBean.getAssigneeId())) {
                        redmineIssueManager.createIssueThings(issueBean);
                    } else {
                        logger.info("任务-" + issueBean.getTitle() + "重复，无法进行添加!");
                    }
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
