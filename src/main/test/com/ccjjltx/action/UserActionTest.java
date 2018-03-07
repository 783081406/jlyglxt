package com.ccjjltx.action;

import net.sf.json.JSONArray;
import org.apache.struts2.StrutsSpringJUnit4TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import java.io.UnsupportedEncodingException;

/**
 * Created by ccjjltx on 2018/3/6.
 * 测试UserAction方法（账户数据的增删改查）
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class UserActionTest extends StrutsSpringJUnit4TestCase<UserAction> {
    @Resource(name = "userAction")
    private UserAction userAction;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * describe：得到所有的用户信息
     */
    @Test
    @Transactional
    public void testUser() {
        userAction.setPage(1);
        userAction.setRows(7);
        userAction.setResult(null);
        Assert.assertEquals("success", userAction.getUser());
        Assert.assertEquals(7, ((JSONArray) userAction.getResult().get("rows")).size());
    }

    /**
     * describe：增加User
     * error  类型错误
     */
    @Test
    @Transactional
    @Rollback
    public void testSaveUser1() {
        userAction.setUserName("ccjccjccj");
        userAction.setPassword("ccjccjccj");
        userAction.setUType(55);
        Assert.assertEquals("error", userAction.saveUser());
    }

    /**
     * describe：增加User
     * error  数据库中已经存在相同的用户名了
     */
    @Test
    @Transactional
    @Rollback
    public void testSaveUser2() {
        userAction.setUserName("admin");
        userAction.setPassword("admin");
        userAction.setUType(1);
        Assert.assertEquals("error", userAction.saveUser());
    }

    /**
     * describe：增加User
     * success
     */
    @Test
    @Transactional
    @Rollback
    public void testSaveUser3() {
        userAction.setUserName("ccjccjccj");
        userAction.setPassword("ccjccjccj");
        userAction.setUType(1);
        Assert.assertEquals("success", userAction.saveUser());
    }

    /**
     * describe：更新信息
     * error  更新失败：类型必须管理员或普通
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateUser1() {
        userAction.setUType(66);
        Assert.assertEquals("error", userAction.updateUser());
    }

    /**
     * describe：更新信息
     * success
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateUser2() {
        userAction.setId(1);
        userAction.setUserName("admin");
        userAction.setPassword("admin");
        userAction.setUType(1);
        Assert.assertEquals("success", userAction.updateUser());
    }

    /**
     * describe：删除功能
     */
    @Test
    @Transactional
    @Rollback
    public void testRemoveUser() {
        userAction.setId(2);
        Assert.assertEquals("success", userAction.removeUser());
    }

    /**
     * describe：更新密码
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdatePassword() {
        try {
            request.setParameter("userName", "admin");
            request.setParameter("password", "admin");
            executeAction("/backstage/index.action");
            request.setParameter("newpass", "admin");
            executeAction("/useraction/updatePassword.action");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
