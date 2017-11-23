package com.ccjjltx.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by ccjjltx on 2017/11/23.
 * 测试InterlocutionDAO相关功能
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class InterlocutionDAOTest {
    @Resource(name = "interlocutionDAO")
    private InterlocutionDAO interlocutionDAO;

    /**
     * 验证：是否得到全部的数据
     */
    @Test
    @Transactional
    public void testGetAllInformation() {
        //测试该方法时数据库的数据为16
        int result = interlocutionDAO.getAllInformation(0, 20).size();
        Assert.assertEquals(16, result);
    }

    /**
     * 验证：是否得到总条数
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber() {
        int result = interlocutionDAO.getAllInformation(0, 30).size();
        int result2 = interlocutionDAO.getAllInformationNumber();
        Assert.assertEquals(result, result2);
    }

}
