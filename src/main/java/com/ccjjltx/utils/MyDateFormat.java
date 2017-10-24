package com.ccjjltx.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ccjjltx on 2017/10/21.
 * 工具类：用于格式化从数据库得到的Date，格式化成String
 * 不可实例化
 *
 * @author ccj
 * @version 1.0
 */
public class MyDateFormat {
    //私有化不可实例
    private MyDateFormat() {
    }

    /**
     * 装饰模式
     *
     * @param date 传入date值
     * @return 字符串类型
     */
    public static String format(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static Date parse(String dateString) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
    }
}
