package com.redmine.rz.service;

import com.redmine.rz.bean.IssueBean;

import java.util.List;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: redmine-robotization-task
 * @package: com.redmine.rz.service、
 * @email: cy880708@163.com
 * @date: 2018/12/28 上午10:56
 * @mofified By:
 */
public interface RedmineService {

    /**
     * @param issueBeanList 任务集合
     * @description：
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/1/2 下午5:23
     * @mofified By:
     */
    boolean createIssues(List<IssueBean> issueBeanList);

}
