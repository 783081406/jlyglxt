package com.ccjjltx.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by ccjjltx on 2017/11/22.
 * 测试BespeakDAO相关的功能
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class BespeakDAOTest {
    @Resource(name = "bespeakDAO")
    private BespeakDAO bespeakDAO;

    /**
     * 验证：是否能返回所有已经处理的数据
     */
    @Test
    @Transactional
    public void testGetAllHandleInformation() {
        //测试该方法时，数据库的数据已处理为11
        int result = bespeakDAO.getAllHandleInformation(0, 20).size();
        Assert.assertEquals(11, result);
    }

    /**
     * 验证：是否能得到正确已处理数据的条数
     */
    @Test
    @Transactional
    public void testGetAllHandleInformationNumber() {
        int result = bespeakDAO.getAllHandleInformation(0, 100).size();
        int result2 = bespeakDAO.getAllHandleInformationNumber();
        Assert.assertEquals(result, result2);
    }

    /**
     * 验证：是否能返回所有已经处理的数据
     */
    @Test
    @Transactional
    public void testGetAllUnhandleInformation() {
        //测试该方法时，数据库的数据已处理为12
        int result = bespeakDAO.getAllUnhandleInformation(0, 20).size();
        Assert.assertEquals(12, result);
    }
}
