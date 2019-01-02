package com.redmine.rz.service;

import com.redmine.rz.bean.IssueBean;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: redmine-robotization-task
 * @package: com.redmine.rz.service、
 * @email: cy880708@163.com
 * @date: 2019/1/2 下午3:23
 * @mofified By:
 */
public interface ResolveExcelService {

    /**
     * @exception Exception 错误
     * @param file 上传文件
     * @description：
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/1/2 下午5:23
     * @mofified By:
     */
    List<IssueBean> initRedmineInfo(MultipartFile file) throws Exception;
}
