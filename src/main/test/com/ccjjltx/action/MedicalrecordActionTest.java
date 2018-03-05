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
 * 测试MedicalrecordAction方法（就医记录增删查改）
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class MedicalrecordActionTest  extends StrutsSpringJUnit4TestCase<MedicalrecordAction> {
    @Resource(name = "medicalrecordAction")
    private MedicalrecordAction medicalrecordAction;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * describe：得到全部的就医记录
     */
    @Test
    @Transactional
    public void testGetAllInformation() {
        medicalrecordAction.setChId(1);
        medicalrecordAction.setResult(null);
        Assert.assertEquals("success", medicalrecordAction.getAllInformation());
        Assert.assertNotNull(medicalrecordAction.getResult());
    }




}
