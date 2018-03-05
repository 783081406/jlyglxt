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
 * 测试FamilyinformationAction方法（对家庭信息的相关操作）
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class FamilyinformationActionTest extends StrutsSpringJUnit4TestCase<EinformationAction> {
    @Resource(name = "familyinformationAction")
    private FamilyinformationAction familyinformationAction;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * describe：得到所有的家庭信息
     */
    @Test
    @Transactional
    public void testGetAllInformation() {
        familyinformationAction.setEiId(1);
        familyinformationAction.setResult(null);
        Assert.assertEquals("success", familyinformationAction.getAllInformation());
        Assert.assertNotNull(familyinformationAction.getResult());
    }

    /**
     * describe：增加新信息
     */
    @Test
    @Transactional
    @Rollback
    public void testSaveInformation() {
        familyinformationAction.setEiId(1);
        familyinformationAction.setFamilyName("ccj");
        familyinformationAction.setRelationship("父子");
        familyinformationAction.setFaddress("123456");
        familyinformationAction.setPhone("123");
        Assert.assertEquals("success", familyinformationAction.saveInformation());
    }


    /**
     * describe：更新操作
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation() {
        familyinformationAction.setFId(1);
        familyinformationAction.setFamilyName("ccj");
        familyinformationAction.setRelationship("父子");
        familyinformationAction.setFaddress("123456");
        familyinformationAction.setPhone("123");
        Assert.assertEquals("success", familyinformationAction.updateInformation());
    }

    /**
     * describe：删除功能
     */
    @Test
    @Transactional
    @Rollback
    public void testRemoveInformation() {
        familyinformationAction.setFId(1);
        Assert.assertEquals("success", familyinformationAction.removeInformation());
    }

}
