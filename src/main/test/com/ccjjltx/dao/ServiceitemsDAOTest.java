package com.ccjjltx.dao;

import com.ccjjltx.domain.Serviceitems;
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
        int size = serviceitemsDAO.getAllInformationNumber();
        int result = serviceitemsDAO.getAllInformation(0, 100).size();
        Assert.assertEquals(size, result);
    }

    /**
     * 验证是否能返回得到的总条数
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber() {
        int size = serviceitemsDAO.getAllInformation(0, 100).size();
        int result = serviceitemsDAO.getAllInformationNumber();
        Assert.assertEquals(size, result);
    }

    /**
     * 验证：是否所有的值都改为0
     */
    @Test
    @Transactional
    @Rollback
    public void testReelectInformation() {
        List<Serviceitems> list = serviceitemsDAO.getAllInformation(0, 90);//得到所有的值
        int old_record = 0;
        for (Serviceitems bb : list) {
            if (bb.getIsSelect() == 1) {
                ++old_record;
            }
        }
        Assert.assertTrue(old_record > 0);//断言，选中的数据大于0
        //执行重选
        serviceitemsDAO.reelectInformation();
        List<Serviceitems> list2 = serviceitemsDAO.getAllInformation(0, 90);//再次得到所有的数据
        int new_record = 0;
        for (Serviceitems bb : list2) {
            if (bb.getIsSelect() == 1) {
                ++new_record;
            }
        }
        Assert.assertTrue(new_record == 0);//断言，无选中数据
    }

    /**
     * 验证：是否能根据主键得到实例化
     */
    @Test
    @Transactional
    public void testGetSearchInformation() {
        //得到实例化
        Serviceitems si = serviceitemsDAO.getSearchInformation(1);
        Assert.assertEquals(1, si.getSid());
    }

    /**
     * 验证：是否能根据主键修改isSelect的值
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateIsSelect() {
        //测试该方法时，数据库的sid为1的数据，isSelected为0,
        int result1 = serviceitemsDAO.getSearchInformation(1).getIsSelect();
        int[] data = new int[]{1};
        //执行更新方法
        serviceitemsDAO.updateIsSelect(data);
        //执行上面更新操作之后，isSelect为1
        int result2 = serviceitemsDAO.getSearchInformation(1).getIsSelect();
        Assert.assertEquals(result1 + 1, result2);
    }

    /**
     * 验证：是否能新增
     */
    @Test
    @Transactional
    @Rollback
    public void testAddInformation() {
        int result1 = serviceitemsDAO.getAllInformationNumber();
        Serviceitems si = new Serviceitems("f1.jpg", "1234", "1234");
        //执行插入数据操作
        serviceitemsDAO.addInformation(si);
        int result2 = serviceitemsDAO.getAllInformationNumber();//未插入前的数据量
        Assert.assertEquals(result1 + 1, result2);
    }

    /**
     * 验证：是否能更新
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation() {
        //得到一个实例化
        Serviceitems si = serviceitemsDAO.getSearchInformation(1);
        si.setScontent("1234");
        serviceitemsDAO.updateInformation(si);
        Assert.assertEquals("1234", serviceitemsDAO.getSearchInformation(1).getScontent());
    }

    /**
     * 验证：是否能删除数据
     */
    @Test
    @Transactional
    @Rollback
    public void testRemoveInformation() {
        int result1 = serviceitemsDAO.getAllInformationNumber();
        //执行删除
        serviceitemsDAO.removeInformation(1);
        int result2 = serviceitemsDAO.getAllInformationNumber();
        Assert.assertEquals(result1 - 1, result2);
    }

    /**
     * 验证：是否得到所有selected的值
     */
    @Test
    @Transactional
    @Rollback
    public void testGetSelectInformation() {
        //测试该数据时，数据库的数据为1的条数为4
        int result = serviceitemsDAO.getSelectInformation().size();
        Assert.assertEquals(4, result);
    }
}
