package com.redmine.rz.task;

import com.taskadapter.redmineapi.IssueManager;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.IssueFactory;

import java.util.List;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: redmine-robotization-task
 * @package: com.redmine.rz.task、
 * @email: cy880708@163.com
 * @date: 2018/12/19 下午1:24
 * @mofified By:
 */
public class TaskManager {

    private static Object issues;

    public static void main(String[] args) throws Exception {
        //redmine本地地址
        String uri = "http://192.168.10.110:3000";
        //redmine apiAccessKey
        String apiAccessKey = "79657afb1f5389c0a05b54f4c98ada401580aa93";
        //目标项目
        String projectKey = "first_pro";
        // any
        Integer queryId = null;

        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
        //获取问题管理对象
        IssueManager issueManager = mgr.getIssueManager();
        //获取所有问题
        List<Issue> issues = issueManager.getIssues(projectKey, queryId);
        //迭代输出问题
        for (Issue issue : issues) {
            System.out.println(issue.getDescription());
            System.out.println(issue.toString());
        }

        //循环创建
        for(int i=10; i<30; i++) {
            Issue issue = new Issue();
            issue.setProjectId(i+1);
            issue.setDescription("描述信息：" + i);
            issue.setSubject("错误");
            Issue createdIssue = issueManager.createIssue(issue);
        }

        //通过ID获取问题（这里获取ID=11的问题）
        Issue issue = issueManager.getIssueById(11);
        System.out.println(issue.getDescription());
    }

}