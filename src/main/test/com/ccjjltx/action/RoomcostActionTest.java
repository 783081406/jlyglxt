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
 * 测试RoomcostAction方法（入住费用的增删改查）
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class RoomcostActionTest extends StrutsSpringJUnit4TestCase<RoomcostAction> {
    @Resource(name = "roomcostAction")
    private RoomcostAction roomcostAction;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * describe：更新方法
     */
    @Test
    @Transactional
    public void testGetAllInformation() {
        Assert.assertEquals("success", roomcostAction.getAllInformation());
    }

    /**
     * describe： 更新信息
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation() {
        roomcostAction.setResult(null);
        roomcostAction.setRtype1(1);
        roomcostAction.setRtype2(2);
        roomcostAction.setRtype3(3);
        Assert.assertEquals("success", roomcostAction.updateInformation());
        Assert.assertEquals(true, roomcostAction.getResult().get("success"));
    }

}
