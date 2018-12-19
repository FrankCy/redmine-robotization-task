package com.redmine.rz.task;

import com.taskadapter.redmineapi.IssueManager;
import com.taskadapter.redmineapi.RedmineManager;
import com.taskadapter.redmineapi.RedmineManagerFactory;
import com.taskadapter.redmineapi.bean.Issue;
import com.taskadapter.redmineapi.bean.IssueFactory;
import com.taskadapter.redmineapi.bean.Tracker;
import com.taskadapter.redmineapi.bean.TrackerFactory;

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

        //设置跟踪 1：问题；2：功能
        Tracker tracker = TrackerFactory.create(2);
        for(int i=1; i<10; i++) {
            Issue issueToCreate = IssueFactory.create(1, "some subject");
            issueToCreate.setStatusId(5);
            issueToCreate.setTracker(tracker);
            issueToCreate.setAuthorId(1);
            issueToCreate.setAssigneeId(9);
            issueManager.createIssue(issueToCreate);
        }

        //通过ID获取问题（这里获取ID=11的问题）
//        Issue issue = issueManager.getIssueById(11);
//        System.out.println(issue.getDescription());
    }

}
