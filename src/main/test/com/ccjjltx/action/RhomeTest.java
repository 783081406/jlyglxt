package com.ccjjltx.action;

import org.apache.struts2.StrutsSpringJUnit4TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by ccjjltx on 2018/3/5.
 * 测试Rhome方法（前端页面汇总）
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class RhomeTest extends StrutsSpringJUnit4TestCase<Rhome> {
    @Resource(name = "rhome")
    private Rhome rhome;

    /**
     * describe：宣传栏
     */
    @Test
    @Transactional
    public void testIndex() {
        Assert.assertEquals("success", rhome.index());
        Assert.assertNotNull(rhome.getListBillboards());
        Assert.assertNotNull(rhome.getLbSize());
        Assert.assertNotNull(rhome);
        Assert.assertNotNull(rhome.getListServiceitems());
    }

    /**
     * describe：环境概括
     */
    @Test
    @Transactional
    public void testEnvironment() {
        Assert.assertEquals("success", rhome.environment());
        Assert.assertNotNull(rhome.getListBillboards().size());
        Assert.assertNotNull(rhome.getLbSize());
    }

    /**
     * describe：问题与回答
     */
    @Test
    @Transactional
    public void testQa() {
        Assert.assertEquals("success", rhome.qa());
        Assert.assertNotNull(rhome.getListInterlocution());
    }

    /**
     * describe：联系我们
     */
    @Test
    @Transactional
    public void tsetContact() {
        Assert.assertEquals("success", rhome.contact());
    }

    /**
     * describe：花费
     */
    @Test
    @Transactional
    public void tsetCost() {
        Assert.assertEquals("success", rhome.cost());
        //入住费用
        Assert.assertNotNull(rhome.getRoomcost1());
        Assert.assertNotNull(rhome.getRoomcost2());
        Assert.assertNotNull(rhome.getRoomcost3());
        //伙食费
        Assert.assertNotNull(rhome.getMinimum());
        Assert.assertNotNull(rhome.getHighest());
        //护理费
        Assert.assertNotNull(rhome.getNursingfee1());
        Assert.assertNotNull(rhome.getNursingfee2());
        Assert.assertNotNull(rhome.getNursingfee3());
        //合计
        Assert.assertNotNull(rhome.getCtotal11());
        Assert.assertNotNull(rhome.getCtotal12());
        Assert.assertNotNull(rhome.getCtotal21());
        Assert.assertNotNull(rhome.getCtotal22());
        Assert.assertNotNull(rhome.getCtotal31());
        Assert.assertNotNull(rhome.getCtotal32());
    }
}
