# Redmine Rest API —— JAVA #

## 简介 ##
通过Java调用Redmine Rest API进行创建Demand或Bug

## 应用场景 ##
1. 规定Java可读取格式，解析需求功能清单，自动创建功能列表；
2. 将此代码打包成jar，放入可执行jar包的自动化测试工具中作为外部引用jar进行使用，当自动化测试脚本出现问题时，调用外部引用jar进行自动创建任务；
注：通过此功能，集成Jenkins完成DevOps；
## 核心代码 ##
### 创建 ###
```java
// Redmine管理器
RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);
// 获取问题管理对象
IssueManager issueManager = mgr.getIssueManager();
// 设置跟踪 1：问题；2：功能
Tracker tracker = TrackerFactory.create(1);
// 创建任务，并赋标题
Issue issueToCreate = IssueFactory.create(1, isEmpty(title));
// 设置状态新建
issueToCreate.setStatusId(1);
// 设置跟踪
issueToCreate.setTracker(tracker);
// 设置发起人
issueToCreate.setAuthorId(1);
// 设置指派人
issueToCreate.setAssigneeId(9);
// 设置问题描述
issueToCreate.setDescription(isEmpty(description));
// 创建任务
issueManager.createIssue(issueToCreate);
```

### 删除 ###
```java
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
```

### 地址 ###
地址|描述|
---|:--:
首页| http://localhost:8681/redmine/index
用户| http://192.168.10.110:3000/users.xml
任务| http://192.168.10.110:3000/issues.xml
任务状态| http://192.168.10.110:3000/issue_statuses.xml
