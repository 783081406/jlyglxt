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
 * Created by ccjjltx on 2018/3/6.
 * 测试RoominformationAction方法（房间信息的增删改查）
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class RoominformationActionTest extends StrutsSpringJUnit4TestCase<RoominformationAction> {
    @Resource(name = "roominformationAction")
    private RoominformationAction roominformationAction;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * describe：得到全部或特定(搜索框触发)的房间信息数据
     */
    @Test
    @Transactional
    public void testGetAllInformation() {
        roominformationAction.setPage(1);
        roominformationAction.setRows(2);
        roominformationAction.setEname(null);
        roominformationAction.setResult(null);
        Assert.assertEquals("success", roominformationAction.getAllInformation());
        Assert.assertNotNull(roominformationAction.getResult());
    }

    /**
     * describe：用于增加新的房间信息
     * error 请输入有效姓名
     */
    @Test
    @Transactional
    @Rollback
    public void testAddInformation1() {
        roominformationAction.setEname("qb");
        Assert.assertEquals("error", roominformationAction.addInformation());
    }

    /**
     * describe：用于增加新的房间信息
     * error 已经存在有相同的楼号与房间号
     */
    @Test
    @Transactional
    @Rollback
    public void testAddInformation2() {
        roominformationAction.setEname("1");
        roominformationAction.setFloor("东");
        roominformationAction.setRoomNumber(101);
        roominformationAction.setRType("标准");
        roominformationAction.setRCost(800);
        Assert.assertEquals("error", roominformationAction.addInformation());
    }

    /**
     * describe：用于增加新的房间信息
     * error   该名老人已经入住了
     */
    @Test
    @Transactional
    @Rollback
    public void testAddInformation3() {
        roominformationAction.setEname("2");
        roominformationAction.setFloor("cc");
        roominformationAction.setRoomNumber(110);
        roominformationAction.setRType("豪华");
        roominformationAction.setRCost(1000);
        Assert.assertEquals("error", roominformationAction.addInformation());
    }

    /**
     * describe：用于增加新的房间信息
     * success
     */
    @Test
    @Transactional
    @Rollback
    public void testAddInformation4() {
        roominformationAction.setEname("16");
        roominformationAction.setFloor("ccc");
        roominformationAction.setRoomNumber(10);
        roominformationAction.setRType("豪华");
        roominformationAction.setRCost(2000);
        Assert.assertEquals("success", roominformationAction.addInformation());
    }

    /**
     * describe：更新信息
     * error  该名老人已入住
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation1() {
        roominformationAction.setRId(1);
        roominformationAction.setEname("2");
        roominformationAction.setFloor("cccc");
        roominformationAction.setRoomNumber(100);
        roominformationAction.setRType("豪华");
        roominformationAction.setRCost(2000);
        Assert.assertEquals("error", roominformationAction.updateInformation());
    }

    /**
     * describe：更新信息
     * error   已有相同的楼号与房间号
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation2() {
        roominformationAction.setRId(1);
        roominformationAction.setEname("16");
        roominformationAction.setFloor("东");
        roominformationAction.setRoomNumber(107);
        roominformationAction.setRType("标准");
        roominformationAction.setRCost(800);
        Assert.assertEquals("error", roominformationAction.updateInformation());
    }

    /**
     * describe：更新信息
     * success
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation3() {
        roominformationAction.setRId(1);
        roominformationAction.setEname("qa");
        roominformationAction.setFloor("aaa");
        roominformationAction.setRoomNumber(123);
        roominformationAction.setRType("标准");
        roominformationAction.setRCost(1000);
        Assert.assertEquals("success", roominformationAction.updateInformation());
    }

    /**
     * describe：删除功能
     */
    @Test
    @Transactional
    @Rollback
    public void testRemoveInformation() {
        roominformationAction.setRId(1);
        Assert.assertEquals("success", roominformationAction.removeInformation());
    }

}
