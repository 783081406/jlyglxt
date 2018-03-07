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

/**
 * Created by ccjjltx on 2018/3/3.
 * 测试BespeakAction方法（体验预约相关功能）
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class BespeakActionTest extends StrutsSpringJUnit4TestCase<BespeakAction> {
    @Resource(name = "bespeakAction")
    private BespeakAction bespeakAction;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * describe：得到所有已处理的数据
     */
    @Test
    @Transactional
    public void testGetAllHandleInformation() {
        bespeakAction.setPage(1);
        bespeakAction.setRows(5);
        bespeakAction.setResult(null);
        Assert.assertEquals("success", bespeakAction.getAllHandleInformation());
        //断言JSON返回数据
        Assert.assertEquals(5, ((JSONArray) bespeakAction.getResult().get("rows")).size());
    }

    /**
     * describe：得到所有未处理的数据
     */
    @Test
    @Transactional
    public void testGetAllUnhandleInformation() {
        bespeakAction.setPage(1);
        bespeakAction.setRows(5);
        Assert.assertEquals("success", bespeakAction.getAllUnhandleInformation());
        //断言JSON返回数据
        Assert.assertEquals(5, ((JSONArray) bespeakAction.getResult().get("rows")).size());
    }

    /**
     * describe：提交过来的处理信息
     */
    @Test
    @Transactional
    @Rollback
    public void testHandleInformation() {
        try {
            request.setParameter("bid", "24");
            bespeakAction.setBid(24);
            String result = executeAction("/bespeakaction/handleInformation.action");
            Assert.assertEquals(24, bespeakAction.getBid());
            Assert.assertEquals(16, result.length());//16  //{"success":true}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * describe：新增
     * return : 前台首页
     */
    @Test
    @Transactional
    @Rollback
    public void testSaveInformation() {
        bespeakAction.setName("123");
        bespeakAction.setPhone("123");
        bespeakAction.setEname("123");
        bespeakAction.setEage(56);
        Assert.assertEquals("reception", bespeakAction.saveInformation());
    }

}
