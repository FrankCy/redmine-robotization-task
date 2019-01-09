package com.redmine.rz.bean;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: redmine-robotization-task
 * @package: com.redmine.rz.bean、
 * @email: cy880708@163.com
 * @date: 2019/1/9 下午4:59
 * @mofified By:
 */
public class LineChart {

    /**
     * 项目名称
     */
    private String proName;

    /**
     * 项目数据
     */
    private String proData;

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProData() {
        return proData;
    }

    public void setProData(String proData) {
        this.proData = proData;
    }

    @Override
    public String toString() {
        return "LineChart{" +
                "proName='" + proName + '\'' +
                ", proData='" + proData + '\'' +
                '}';
    }
}
