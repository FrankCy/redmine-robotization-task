package com.redmine.rz.bean;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: redmine-robotization-task
 * @package: com.redmine.rz.bean、
 * @email: cy880708@163.com
 * @date: 2019/1/3 下午1:30
 * @mofified By:
 */
public class ProjectBean {

    private int proId;

    private String proName;

    private String description;

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
