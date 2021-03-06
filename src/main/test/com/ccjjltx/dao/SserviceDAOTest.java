package com.ccjjltx.dao;

import com.ccjjltx.domain.Sservice;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
        int size = sserviceDAO.getAllInformationNumber(null);
        int result = sserviceDAO.getAllInformation(0, 100, null).size();
        Assert.assertEquals(size, result);
    }

    /**
     * 验证：是否能返回所有的数据(ename不为空时)
     */
    @Test
    @Transactional
    public void testGetAllInformation2() {
        int size = sserviceDAO.getAllInformationNumber("张志新");
        int result = sserviceDAO.getAllInformation(0, 100, "张志新").size();
        Assert.assertEquals(size, result);
    }

    /**
     * 验证：是否能返回所有的数据总条数(ename为空的时候)
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber1() {
        int size = sserviceDAO.getAllInformation(0, 100, null).size();
        int result = sserviceDAO.getAllInformationNumber(null);
        Assert.assertEquals(size, result);
    }

    /**
     * 验证：是否能返回所有的数据总条数(ename不为空的时候)
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber2() {
        int size = sserviceDAO.getAllInformation(0, 100, "张志新").size();
        int result = sserviceDAO.getAllInformationNumber("张志新");
        Assert.assertEquals(size, result);
    }

    /**
     * 验证：当用户存在表数据时，是否true
     */
    @Test
    @Transactional
    public void testIsExist1() {
        Assert.assertTrue(sserviceDAO.isExist(1));
    }

    /**
     * 验证：当用户不存在表数据时，是否false
     */
    @Test
    @Transactional
    public void testIsExist2() {
        Assert.assertFalse(sserviceDAO.isExist(22));
    }

    /**
     * 验证：增加信息（存在的数据，返回1）
     */
    @Test
    @Transactional
    @Rollback
    public void testAddInformation1() {
        Sservice sservice = new Sservice("cc", "cc", "cc");
        int result = sserviceDAO.addInformation(sservice, 1);
        Assert.assertEquals(1, result);
    }

    /**
     * 验证：增加信息（不存在的数据，返回2）
     */
    @Test
    @Transactional
    @Rollback
    public void testAddInformation2() {
        Sservice sservice = new Sservice("cc", "cc", "cc");
        int result = sserviceDAO.addInformation(sservice, 22);
        Assert.assertEquals(2, result);
    }

    /**
     * 验证：是否能更新信息
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation() {
        Sservice sservice = sserviceDAO.getSearchSservice(1);
        //重新更新字段信息
        sservice.setRemark("123");
        //更新
        sserviceDAO.updateInformation(sservice);
        //重新获取数据，比较更新的字段
        Assert.assertEquals("123", sserviceDAO.getSearchSservice(1).getRemark());
    }

    /**
     * 验证：是否能根据主键得到实例化
     */
    @Test
    @Transactional
    public void testGetSearchSservice() {
        Sservice sservice = sserviceDAO.getSearchSservice(1);
        Assert.assertEquals(1, sservice.getElder().getEId());
    }

    /**
     * 验证：是否能删除数据
     */
    @Test
    @Transactional
    @Rollback
    public void testDeleteInformation() {
        int count1 = sserviceDAO.getAllInformationNumber(null);
        //删除数据
        sserviceDAO.deleteInformation(1);
        int count2 = sserviceDAO.getAllInformationNumber(null);
        Assert.assertEquals(count2, count1 - 1);
    }
}
