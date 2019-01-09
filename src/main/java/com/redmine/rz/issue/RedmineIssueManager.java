package com.redmine.rz.issue;

import com.redmine.rz.bean.IssueBean;
import com.redmine.rz.bean.ProjectBean;
import com.redmine.rz.bean.RedmineUser;
import com.taskadapter.redmineapi.*;
import com.taskadapter.redmineapi.bean.*;
import com.taskadapter.redmineapi.internal.ResultsWrapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public boolean createIssueBug(IssueBean issueBean) throws Exception {

        // Redmine管理器
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
        // 获取问题管理对象
        IssueManager issueManager = mgr.getIssueManager();
        // 设置跟踪 1：问题；2：功能
        Tracker tracker = TrackerFactory.create(1);
        // 创建任务，并赋标题
        Issue issueToCreate = IssueFactory.create(1, isEmpty(issueBean.getTitle()));
        // 设置状态新建
        issueToCreate.setStatusId(1);
        // 设置项目
        issueToCreate.setProjectId(issueBean.getProId());
        // 设置跟踪
        issueToCreate.setTracker(tracker);
        // 设置发起人
        issueToCreate.setAuthorId(issueBean.getAuthorId());
        // 设置指派人
        issueToCreate.setAssigneeId(issueBean.getAssigneeId());
        // 设置问题描述
        issueToCreate.setDescription(isEmpty(issueBean.getDescription()));
        // 创建任务
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
    public boolean createIssueThings(IssueBean issueBean) throws Exception {

        // Redmine管理器
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
        // 获取问题管理对象
        IssueManager issueManager = mgr.getIssueManager();
        // 设置跟踪 1：问题；2：功能
        Tracker tracker = TrackerFactory.create(2);
        Issue issueToCreate = IssueFactory.create(1, isEmpty(issueBean.getTitle()));
        // 状态
        issueToCreate.setStatusId(1);
        // 跟踪类型
        issueToCreate.setTracker(tracker);
        // 任务创建人
        issueToCreate.setAuthorId(1);
        // 任务被指派人
        issueToCreate.setAssigneeId(issueBean.getAssigneeId());
        // 描述
        issueToCreate.setDescription(issueBean.getDescription());
        // 默认插入到项目标号为1的项目中
        issueToCreate.setProjectId(issueBean.getProId());
        // 到期日期（Date）
        issueToCreate.setDueDate(issueBean.getDueDate());
        // 预估时间（Float）
        issueToCreate.setEstimatedHours(issueBean.getEstimatedHours());
        // 耗时（Float）
        issueToCreate.setSpentHours(issueBean.getSpentHours());
        issueManager.createIssue(issueToCreate);

        return true;
    }

    /**
     * @description：根据用户Id，查询用户名字
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/1/4 下午4:24
     * @mofified By:
     */
    public String getUserName(int userId) throws RedmineException {
        // 声明Redmine管理器
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);

        // 获取用户管理对象
        UserManager userManager =  mgr.getUserManager();

        // 获取用户
        User user = userManager.getUserById(userId);

        // 判断用户名字
        String name = "";
        if(user.getFirstName() != null && user.getFirstName() != null) {
            name = user.getFirstName() + user.getLastName();
        }
        return  name;
    }

    /**
     * @description：查询某项目下所有问题
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2018/12/24 上午9:55
     * @mofified By:
     */
    public List<IssueBean> getIssues(String proName) throws Exception {

        // 声明Redmine管理器
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);

        // 获取问题管理对象
        IssueManager issueManager = mgr.getIssueManager();

        // 目标项目
        String projectKey = validateProName(proName);

        // 获取所有问题
        List<Issue> issues = issueManager.getIssues(projectKey, null);

        // 声明返回值
        List<IssueBean> issueBeans = new ArrayList<>();

        // 迭代输出问题
        if(issues != null && issues.size() > 0) {
            for (Issue issue : issues) {
                IssueBean issueBean = new IssueBean();
                issueBean.setStartDate(issue.getStartDate());
                issueBean.setAssigneeId(issue.getAssigneeId());
                issueBean.setDueDate(issue.getDueDate());
                issueBeans.add(issueBean);
            }
        }

        return issueBeans;

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
     * @description：查询某项目下指定问题（根据问题标题）
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2018/12/24 上午9:55
     * @mofified By:
     */
    public Boolean getIssueForTitle(String issueTitile, int assigneeId) throws Exception {

        // 声明Redmine管理器
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);

        // 获取问题管理对象
        IssueManager issueManager = mgr.getIssueManager();

        // 根据ID获取问题
        Map parameters = initParames(issueTitile, assigneeId);

        ResultsWrapper<Issue> issueResultsWrapper = issueManager.getIssues(parameters);

        if(issueResultsWrapper.hasSomeResults()) {
            return false;
        } else {
            return true;
        }
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

        return "".equals(proName) == true ? "bdjr-pzh" : proName;

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
     * @description：获取任务的个数
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/1/3 上午11:59
     * @param userId （用户主键，输入则查询对应用户所有任务数量，不输查所有）
     * @mofified By:
     */
    public int getIssuesCountByUserId(int userId) throws Exception {

        // 声明Redmine管理器
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);

        // 获取问题管理对象
        IssueManager issueManager = mgr.getIssueManager();

        // 根据ID获取问题
        Map parameters = initParames(null, userId);

        // 获取所有问题
        ResultsWrapper<Issue> issues = issueManager.getIssues(parameters);

        int count = 0;
        if(issues.hasSomeResults()) {
            count = issues.getResults().size();
        }

        return count;
    }

    /**
     * @description：查询任务总数（根据项目编号）
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/1/3 下午1:20
     * @mofified By:
     */
    public int getIssuesCountByPro(String prokey) throws Exception {

        // 声明Redmine管理器
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);

        // 获取问题管理对象
        IssueManager issueManager = mgr.getIssueManager();

        // 获取所有问题
        List<Issue> issues = issueManager.getIssues(prokey, null);

        int count = 0;
        if(issues.size() > 0) {
            count = issues.size();
        }

        return count;
    }

    /**
     * @description：初始化查询条件
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/1/3 下午1:14
     * @param title 标题
     * @param assigneeId 被指派人ID
     * @mofified By:
     */
    public Map initParames(String title, int assigneeId) {
        Map<String, String> params = new HashMap<String, String>();
        if(!StringUtils.isEmpty(title)) {
            params.put("subject", title);
        } else
        if(assigneeId != 0) {
            params.put("assigned_to_id", String.valueOf(assigneeId));
        }
        return params;
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
        String projectKey = "bdjr-pzh";
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

    /**
     * @description：初始化任务实体
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/1/3 下午12:02
     * @mofified By:
     */
    public static IssueBean initIssueBean(String proName, String title, String description) {

        IssueBean issueBean = new IssueBean();
        //项目名称
        issueBean.setProId(1);
        //demand创建需求（标题）
        issueBean.setTitle(title);
        //描述信息
        issueBean.setDescription(description);
        return issueBean;

    }

    /**
     * @description：查询Redmine所有项目
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/1/3 下午1:34
     * @mofified By:
     */
    public List<ProjectBean> getRedmineProjects() throws RedmineException {

        // 声明Redmine管理器
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);

        // 获取项目管理对象
        ProjectManager projectManager = mgr.getProjectManager();

        // 获取所有项目
        List<Project> projects = projectManager.getProjects();

        // 声明响应实体list
        List<ProjectBean> projectBeans = new ArrayList<>();

        for(Project project : projects) {
            ProjectBean projectBean = new ProjectBean();
            projectBean.setProId(project.getId());
            projectBean.setProName(project.getName());
            projectBean.setDescription(project.getDescription());
            projectBeans.add(projectBean);
        }

        return projectBeans;

    }

    /**
     * @description：查询所有用户
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/1/3 下午1:34
     * @mofified By:
     */
    public List<RedmineUser> getUsers() throws RedmineException {

        // 声明Redmine管理器
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);

        // 获取用户管理对象
        UserManager userManager = mgr.getUserManager();

        // 获取所有用户
        List<User> users = userManager.getUsers();

        // 声明响应bean
        List<RedmineUser> redmineUsers = new ArrayList<>();

        // 循环并封装bean
        for(User user : users) {
            if(user.getId() == 1){
                continue;
            }
            RedmineUser redmineUser = new RedmineUser();
            redmineUser.setUserId(user.getId());
            redmineUser.setUserName(user.getLastName() + user.getFirstName());
            redmineUsers.add(redmineUser);
        }

        return redmineUsers;
    }

    /**
     * @description：查询所有用户，并转换成Map，key是userId，value是userName
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/1/3 下午1:34
     * @mofified By:
     */
    public Map getUsersInfo() throws RedmineException {

        // 声明Redmine管理器
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);

        // 获取用户管理对象
        UserManager userManager = mgr.getUserManager();

        // 获取所有用户
        List<User> users = userManager.getUsers();

        Map<Integer, String> map = new HashMap<>();
        for(User user : users) {
            map.put(user.getId(), user.getLastName() + user.getFirstName());
        }
        return map;
    }

    /**
     * @description：根据用户主键查询所有任务
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/1/3 下午1:51
     * @mofified By:
     */
    public List<IssueBean> getIssuesByUserId(int userId) throws RedmineException {

        List<IssueBean> issueBeans = new ArrayList<>();

        // 声明Redmine管理器
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);

        // 获取任务管理对象
        IssueManager issueManager = mgr.getIssueManager();

        Map params = initParames(null, userId);

        ResultsWrapper<Issue> issueResultsWrapper = issueManager.getIssues(params);

        // 初始化响应信息bean
        if(issueResultsWrapper.hasSomeResults()) {
            List<Issue> issues = issueResultsWrapper.getResults();
            if(issues.size() > 0) {
                for(Issue issue : issues) {
                    IssueBean issueBean = new IssueBean();
                    issueBean.setId(issue.getId());
                    issueBean.setProId(issue.getProjectId());
                    issueBean.setTitle(issue.getSubject());
                    issueBean.setDescription(issue.getDescription());
                    issueBeans.add(issueBean);
                }
            }
        }

        return issueBeans;

    }

    /**
     * @description：查询所有问题
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2018/12/24 下午4:42
     * @mofified By:
     */
    public static void findIssue() throws Exception {
        // 目标项目
        String projectKey = "bdjr-pzh";

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
            System.out.println(issue.getAssigneeId());
        }
    }

    /**
     * @description：查询所有问题（某用户）
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2018/12/24 下午4:42
     * @mofified By:
     */
    public static void findIssueByUserId(String userId) throws Exception {
        // 目标项目
        String projectKey = "bdjr-pzh";

        // 声明Redmine管理器
        RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);

        // 获取问题管理对象
        IssueManager issueManager = mgr.getIssueManager();

        Params params = new Params();
        params.add("assigned_to_id", userId);

        // 获取所有问题
        ResultsWrapper<Issue> issueResultsWrapper = issueManager.getIssues(params);

        if(issueResultsWrapper.hasSomeResults()) {
            List<Issue> issues = issueResultsWrapper.getResults();
            // 迭代输出问题
            for (Issue issue : issues) {
                System.out.println("DES ---- " +issue.getDescription());
                System.out.println("ASS ---- " +issue.getAssigneeId());
            }
        }

    }

    public static void updateIssueInfo() throws RedmineException {
        RedmineManager redmineManager = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
        IssueManager issueManager = redmineManager.getIssueManager();
        Issue issue = issueManager.getIssueById(314, Include.watchers);
        issue.setAuthorId(11);
        issue.setAuthorName("玥龙 苏");
        issue.setSubject("我我我我");
        issueManager.update(issue);
    }

    public static void main(String[] args) throws Exception {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "s1");
        jsonObject.put("data", "[129.9, 171.5, 306.4, 429.2, 144.0, 176.0, 135.6, 248.5, 216.4, 194.1, 95.6, 54.4]");

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("name", "s2");
        jsonObject2.put("data", "[121.9, 216.4, 194.1, 95.6, 54.4]");

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject);
        jsonArray.put(jsonObject2);
        System.out.println(jsonArray.toString());

    }
}
