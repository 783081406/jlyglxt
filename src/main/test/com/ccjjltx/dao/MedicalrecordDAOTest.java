package com.ccjjltx.dao;

import com.ccjjltx.domain.Casehistory;
import com.ccjjltx.domain.Medicalrecord;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by ccjjltx on 2017/11/8.
 * 测试MedicalrecordDAO相关功能
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class MedicalrecordDAOTest {
    @Resource(name = "medicalrecordDAO")
    private MedicalrecordDAO medicalrecordDAO;
    @Resource(name = "casehistoryDAO")
    private CasehistoryDAO casehistoryDAO;

    /**
     * 验证：是否能根据chId得到全部的就医记录实例化
     */
    @Test
    @Transactional
    public void testGetAllInformation() {
        int result = medicalrecordDAO.getAllInformation(1).size();
        //测试的时候数据库chId为1的就医记录数据为2
        Assert.assertEquals(2, result);
    }

    /**
     * 验证：是否能增加就医记录
     */
    @Test
    @Transactional
    @Rollback
    public void testAddInformation() {
        //得到chId为1的数据量
        int result1 = medicalrecordDAO.getAllInformation(1).size();
        Medicalrecord ec = new Medicalrecord("1", "1", "1", "1");
        Casehistory ch = casehistoryDAO.getSearchInformation(1);
        ec.setCasehistory(ch);
        //插入数据操作
        medicalrecordDAO.addInformation(ec);
        //再次得到chId为1的数据量
        int result2 = medicalrecordDAO.getAllInformation(1).size();
        Assert.assertEquals(result1 + 1, result2);
    }

    /**
     * 验证：是否能根据主键得到实例化
     */
    @Test
    @Transactional
    public void testGetSearchEinformation() {
        //得到实例化
        Medicalrecord mc = medicalrecordDAO.getSearchEinformation(1);
        //第一条数据的chId为1
        Assert.assertEquals(1, mc.getCasehistory().getChId());
    }

    /**
     * 验证：是否能更新
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation() {
        //得到一个实例化
        Medicalrecord mc = medicalrecordDAO.getSearchEinformation(1);
        //更新一条数据
        mc.setAdvice("123");
        //执行更新
        medicalrecordDAO.updateInformation(mc);
        //再次得到该实例化
        Medicalrecord mc2 = medicalrecordDAO.getSearchEinformation(1);
        //断言
        Assert.assertEquals("123", mc2.getAdvice());
    }

}
