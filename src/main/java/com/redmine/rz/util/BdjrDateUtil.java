package com.redmine.rz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: redmine-robotization-task
 * @package: com.redmine.rz.util、
 * @email: cy880708@163.com
 * @date: 2019/1/2 下午4:28
 * @mofified By:
 */
public class BdjrDateUtil {

    private static String timePattern = "yyyyMMdd";

    /**
     * @description：转换日期，将String类型转换为Date
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2019/1/2 下午4:35
     * @mofified By:
     */
    public static Date StringDateToDate(String dateString) throws ParseException {

        //定义转换格式
        SimpleDateFormat formatter = new SimpleDateFormat(timePattern);

        //进行转换
        return formatter.parse(dateString);
    }

}
