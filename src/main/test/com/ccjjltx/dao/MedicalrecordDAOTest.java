package com.ccjjltx.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
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
    public void getAllInformation() {
        int result = medicalrecordDAO.getAllInformation(1).size();
        //测试的时候数据库chId为1的就医记录数据为2
        Assert.assertEquals(2, result);
    }
}
