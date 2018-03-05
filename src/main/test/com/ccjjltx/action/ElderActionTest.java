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
 * 测试ElderAction方法（下拉框选择老人名字的时候使用）
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class ElderActionTest extends StrutsSpringJUnit4TestCase<ElderAction> {
    @Resource(name = "elderAction")
    private ElderAction elderAction;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * describe：
     * 下拉框选择用户帐户使用
     * 场景：信息中心->房间信息
     */
    @Test
    @Transactional
    public void testGetIdElderName() {
        elderAction.setResult(null);
        Assert.assertEquals("success", elderAction.getIdElderName());
        Assert.assertNotNull(elderAction.getResult());
    }
}
