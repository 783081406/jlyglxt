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
 * Created by ccjjltx on 2018/3/6.
 * 测试User2Action方法（下拉框选择用户账户使用）
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class User2ActionTest extends StrutsSpringJUnit4TestCase<User2Action> {
    @Resource(name = "user2Action")
    private User2Action user2Action;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * describe：下拉框选择用户账户使用
     * 场景：员工中心->员工信息
     */
    @Test
    @Transactional
    public void testGetIdUserName() {
        user2Action.setResult(null);
        Assert.assertEquals("success", user2Action.getIdUserName());
        Assert.assertNotNull(user2Action.getResult());
    }

}
