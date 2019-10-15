# Redmine Rest API —— JAVA #

## 简介 ##
通过Java调用Redmine Rest API进行创建Demand或Bug

## 应用场景 ##
**```提高团队工作效率(在使用Redmine作为缺陷管理工具的情况下)```**
### 痛点 ###
- 每次开会研发收集需求，在会上收集一边，在会后需要手动向Redmine录入一遍问题，之间团队成员会浪费大量时间来等待明确任务。
### 解决痛点 ###
- 需求会议期间，研发主管将问题收集（手动录入指定格式的Excel内），会后登陆此系统直接上传Excel，系统自动解析Excel并上传任务至Redmine，团队成员可以马上明确自身任务，并进入到工作中。

## 应用扩展 ##
- 此系统不单可以应用在研发团队，也可以应用在业务团队，只要使用Redmine缺陷管理即可，JIRA也支持RestfulAPI，不过收费，公司可根据自身情况使用；
- 此系统核心代码（调用Redmine的工具类）可打包引入至JMeter作为外部依赖，在执行自动化测试时，如果发生BUG，可调用函数创建Redmine BUG并指向研发人员，```需要测试人员直到每个函数的意义，并得到Redmine人员ID等信息```
- 此系统可以扩展传递Img，即需求截图等（未实现）

1. 规定Java可读取格式(Excel或自定义其它，本系统用的POI解析Excle）功能清单，解析需求功能清单，自动创建功能列表；
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
```

## 操作手册 ##

### 配置流程 ###
- 修改redmine配置
```
位置：com.redmine.rz.issue.RedmineIssueManager (uri / apiAccessKey)
```

- Maven构建
maven构建后会在系统根目录target下生成应用jar包，使用此jar包发布，并使用应用

### 访问 ###
地址|描述|
---|:--
首页| http://localhost:8681/redmine/index
用户| http://redmine-home:3000/users.xml
任务| http://redmine-home:3000/issues.xml
任务状态| http://redmine-home:3000/issue_statuses.xml

### 项目博客 ###
- [CSDN-Java for Redmine](https://frankyoung.blog.csdn.net/article/details/85787483)
- [CSDN-Jenkins + JMeter + Redmine 完成DevOps](https://frankyoung.blog.csdn.net/article/details/85236349)
- [CSDN-Java Redmine Rest API](https://frankyoung.blog.csdn.net/article/details/85100850)
### 扩展【JMeter自动化测试发布Remine BUG】
- [CSDN-JMeter 调用自定义Java程序创建Redmine问题](https://frankyoung.blog.csdn.net/article/details/85233143)
- [CSDN-JMeter 调用自定义Java程序创建Redmine问题（II）](https://frankyoung.blog.csdn.net/article/details/85236294)
