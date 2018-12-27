package com.redmine.rz.issue;

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
 * @package: com.redmine.rz.issue、
 * @email: cy880708@163.com
 * @date: 2018/12/24 上午9:50
 * @mofified By:
 */
public class RedmineIssueManager {

    /**
     * redmine本地地址
     */
    private static String uri = "http://192.168.10.110:3000";

    /**
     * redmine apiAccessKey
     */
    private static String apiAccessKey = "79657afb1f5389c0a05b54f4c98ada401580aa93";

    /**
     * @description：创建问题
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2018/12/24 上午9:55
     * @mofified By:
     */
    public static boolean createIssueBug(String proName, String title, String description) throws Exception {

        // Redmine管理器
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);

        // 获取问题管理对象
        IssueManager issueManager = mgr.getIssueManager();

        // 设置跟踪 1：问题；2：功能
        Tracker tracker = TrackerFactory.create(1);

        //创建任务，并赋标题
        Issue issueToCreate = IssueFactory.create(1, isEmpty(title));
        //设置状态新建
        issueToCreate.setStatusId(1);
        //设置跟踪
        issueToCreate.setTracker(tracker);
        //设置发起人
        issueToCreate.setAuthorId(1);
        //设置指派人
        issueToCreate.setAssigneeId(9);
        //设置问题描述
        issueToCreate.setDescription(isEmpty(description));
        //创建任务
        issueManager.createIssue(issueToCreate);

        return true;
    }


    /**
     * @description：创建问题
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2018/12/24 上午9:55
     * @mofified By:
     */
    public static boolean createIssueThings(String proName, String title, String description) throws Exception {

        // Redmine管理器
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);

        // 获取问题管理对象
        IssueManager issueManager = mgr.getIssueManager();

        // 设置跟踪 1：问题；2：功能
        Tracker tracker = TrackerFactory.create(2);

        Issue issueToCreate = IssueFactory.create(1, isEmpty(title));
        issueToCreate.setStatusId(1);
        issueToCreate.setTracker(tracker);
        issueToCreate.setAuthorId(1);
        issueToCreate.setAssigneeId(9);
        issueToCreate.setDescription(description);
        issueManager.createIssue(issueToCreate);

        return true;
    }

    /**
     * @description：查询某项目下所有问题
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2018/12/24 上午9:55
     * @mofified By:
     */
    public static List<Issue> getIssues(String proName) throws Exception {

        // 声明Redmine管理器
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);

        // 获取问题管理对象
        IssueManager issueManager = mgr.getIssueManager();

        // 目标项目
        String projectKey = validateProName(proName);

        // 获取所有问题
        List<Issue> issues = issueManager.getIssues(projectKey, null);

        // 迭代输出问题
        for (Issue issue : issues) {
            System.out.println(issue.getDescription());
            System.out.println(issue.toString());
        }

        return issues.size() > 0 ? issues : null;

    }

    /**
     * @description：查询某项目下指定问题
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2018/12/24 上午9:55
     * @mofified By:
     */
    public static Issue getIssue(int issueId) throws Exception {

        // 声明Redmine管理器
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);

        // 获取问题管理对象
        IssueManager issueManager = mgr.getIssueManager();

        // 根据ID获取问题
        Issue issue = issueManager.getIssueById(issueId);

        return issue == null ? null : issue;

    }


    /**
     * @description：校验项目是否存在，给缺省
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2018/12/24 上午9:55
     * @mofified By:
     */
    public static String validateProName(String proName) {

        return "".equals(proName) == true ? "first_pro" : proName;

    }


    /**
     * @description：判断是否为空
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2018/12/24 上午10:18
     * @mofified By:
     */
    public static String isEmpty(String param) {

        String defaultNull = "null";

        if("".equals(param) || defaultNull.equals(param) || param == null) {
            return "";
        } else {
            return param;
        }
    }

    /**
     * @description：删除所有问题
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2018/12/24 下午4:42
     * @mofified By:
     */
    public static void deleteIssues() throws Exception {
        // 目标项目
        String projectKey = "first_pro";

        // 声明Redmine管理器
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);

        // 获取问题管理对象
        IssueManager issueManager = mgr.getIssueManager();

        // 获取所有问题
        List<Issue> issues = issueManager.getIssues(projectKey, null);

        // 迭代输出问题
        for (Issue issue : issues) {
            System.out.println(issue.getDescription());
            System.out.println(issue.toString());
            issueManager.deleteIssue(issue.getId());
        }
    }

    public static void main(String[] args) throws Exception {
        RedmineIssueManager.deleteIssues();
    }
}
