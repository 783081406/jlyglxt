package com.ccjjltx.action;

import org.apache.struts2.StrutsSpringJUnit4TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.ServletException;
import java.io.UnsupportedEncodingException;

/**
 * Created by ccjjltx on 2018/3/5.
 * 测试HomeAction方法（进入后台首页的过滤器）
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class HomeActionTest extends StrutsSpringJUnit4TestCase<HomeAction> {

    /**
     * describe：验证合法性
     */
    @Test
    public void testCheckValidity1() {
        try {
            request.setParameter("userName", "admin");
            request.setParameter("password", "admin");
            executeAction("/backstage/index.action");
            String result = executeAction("/home/index.action");
            System.out.println(result.length());//{"code":true,"msg":"success"}
            Assert.assertEquals(29, result.length());//{"code":true,"msg":"success"}
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
