package com.ccjjltx.utils;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ccjjltx on 2017/11/20.
 * 测试ResourcePath类
 *
 * @author ccj
 * @version 1.0
 */
public class ResourcePathTest {
    @Test
    public void testParse() {
        String str = "e:\\\\123\\\\1234";
        String result = ResourcePath.parse(str);
        Assert.assertEquals("e:\\123\\1234", result);
        ResourcePath resourcePath = new ResourcePath();//弥补计算规则出错
    }
}
