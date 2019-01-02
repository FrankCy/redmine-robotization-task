package com.redmine.rz.impl;

import com.redmine.rz.bean.IssueBean;
import com.redmine.rz.service.ResolveExcelService;
import com.redmine.rz.util.BdjrDateUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: redmine-robotization-task
 * @package: com.redmine.rz.impl、
 * @email: cy880708@163.com
 * @date: 2019/1/2 下午3:24
 * @mofified By:
 */
@Service("resolveExcelServiceImpl")
public class ResolveExcelServiceImpl implements ResolveExcelService {

    /**
     *打印日志
     */
    private static final Log logger = LogFactory.getLog(ResolveExcelServiceImpl.class);
    /**
     * 注册url
     */
    private static final String SUFFIX_2003 = ".xls";

    private static final String SUFFIX_2007 = ".xlsx";

    @Override
    public List<IssueBean> initRedmineInfo(MultipartFile file) throws Exception {

        List<IssueBean> list = new ArrayList<>();
        if (file == null) {
            throw new Exception("对象不能为空");
        }
        //获取文件的名字
        String originalFilename = file.getOriginalFilename();
        Workbook workbook = null;
        try {
            if (originalFilename.endsWith(SUFFIX_2003)) {
                workbook = new HSSFWorkbook(file.getInputStream());
            } else if (originalFilename.endsWith(SUFFIX_2007)) {
                workbook = new XSSFWorkbook(file.getInputStream());
            }
        } catch (Exception e) {
            logger.info(originalFilename);
            e.printStackTrace();
            throw new Exception("格式错误");
        }
        if (workbook == null) {
            logger.info(originalFilename);
            throw new Exception("格式错误");
        } else {
            //获取所有的工作表的的数量
            int numOfSheet = workbook.getNumberOfSheets();
            //遍历这个这些表
            for (int i = 0; i < numOfSheet; i++) {
                //获取一个sheet也就是一个工作簿
                Sheet sheet = workbook.getSheetAt(i);
                int lastRowNum = sheet.getLastRowNum();
                //从第一行开始第一行一般是标题
                for (int j = 1; j <= lastRowNum; j++) {
                    Row row = sheet.getRow(j);
                    logger.info("row ------- " + row.toString());
                    IssueBean issueBean = new IssueBean();
                    //标题
                    if (row.getCell(0) != null) {
                        row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                        String title = row.getCell(0).getStringCellValue();
                        logger.info("title ------- " + title);
                        issueBean.setTitle(title);
                    }
                    //描述
                    if (row.getCell(1) != null) {
                        row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                        String description = row.getCell(1).getStringCellValue();
                        logger.info("description ------- " + description);
                        issueBean.setDescription(description);
                    }
                    //指派给谁
                    if (row.getCell(2) != null) {
                        row.getCell(2).setCellType(Cell.CELL_TYPE_NUMERIC);
                        Double assigneeId = row.getCell(2).getNumericCellValue();
                        logger.info("assigneeId ------- " + assigneeId);
                        issueBean.setAssigneeId(assigneeId.intValue());
                    }
                    //截止时间
                    if (row.getCell(3) != null) {
                        row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                        String dueDateString = row.getCell(3).getStringCellValue();
                        logger.info("dueDate ------- " + dueDateString);
                        issueBean.setDueDate(BdjrDateUtil.StringDateToDate(dueDateString));
                    }
                    //预估完成时间（小时）
                    if (row.getCell(4) != null) {
                        row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                        String estimatedHours = row.getCell(4).getStringCellValue();
                        logger.info("estimatedHours ------- " + estimatedHours);
                        issueBean.setEstimatedHours(Float.valueOf(estimatedHours));
                    }
                    list.add(issueBean);
                }
            }
        }
        return list;
    }


}

