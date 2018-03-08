package com.ccjjltx.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ccjjltx on 2017/10/21.
 * 工具类：用于格式化从数据库得到的Date，格式化成String
 *
 * @author ccj
 * @version 1.0
 */
public class MyDateFormat {

    /**
     * 装饰模式
     * date 转 String
     *
     * @param date 传入date值
     * @return 字符串类型
     */
    public static String format(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    /**
     * String 转 date
     *
     * @param dateString 日期
     * @return String类型
     * @throws ParseException 转换错误异常
     */
    public static Date parse(String dateString) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
    }
}
