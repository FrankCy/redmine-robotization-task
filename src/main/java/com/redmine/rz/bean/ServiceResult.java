package com.redmine.rz.bean;

import java.util.List;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: redmine-robotization-task
 * @package: com.redmine.rz.bean、
 * @email: cy880708@163.com
 * @date: 2019/1/3 上午11:03
 * @mofified By:
 */
public class ServiceResult {

    private String resCode;

    private String resMessage;

    private List resList;

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResMessage() {
        return resMessage;
    }

    public void setResMessage(String resMessage) {
        this.resMessage = resMessage;
    }

    public List getResList() {
        return resList;
    }

    public void setResList(List resList) {
        this.resList = resList;
    }
}
