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
        //当时数据库只有十五条数据
        List<Casehistory> list = casehistoryDAO.getAllInformation(0, 20, null);
        Assert.assertEquals(15, list.size());
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
     * 验证：假ename时候，是否返回null
     */
    @Test
    @Transactional
    public void testGetAllInformation3() {
        List<Casehistory> list = casehistoryDAO.getAllInformation(0, 10, "ccj");
        Assert.assertNull(list);
    }

    /**
     * 验证：当ename为null时候是否得到总条数
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber1() {
        int result = casehistoryDAO.getAllInformationNumber(null);
        Assert.assertEquals(15, result);
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
}
