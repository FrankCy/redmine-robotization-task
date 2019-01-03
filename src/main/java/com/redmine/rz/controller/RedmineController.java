package com.redmine.rz.controller;

import com.redmine.rz.bean.IssueBean;
import com.redmine.rz.bean.ProjectBean;
import com.redmine.rz.bean.ServiceResult;
import com.redmine.rz.bean.UserIssue;
import com.redmine.rz.service.RedmineService;
import com.redmine.rz.service.ResolveExcelService;
import com.xiaoleilu.hutool.date.DateTime;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    /**
     *打印日志
     */
    private static final Log logger = LogFactory.getLog(RedmineController.class);

    @Resource(name = "redmineServiceImpl")
    RedmineService redmineService;

    @Resource(name = "resolveExcelServiceImpl")
    ResolveExcelService resolveExcelService;

    /**
     * @description：首页跳转
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/1/2 下午4:59
     * @mofified By:
     */
    @RequestMapping(value = "/index")
    public ModelAndView index() {
        //初始化响应模版
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        //查询平台所有项目
        List<ProjectBean> projectBeans = redmineService.getRedmineProjects();
        modelAndView.addObject("projectBeans", projectBeans);

        //查询平台任务总数
        int issueByProCount = redmineService.getIssuesCountByPro("");
        modelAndView.addObject("issueByProCount", issueByProCount);

        //查询每个人的任务总数
        List<UserIssue> userIssueList = redmineService.getUsersIssue();
        modelAndView.addObject("userIssueList", userIssueList);

        return modelAndView;
    }

    /**
     * @description：上传Excel
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/1/2 下午4:59
     * @mofified By:
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ModelAndView uploadExcel(@RequestParam("file") MultipartFile uploadFile) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("uploadError");
        try {
            List<IssueBean> issueBeans = resolveExcelService.initRedmineInfo(uploadFile);
            ServiceResult result = redmineService.createIssues(issueBeans);
            String resCode = result.getResCode();
            if(!StringUtils.isEmpty(resCode) && "200".equals(resCode)) {
                //判断是否有重复的
                modelAndView.addObject("dIsList", result.getResList());
                modelAndView.setViewName("uploadSuccess");
                return modelAndView;
            } else {
                return modelAndView;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/getRedmineIssueJson")
    @ResponseBody
    public String getRedmineIssueJson() throws Exception {

        //定义起始日期
        Date d1 = new SimpleDateFormat("yyyyMMdd").parse("20150101");
        //定义结束日期
        Date d2 = new SimpleDateFormat("yyyyMMdd").parse("20191231");
        //定义日期实例
        Calendar dd = Calendar.getInstance();
        //设置日期起始时间
        dd.setTime(d1);
        //判断是否到结束日期
        int i=2000;
        List listTwo = new ArrayList();
        while(dd.getTime().before(d2)){
            i = i-1;
            DateTime dateTime = new DateTime(dd.getTime());
            //进行当前日期月份加1
            dd.add(Calendar.DAY_OF_MONTH, 1);
            List listOne = new ArrayList();
            listOne.add(dateTime.getTime());
            listOne.add(i);
            listTwo.add(listOne);
        }
        return listTwo.toString();
    }

}
