package com.ccjjltx.dao;

import com.ccjjltx.domain.Interlocution;
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
        int size = interlocutionDAO.getAllInformationNumber();
        int result = interlocutionDAO.getAllInformation(0, 100).size();
        Assert.assertEquals(size, result);
    }

    /**
     * 验证：是否得到总条数
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber() {
        int size = interlocutionDAO.getAllInformation(0, 100).size();
        int result = interlocutionDAO.getAllInformationNumber();
        Assert.assertEquals(size, result);
    }

    /**
     * 验证：是否可以批量更新
     */
    @Test
    @Transactional
    @Rollback
    public void testreelectInformation() {
        int size1 = interlocutionDAO.getSelectInformation().size();
        Assert.assertTrue(size1 > 0);//基本保持八条选中数据
        interlocutionDAO.reelectInformation();//执行重选
        int size2 = interlocutionDAO.getSelectInformation().size();//再次得到位0条
        Assert.assertTrue(size2 == 0);
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

    /**
     * 验证：是否能新增数据
     */
    @Test
    @Transactional
    @Rollback
    public void testAddInformation() {
        //未插入前数据量
        int result = interlocutionDAO.getAllInformationNumber();
        Interlocution il = new Interlocution("1234", "123");
        //执行插入
        interlocutionDAO.addInformation(il);
        //执行插入之后再次得到数据量
        int result2 = interlocutionDAO.getAllInformationNumber();
        Assert.assertEquals(result + 1, result2);
    }

    /**
     * 验证：是否能更新数据
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation() {
        Interlocution il = interlocutionDAO.getSearchInformation(1);
        il.setQuestion("123");
        interlocutionDAO.updateInformation(il);
        Assert.assertEquals("123", interlocutionDAO.getSearchInformation(1).getQuestion());
    }

    /**
     * 验证：是否删除数据
     */
    @Test
    @Transactional
    @Rollback
    public void testRemoveInformation() {
        //未删除之前数据量
        int result = interlocutionDAO.getAllInformationNumber();
        interlocutionDAO.removeInformation(1);
        //删除之后的数据量
        int result2 = interlocutionDAO.getAllInformationNumber();
        Assert.assertEquals(result - 1, result2);
    }

    /**
     * 验证：是否能得到所有选中的数据
     */
    @Test
    @Transactional
    public void testGetSelectInformation() {
        //测试该方法时，数据库数据选中的量为8
        int result = interlocutionDAO.getSelectInformation().size();
        Assert.assertEquals(8, result);
    }
}
