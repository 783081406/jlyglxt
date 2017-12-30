package com.ccjjltx.dao;

import com.ccjjltx.domain.Sservice;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/12/30.
 * 测试SserviceDAO相关的功能
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class SserviceDAOTest {
    @Resource(name = "sserviceDAO")
    private SserviceDAO sserviceDAO;

    /**
     * 验证：是否能返回所有的数据(ename为空的时候)
     */
    @Test
    @Transactional
    public void testGetAllInformation1() {
        //测试该方法的时候该表的数据为11
        int result = sserviceDAO.getAllInformation(0, 20, null).size();
        Assert.assertEquals(11, result);
    }

    /**
     * 验证：是否能返回所有的数据(ename不为空时)
     */
    @Test
    @Transactional
    public void testGetAllInformation2() {
        //测试该方法的时候名字为张志新的只有一个
        int result = sserviceDAO.getAllInformation(0, 20, "张志新").size();
        Assert.assertEquals(1, result);
    }

    /**
     * 验证：是否返回null(ename不存在时)
     */
    @Test
    @Transactional
    public void testGetAllInformation3() {
        //无此用户名返回null
        List<Sservice> result = sserviceDAO.getAllInformation(0, 20, "ccj");
        Assert.assertNull(result);
    }

    /**
     * 验证：是否能返回所有的数据总条数(ename为空的时候)
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber1() {
        int result = sserviceDAO.getAllInformationNumber(null);
        Assert.assertEquals(11, result);
    }

    /**
     * 验证：是否能返回所有的数据总条数(ename不为空的时候)
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber2() {
        int result = sserviceDAO.getAllInformationNumber("张志新");
        Assert.assertEquals(1, result);
    }

    /**
     * 验证：是否能返回所有的数据总条数(ename不存在时候)
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber3() {
        int result = sserviceDAO.getAllInformationNumber("ccj");
        Assert.assertEquals(0, result);
    }

}
