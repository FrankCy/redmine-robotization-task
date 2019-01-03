package com.redmine.rz.bean;

import java.util.List;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: redmine-robotization-task
 * @package: com.redmine.rz.bean、
 * @email: cy880708@163.com
 * @date: 2019/1/3 下午1:41
 * @mofified By:
 */
public class UserIssue extends RedmineUser {

    private List<IssueBean> userIssues;

    public List<IssueBean> getUserIssues() {
        return userIssues;
    }

    public void setUserIssues(List<IssueBean> userIssues) {
        this.userIssues = userIssues;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for(IssueBean issueBean : userIssues) {
            stringBuffer.append(getUserId()+"-");
            stringBuffer.append(getUserName()+"-");
            stringBuffer.append(issueBean.toString()).append(",\n") ;
        }
        return stringBuffer.toString();
    }
}
