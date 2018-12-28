package com.redmine.rz.controller;

import com.redmine.rz.issue.RedmineIssueManager;
import com.redmine.rz.service.RedmineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: redmine-robotization-task
 * @package: com.redmine.rz.controller、
 * @email: cy880708@163.com
 * @date: 2018/12/28 上午10:25
 * @mofified By:
 */
@Controller
@RequestMapping (value = "/redmine")
public class RedmineController {

    @Autowired
    RedmineService redmineService;

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

}
