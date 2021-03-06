package com.ccjjltx.dao;

import com.ccjjltx.domain.Casehistory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/11/8.
 * 测试CasehistoryDAO相关的功能
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class CasehistoryDAOTest {
    @Resource(name = "casehistoryDAO")
    private CasehistoryDAO casehistoryDAO;

    /**
     * 验证：ename为null的时候是否返回总条数
     */
    @Test
    @Transactional
    public void testGetAllInformation1() {
        int size = casehistoryDAO.getAllInformationNumber(null);
        List<Casehistory> list = casehistoryDAO.getAllInformation(0, 100, null);
        Assert.assertEquals(size, list.size());
    }

    /**
     * 验证：输入正确的ename时候是否返回准确值
     */
    @Test
    @Transactional
    public void testGetAllInformation2() {
        List<Casehistory> list = casehistoryDAO.getAllInformation(0, 10, "张志新");
        Assert.assertEquals(1, list.size());
    }

    /**
     * 验证：当ename为null时候是否得到总条数
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber1() {
        int result = casehistoryDAO.getAllInformationNumber(null);
        int rr = casehistoryDAO.getAllInformation(0, 100, null).size();
        Assert.assertEquals(rr, result);
    }

    /**
     * 验证：当ename为正确值时候是否得到总条数
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber2() {
        int result = casehistoryDAO.getAllInformationNumber("张志新");
        Assert.assertEquals(1, result);
    }


    /**
     * 验证：是否能根据主键得到实例化
     */
    @Test
    @Transactional
    public void testGetSearchInformation() {
        Casehistory casehistory = casehistoryDAO.getSearchInformation(1);
        Assert.assertEquals(1, casehistory.getChId());
    }

    /**
     * 验证：是否能更新
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation() {
        //得到一个实例化
        Casehistory ch = casehistoryDAO.getSearchInformation(1);
        //修改一个变量
        ch.setHospital("123123");
        //执行更新操作
        casehistoryDAO.updateInformation(ch);
        Assert.assertEquals("123123", casehistoryDAO.getSearchInformation(1).getHospital());
    }

    /**
     * 验证：当存在病历的Elder是否返回true
     */
    @Test
    @Transactional
    public void testIsElder1() {
        boolean result = casehistoryDAO.isElder(1);
        Assert.assertTrue(result);
    }

    /**
     * 验证：当不存在病历的Elder是否返回false
     */
    @Test
    @Transactional
    public void testIsElder2() {
        boolean result = casehistoryDAO.isElder(22);
        Assert.assertFalse(result);
    }

    /**
     * 验证：当Elder存在的时候是否返回false
     */
    @Test
    @Transactional
    @Rollback
    public void testAddInformation1() {
        boolean result = casehistoryDAO.addInformation(null, 1);
        Assert.assertFalse(result);
    }

    /**
     * 验证：当Elder不存在的时候是否返回true
     */
    @Test
    @Transactional
    @Rollback
    public void testAddInformation2() {
        Casehistory ch = new Casehistory("1", "2", "3", "4", "5", "6", "7");
        boolean result = casehistoryDAO.addInformation(ch, 22);
        Assert.assertTrue(result);
    }

    /**
     * 验证：当Elder不存在的时候,是否插入成功
     */
    @Test
    @Transactional
    @Rollback
    public void testAddInformation3() {
        Casehistory ch = new Casehistory("1", "2", "3", "4", "5", "6", "7");
        //得到插入前总数量
        int result1 = casehistoryDAO.getAllInformationNumber(null);
        //执行插入
        casehistoryDAO.addInformation(ch, 16);
        int result2 = casehistoryDAO.getAllInformationNumber(null);
        //理论插入成功result1应该比result2大1
        Assert.assertEquals(result1 + 1, result2);
    }

    /**
     * 验证：是否能将病历删除
     */
    @Test
    @Transactional
    @Rollback
    public void testdeleteInformation1() {
        //得到删除前的数据
        int result1 = casehistoryDAO.getAllInformationNumber(null);
        //执行删除功能
        casehistoryDAO.deleteInformation(1);
        //得到删除后的数据
        int result2 = casehistoryDAO.getAllInformationNumber(null);
        Assert.assertEquals(result1 - 1, result2);
    }
}
