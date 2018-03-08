package com.ccjjltx.utils;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ccjjltx on 2018/3/8.
 * 对MyDateFormat进行单元测试
 *
 * @author ccj
 * @version 1.0
 */
public class MyDateFormatTest {

    /**
     * 验证：是否能转换
     */
    @Test
    public void testFormat() {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Assert.assertEquals(date, MyDateFormat.format(new Date()));
        MyDateFormat myDateFormat = new MyDateFormat();//弥补计算规则出错
    }

    /**
     * 验证：是否能转换
     */
    @Test
    public void testParse() {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2018-01-01");
            Assert.assertEquals(date, MyDateFormat.parse("2018-01-01"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
