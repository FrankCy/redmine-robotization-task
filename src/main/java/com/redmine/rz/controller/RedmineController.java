package com.redmine.rz.controller;

import com.redmine.rz.bean.IssueBean;
import com.redmine.rz.impl.ResolveExcelServiceImpl;
import com.redmine.rz.service.ResolveExcelService;
import com.taskadapter.redmineapi.bean.Issue;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Resource(name = "resolveExcelServiceImpl")
    ResolveExcelService resolveExcelService;

    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadExcel(@RequestParam("file") MultipartFile uploadFile) {

        Map resultMap = new HashMap(2);

        List<IssueBean> result;

        try {
            result = resolveExcelService.initRedmineInfo(uploadFile);
            for(IssueBean issueBean : result) {
                logger.info(issueBean.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resCode", "400");
            resultMap.put("resMessage", "解析错误");
        }

        resultMap.put("resCode", "200");
        resultMap.put("resMessage", "上传成功");

        return "uploadSuccess";
    }

}
