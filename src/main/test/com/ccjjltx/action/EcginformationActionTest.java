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
 * 测试EcginformationAction方法（心电信息增删改查）
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class EcginformationActionTest extends StrutsSpringJUnit4TestCase<EcginformationAction> {
    @Resource(name = "ecginformationAction")
    private EcginformationAction ecginformationAction;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * describe：得到全部心电信息
     */
    @Test
    @Transactional
    public void testGetAllInformation() {
        ecginformationAction.setChId(1);
        ecginformationAction.setResult(null);
        Assert.assertEquals("success", ecginformationAction.getAllInformation());
        Assert.assertNotNull(ecginformationAction.getResult());
    }

    /**
     * describe：增加心电信息
     */
    @Test
    @Transactional
    @Rollback
    public void testSaveInformation() {
        try {
            ecginformationAction.setChId(1);
            ecginformationAction.setQrs("234");
            ecginformationAction.setComment("34");
            ecginformationAction.setRr("34");
            ecginformationAction.setAnalysisResult("234");
            ecginformationAction.setRhythm("234");
            ecginformationAction.setRdate(MyDateFormat.parse("2017-20-12"));
            ecginformationAction.setResult(null);
            Assert.assertEquals("success", ecginformationAction.saveInformation());
            Assert.assertEquals(true, ecginformationAction.getResult().get("success"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * describe：更新信息
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation() {
        try {
            ecginformationAction.setEcgId(1);
            ecginformationAction.setQrs("1");
            ecginformationAction.setComment("1");
            ecginformationAction.setRr("1");
            ecginformationAction.setAnalysisResult("1");
            ecginformationAction.setRdate(MyDateFormat.parse("2017-12-12"));
            Assert.assertEquals("success", ecginformationAction.updateInformation());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * describe：删除功能
     */
    @Test
    @Transactional
    @Rollback
    public void testRemoveInformation() {
        ecginformationAction.setEcgId(1);
        Assert.assertEquals("success", ecginformationAction.removeInformation());
    }
}
