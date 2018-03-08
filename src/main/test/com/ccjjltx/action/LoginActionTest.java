package com.ccjjltx.action;

import org.apache.struts2.StrutsSpringJUnit4TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by ccjjltx on 2018/3/5.
 * 测试LoginAction方法（员工信息增删改查）
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class LoginActionTest extends StrutsSpringJUnit4TestCase<LoginAction> {
    @Resource(name = "loginAction")
    private LoginAction loginAction;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * describe：验证用户登录
     * first
     */
    @Test
    @Transactional
    public void testCheckForntLogin1() {
        loginAction.setUserName(null);
        loginAction.setPassword(null);
        Assert.assertEquals("first", loginAction.checkForntLogin());
    }

    /**
     * describe：验证用户登录
     * error    用户名错误
     */
    @Test
    @Transactional
    public void testCheckForntLogin2() {
        loginAction.setUserName("123321");
        loginAction.setPassword("1234341");
        Assert.assertEquals("error", loginAction.checkForntLogin());
    }

    /**
     * describe：验证用户登录
     * error    密码错误
     */
    @Test
    @Transactional
    public void testCheckForntLogin3() {
        loginAction.setUserName("admin");
        loginAction.setPassword("adminadmin123");
        Assert.assertEquals("error", loginAction.checkForntLogin());
    }

    /**
     * describe：验证用户登录
     * success
     */
    @Test
    @Transactional
    public void testCheckForntLogin4() {
        try {
            request.setParameter("userName", "admin");
            request.setParameter("password", "admin");
            String result = executeAction("/backstage/index.action");
            Assert.assertNotNull(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * describe：判断是否可以跳转到后台首页
     * error
     */
    @Test
    @Transactional
    public void testSignIn1() {
        try {
            String result = executeAction("/backstage/signIn.action");
            Assert.assertNotNull(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * describe：判断是否可以跳转到后台首页
     * error
     */
    @Test
    @Transactional
    public void testSignIn2() {
        try {
            request.setParameter("userName", "admin");
            request.setParameter("password", "admin");
            executeAction("/backstage/index.action");
            String result = executeAction("/backstage/signIn.action");
            Assert.assertNotNull(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * describe：退出
     */
    @Test
    @Transactional
    public void testSignOut() {
        try {
            request.setParameter("userName", "admin");
            request.setParameter("password", "admin");
            loginAction.checkForntLogin();
            String result = executeAction("/backstage/signOut.action");
            Assert.assertNotNull(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
