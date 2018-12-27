# Redmine Rest API —— JAVA #

### 核心代码 ##
```
// Redmine管理器
RedmineManager mgr = RedmineManagerFactory.createWithApiKey(uri, apiAccessKey);

// 获取问题管理对象
IssueManager issueManager = mgr.getIssueManager();

// 设置跟踪 1：问题；2：功能
Tracker tracker = TrackerFactory.create(1);

Issue issueToCreate = IssueFactory.create(1, isEmpty(title)); //创建任务，并赋标题
issueToCreate.setStatusId(1); //设置状态新建
issueToCreate.setTracker(tracker); //设置跟踪
issueToCreate.setAuthorId(1); //设置发起人
issueToCreate.setAssigneeId(9); //设置指派人
issueToCreate.setDescription(isEmpty(description)); //设置问题描述
issueManager.createIssue(issueToCreate); //创建任务

```