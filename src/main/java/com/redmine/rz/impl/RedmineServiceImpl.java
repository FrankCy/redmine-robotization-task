package com.redmine.rz.impl;

import com.redmine.rz.bean.IssueBean;
import com.redmine.rz.issue.RedmineIssueManager;
import com.redmine.rz.service.RedmineService;
import org.springframework.beans.factory.annotation.Autowired;

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
public class RedmineServiceImpl implements RedmineService {

    @Autowired
    RedmineIssueManager redmineIssueManager;

    @Override
    public boolean uploadIssue() {

        IssueBean issueBean = new IssueBean();
        //项目名称
        issueBean.setProName("first_pro");
        //demand创建需求（标题）
        issueBean.setTitle("[V3.3.1-D]创建用户");
        //描述信息
        issueBean.setDescription("1.创建用户表\n 2.新增创建用户表单\n 3.完成用户创建功能");

        try {
            boolean uploadFlag =redmineIssueManager.createIssueThings(issueBean.getTitle(), issueBean.getDescription());
            return uploadFlag;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
