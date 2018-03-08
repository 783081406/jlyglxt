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
 * Created by ccjjltx on 2018/3/5.
 * 测试CasehistoryAction方法（病例增删改查）
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class CasehistoryActionTest extends StrutsSpringJUnit4TestCase<CasehistoryAction> {
    @Resource(name = "casehistoryAction")
    private CasehistoryAction casehistoryAction;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * describe：得到全部或者特定的（搜索框触发）的信息
     */
    @Test
    @Transactional
    public void testGetAllInformation() {
        casehistoryAction.setPage(1);
        casehistoryAction.setRows(2);
        casehistoryAction.setResult(null);
        Assert.assertEquals("success", casehistoryAction.getAllInformation());
        Assert.assertEquals(2, ((JSONArray) casehistoryAction.getResult().get("rows")).size());
    }


    /**
     * describe：返回单人所有的数据
     */
    @Test
    @Transactional
    public void testGetAllInformation2() {
        casehistoryAction.setChId(1);
        Assert.assertEquals("success", casehistoryAction.getAllInformation2());
    }

    /**
     * describe：更新信息
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation() {
        casehistoryAction.setChId(1);//需要修改的实例化id
        //基础特征
        casehistoryAction.setHeight("123.23");
        casehistoryAction.setWeight("123.23");
        casehistoryAction.setPhysician("ccj");
        casehistoryAction.setHospital("cc_hospital");
        casehistoryAction.setHospitalPhone("123456789123");
        casehistoryAction.setAllergyDrugs("无");
        casehistoryAction.setMajorDiseases(null);
        //血氧血压
        casehistoryAction.setHighHanded("11");
        casehistoryAction.setLowHanded("11");
        casehistoryAction.setBloodOxygenValue("11");
        //血糖信息
        casehistoryAction.setFastingPlasmaGlucose("11");
        casehistoryAction.setPostprandialBoolGlucose("12");
        //血脂信息
        casehistoryAction.setTotalCholesterol("11");
        casehistoryAction.setTriglyceride("11");
        casehistoryAction.setHdlc("11");
        casehistoryAction.setLdlc("11");
        Assert.assertEquals("success", casehistoryAction.updateInformation());
    }

    /**
     * describe：增加病历
     * success
     */
    @Test
    @Transactional
    @Rollback
    public void testSaveInformation1() {
        casehistoryAction.setEname("16");
        //基础特征
        casehistoryAction.setHeight("123.23");
        casehistoryAction.setWeight("123.23");
        casehistoryAction.setPhysician("ccj");
        casehistoryAction.setHospital("cc_hospital");
        casehistoryAction.setHospitalPhone("123456789123");
        casehistoryAction.setAllergyDrugs("无");
        casehistoryAction.setMajorDiseases("123");
        //血氧血压
        casehistoryAction.setHighHanded("11");
        casehistoryAction.setLowHanded("11");
        casehistoryAction.setBloodOxygenValue("11");
        //血糖信息
        casehistoryAction.setFastingPlasmaGlucose("11");
        casehistoryAction.setPostprandialBoolGlucose("12");
        //血脂信息
        casehistoryAction.setTotalCholesterol("11");
        casehistoryAction.setTriglyceride("11");
        casehistoryAction.setHdlc("11");
        casehistoryAction.setLdlc("11");
        Assert.assertEquals("success", casehistoryAction.saveInformation());
    }

    /**
     * describe：增加病历
     * error
     */
    @Test
    @Transactional
    @Rollback
    public void testSaveInformation2() {
        casehistoryAction.setEname("aq");
        Assert.assertEquals("error", casehistoryAction.saveInformation());
    }

    /**
     * describe：增加病历
     * error
     */
    @Test
    @Transactional
    @Rollback
    public void testSaveInformation3() {
        casehistoryAction.setEname("1");
        Assert.assertEquals("error", casehistoryAction.saveInformation());
    }

    /**
     * describe：删除功能
     */
    @Test
    @Transactional
    @Rollback
    public void testRemoveInformation() {
        casehistoryAction.setChId(1);
        Assert.assertEquals("success", casehistoryAction.removeInformation());
    }

}
