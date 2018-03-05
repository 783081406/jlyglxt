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
 * 测试NursingfeeAction方法(对护理费相关操作)
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class NursingfeeActionTest extends StrutsSpringJUnit4TestCase<NursingfeeAction> {
    @Resource(name = "nursingfeeAction")
    private NursingfeeAction nursingfeeAction;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * describe：更新方法
     */
    @Test
    @Transactional
    @Rollback
    public void testGetAllInformation() {
        Assert.assertEquals("success", nursingfeeAction.getAllInformation());
    }

    /**
     * describe： 更新方法
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation() {
        nursingfeeAction.setNcost1(100);
        nursingfeeAction.setNcost2(200);
        nursingfeeAction.setNcost3(300);
        nursingfeeAction.setResult(null);
        Assert.assertEquals("success", nursingfeeAction.updateInformation());
        Assert.assertEquals(true, nursingfeeAction.getResult().get("success"));
    }

}
