package com.ccjjltx.dao;

import com.ccjjltx.domain.Bespeak;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

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

    /**
     * 验证：是否能得到正确未处理数据的条数
     */
    @Test
    @Transactional
    public void testGetAllUnhandleInformationNumber() {
        int result = bespeakDAO.getAllUnhandleInformation(0, 100).size();
        int result2 = bespeakDAO.getAllUnhandleInformationNumber();
        Assert.assertEquals(result, result2);
    }

    /**
     * 验证：是否能根据主键得到实例化
     */
    @Test
    @Transactional
    public void testGetSearchInformation() {
        Bespeak bs = bespeakDAO.getSearchInformation(1);
        Assert.assertEquals(1, bs.getBid());
    }

    /**
     * 验证：是否能修改状态同时添加处理人与处理时间
     */
    @Test
    @Transactional
    @Rollback
    public void testHandleInformation() {
        Bespeak bs = bespeakDAO.getSearchInformation(1);
        //修改为处理状态
        bs.setIshandle(1);
        //添加处理人
        bs.setHandleUser("ccj");
        //添加处理时间
        bs.setHandleDate(new Date());
        bespeakDAO.handleInformation(bs);
    }

    /**
     * 验证：是否能新增
     */
    @Test
    @Transactional
    @Rollback
    public void testSaveInformation() {
        int result = bespeakDAO.getAllUnhandleInformationNumber();
        Bespeak bs = new Bespeak("123", "1234", "1234", 12, new Date());
        bespeakDAO.saveInformation(bs);
        int result2 = bespeakDAO.getAllUnhandleInformationNumber();
        Assert.assertEquals(result + 1, result2);
    }
}
