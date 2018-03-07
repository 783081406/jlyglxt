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
 * Created by ccjjltx on 2018/3/6.
 * 测试SserviceAction方法（特別服务的增删除改查）
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class SserviceActionTest extends StrutsSpringJUnit4TestCase<SserviceAction> {
    @Resource(name = "sserviceAction")
    private SserviceAction sserviceAction;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * describe：得到全部或特定(搜索框触发)的特别服务的数据
     */
    @Test
    @Transactional
    public void testGetAllInformation() {
        sserviceAction.setPage(1);
        sserviceAction.setRows(6);
        sserviceAction.setResult(null);
        Assert.assertEquals("success", sserviceAction.getAllInformation());
        Assert.assertEquals(6, ((JSONArray) sserviceAction.getResult().get("rows")).size());
    }

    /**
     * describe：用于增加特别服务信息
     * error 请输入有效姓名
     */
    @Test
    @Transactional
    @Rollback
    public void testAddInformation1() {
        sserviceAction.setEname("1q");
        Assert.assertEquals("error", sserviceAction.addInformation());
    }

    /**
     * describe：用于增加特别服务信息
     * error 该名老人的数据已经存在
     */
    @Test
    @Transactional
    @Rollback
    public void testAddInformation2() {
        sserviceAction.setEname("1");
        sserviceAction.setStype("stype");
        sserviceAction.setStime("stime");
        sserviceAction.setRemark("sremark");
        Assert.assertEquals("error", sserviceAction.addInformation());
    }

    /**
     * describe：用于增加特别服务信息
     * success
     */
    @Test
    @Transactional
    @Rollback
    public void testAddInformation3() {
        sserviceAction.setEname("22");
        sserviceAction.setStype("type");
        sserviceAction.setStime("time");
        sserviceAction.setRemark("remark");
        Assert.assertEquals("success", sserviceAction.addInformation());
    }

    /**
     * describe：更新信息
     * success
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation() {
        sserviceAction.setSsid(1);
        sserviceAction.setStype("type");
        sserviceAction.setStime("time");
        sserviceAction.setRemark("remark");
        Assert.assertEquals("success", sserviceAction.updateInformation());
    }

    /**
     * describe：删除功能
     */
    @Test
    @Transactional
    @Rollback
    public void testRemoveInformation() {
        sserviceAction.setSsid(1);
        Assert.assertEquals("success", sserviceAction.removeInformation());
    }
}
