package com.ccjjltx.action;

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

/**
 * Created by ccjjltx on 2018/3/5.
 * 测试BoardwagesAction方法（对Boardwages相关操作（伙食费用））
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class BoardwagesActionTest extends StrutsSpringJUnit4TestCase<BoardwagesAction> {
    @Resource(name = "boardwagesAction")
    private BoardwagesAction boardwagesAction;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * describe：得到所有的信息
     */
    @Test
    @Transactional
    public void testGetAllInformation() {
        Assert.assertEquals("success", boardwagesAction.getAllInformation());
    }

    /**
     * describe：得到所有的信息
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation() {
        boardwagesAction.setMinimum(100);
        boardwagesAction.setHighest(200);
        boardwagesAction.setResult(null);
        Assert.assertEquals("success", boardwagesAction.updateInformation());
        Assert.assertEquals(true, boardwagesAction.getResult().get("success"));
    }
}
