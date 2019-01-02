package com.redmine.rz.bean;

import java.util.Date;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: redmine-robotization-task
 * @package: com.redmine.rz.bean、
 * @email: cy880708@163.com
 * @date: 2018/12/28 上午10:58
 * @mofified By:
 */
public class IssueBean {

    //项目主键
    private int proId;

    //任务标题
    private String title;

    //任务被指派人
    private int assigneeId;

    //任务描述
    private String description;

    //到期日期
    private Date dueDate;

    //预估时间（工时）
    private Float estimatedHours;

    //实际耗时
    private Float spentHours;

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(int assigneeId) {
        this.assigneeId = assigneeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Float getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(Float estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public Float getSpentHours() {
        return spentHours;
    }

    public void setSpentHours(Float spentHours) {
        this.spentHours = spentHours;
    }

    @Override
    public String toString() {
        return "IssueBean{" +
                "proId=" + proId +
                ", title='" + title + '\'' +
                ", assigneeId=" + assigneeId +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                ", estimatedHours=" + estimatedHours +
                ", spentHours=" + spentHours +
                '}';
    }
}
