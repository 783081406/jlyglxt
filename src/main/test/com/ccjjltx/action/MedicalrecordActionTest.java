package com.ccjjltx.action;

import com.ccjjltx.utils.MyDateFormat;
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
import java.text.ParseException;

/**
 * Created by ccjjltx on 2018/3/5.
 * 测试MedicalrecordAction方法（就医记录增删查改）
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class MedicalrecordActionTest extends StrutsSpringJUnit4TestCase<MedicalrecordAction> {
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

    /**
     * describe：增加就医记录
     */
    @Test
    @Transactional
    @Rollback
    public void testSaveInformation() {
        medicalrecordAction.setChId(1);
        medicalrecordAction.setMrpPlace("123");
        medicalrecordAction.setMedicalDoctor("123");
        medicalrecordAction.setDiagnosisResult("123");
        medicalrecordAction.setAdvice("134");
        medicalrecordAction.setResult(null);
        Assert.assertEquals("success", medicalrecordAction.saveInformation());
        Assert.assertEquals(true, medicalrecordAction.getResult().get("success"));
    }

    /**
     * describe：更新操作
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation() {
        medicalrecordAction.setMrId(1);
        medicalrecordAction.setMrpPlace("123");
        medicalrecordAction.setMedicalDoctor("123");
        medicalrecordAction.setDiagnosisResult("123");
        medicalrecordAction.setAdvice("134");
        Assert.assertEquals("success", medicalrecordAction.updateInformation());
    }

    /**
     * describe：删除功能
     */
    @Test
    @Transactional
    @Rollback
    public void testRemoveInformation() {
        medicalrecordAction.setMrId(1);
        Assert.assertEquals("success", medicalrecordAction.removeInformation());
    }
}
