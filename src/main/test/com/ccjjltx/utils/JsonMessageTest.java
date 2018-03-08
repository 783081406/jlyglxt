package com.ccjjltx.utils;

import net.sf.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by ccjjltx on 2018/3/8.
 * 对JsonMessageTest进行测试
 *
 * @author ccj
 * @version 1.0
 */
public class JsonMessageTest {
    /**
     * 验证：状态码为true时
     */
    @Test
    public void testReturnMessage1() {
        JSONObject result = JsonMessage.returnMessage(true, "success");
        Assert.assertEquals(true, result.get("success"));
        JsonMessage jsonMessage = new JsonMessage();//弥补计算规则出错
    }

    /**
     * 验证：状态码为false时
     */
    @Test
    public void testReturnMessage2() {
        JSONObject result = JsonMessage.returnMessage(false, "error");
        Assert.assertEquals("error", result.get("msg"));
    }

    /**
     * 验证：returnMessage方法
     */
    @Test
    public void testReturnMessage3() {
        JSONObject result = JsonMessage.returnMessage2(true, "123");
        Assert.assertEquals(true, result.get("code"));
    }

}
