package com.redmine.rz.service;

import com.redmine.rz.bean.IssueBean;
import com.redmine.rz.bean.ProjectBean;
import com.redmine.rz.bean.ServiceResult;
import com.redmine.rz.bean.UserIssue;

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
    ServiceResult createIssues(List<IssueBean> issueBeanList);

    /**
     * @description：根据项目编号查询任务总数
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/1/3 下午1:24
     * @mofified By:
     */
    int getIssuesCountByPro(String prokey);

    /**
     * @description：根据用户id获取任务总数
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/1/3 下午1:24
     * @mofified By:
     */
    int getIssuesCountByUserId(int userId);

    /**
     * @description：查询所有用户与他的任务信息
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/1/3 下午1:43
     * @mofified By:
     */
    List<UserIssue> getUsersIssue();

    /**
     * @description：查询平台所有项目
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/1/3 下午1:35
     * @mofified By:
     */
    List<ProjectBean> getRedmineProjects();

    /**
     * @description 获取燃尽图
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/1/9 上午11:18
     * @mofified By:
     */
    String getRedmineIssueJson(String proName) throws Exception;

    /**
     * @description 获取饼状图
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/1/9 上午11:18
     * @mofified By:
     */
    String getIssueProportion(String proName) throws Exception;

}
