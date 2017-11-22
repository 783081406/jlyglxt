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
 * 测试Serviceitems相关的功能
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class ServiceitemsDAOTest {
    @Resource(name = "serviceitemsDAO")
    private ServiceitemsDAO serviceitemsDAO;

    /**
     * 验证：是否能返回所有的数据
     */
    @Test
    @Transactional
    public void testGetAllInformation() {
        //测试时候数据库的数据为12条
        int result = serviceitemsDAO.getAllInformation(0, 12).size();
        Assert.assertEquals(12, result);
    }

    /**
     * 验证是否能返回得到的总条数
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber() {
        //测试的时候数据库的数据为12条
        int result = serviceitemsDAO.getAllInformationNumber();
        Assert.assertEquals(12, result);
    }



}
