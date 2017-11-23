package com.ccjjltx.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
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

    /**
     * 验证：是否可以批量更新
     */
    @Test
    @Transactional
    @Rollback
    public void testreelectInformation() {
        //测试该方法时，数据库最后一条数据的isSelect为1
        int result = interlocutionDAO.getAllInformation(0, 1).get(0).getIsSelect();
        //执行批量更新
        interlocutionDAO.reelectInformation();
        //再次得到该数据
        int result2 = interlocutionDAO.getAllInformation(0, 1).get(0).getIsSelect();
        Assert.assertEquals(result - 1, result2);
    }

    /**
     * 验证：是否能根据主键得到实例化
     */
    @Test
    @Transactional
    public void testGetSearchInformation() {
        int result = interlocutionDAO.getSearchInformation(1).getQaid();
        Assert.assertEquals(1, result);
    }

    /**
     * 验证：是否能根据主键修改isSelect的值
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateIsSelect() {
        //测试该方法时，数据库的qaid为1的数据，isSelect的值为0
        int result = interlocutionDAO.getSearchInformation(1).getQaid();
        int[] data = new int[]{1};
        //执行更新方法
        interlocutionDAO.updateIsSelect(data);
        //再次得到数据
        int result2 = interlocutionDAO.getSearchInformation(1).getQaid();
        Assert.assertEquals(result, result2);
    }
}
