package com.ccjjltx.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by ccjjltx on 2017/11/20.
 * 测试BillboardsDAO相关的功能
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class BillboardsDAOTest {
    @Resource(name = "billboardsDAO")
    private BillboardsDAO billboardsDAO;

    /**
     * 验证：是否能返回所有的数据
     */
    @Test
    @Transactional
    public void testGetAllInformation() {
        //测试时候数据库的数据为10条
        int result = billboardsDAO.getAllInformation(0, 10).size();
        Assert.assertEquals(10, result);
    }

    /**
     * 验证：测试是否能得到总条数
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber() {
        //测试的术后数据库的数据为12条
        int result = billboardsDAO.getAllInformationNumber();
        Assert.assertEquals(12, result);
    }
}
