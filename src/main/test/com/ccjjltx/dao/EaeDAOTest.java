package com.ccjjltx.dao;

import com.ccjjltx.domain.Eae;
import com.ccjjltx.utils.MyDateFormat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

/**
 * Created by ccjjltx on 2017/12/31.
 * 测试EaeDAO相关的功能
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class EaeDAOTest {
    @Resource(name = "eaeDAO")
    private EaeDAO eaeDAO;

    /**
     * 验证：是否能返回所有的数据(ename为空的时候)
     */
    @Test
    @Transactional
    public void testGetAllInformation1() {
        int size = eaeDAO.getAllInformationNumber(null);
        int result = eaeDAO.getAllInformation(0, 100, null).size();
        Assert.assertEquals(size, result);
    }

    /**
     * 验证：是否能返回所有的数据(ename不为空时)
     */
    @Test
    @Transactional
    public void testGetAllInformation2() {
        //测试该方法的时候名字为张志新的只有一个
        int result = eaeDAO.getAllInformation(0, 100, "张志新").size();
        Assert.assertEquals(1, result);
    }

    /**
     * 验证：是否能返回所有的数据总条数(ename为空的时候)
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber1() {
        int size = eaeDAO.getAllInformation(0, 100, null).size();
        int result = eaeDAO.getAllInformationNumber(null);
        Assert.assertEquals(size, result);
    }

    /**
     * 验证：是否能返回所有的数据总条数(ename不为空的时候)
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber2() {
        int result = eaeDAO.getAllInformationNumber("张志新");
        Assert.assertEquals(1, result);
    }

    /**
     * 验证：增加信息（存在的数据，返回1）
     */
    @Test
    @Transactional
    @Rollback
    public void testAddInformation1() {
        try {
            Eae eae = new Eae(MyDateFormat.parse("2017-12-31"), null);
            int result = eaeDAO.addInformation(eae, 1, 1);
            Assert.assertEquals(1, result);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证：增加信息（不存在的数据，返回2）
     */
    @Test
    @Transactional
    @Rollback
    public void testAddInformation2() {
        try {
            Eae eae = new Eae(MyDateFormat.parse("2017-12-31"), null);
            int result = eaeDAO.addInformation(eae, 22, 1);
            Assert.assertEquals(2, result);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证：当用户存在表数据时，是否true
     */
    @Test
    @Transactional
    public void testIsExist1() {
        Assert.assertTrue(eaeDAO.isExist(1));
    }

    /**
     * 验证：当用户不存在表数据时，是否false
     */
    @Test
    @Transactional
    public void testIsExist2() {
        Assert.assertFalse(eaeDAO.isExist(22));
    }

    /**
     * 验证：是否能根据主键得到实例化
     */
    @Test
    @Transactional
    public void testGetSearchEae() {
        Eae eae = eaeDAO.getSearchEae(1);
        Assert.assertEquals(1, eae.getElder().getEId());
    }

    /**
     * 验证：是否能更新信息
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation() {
        try {
            Eae eae = eaeDAO.getSearchEae(1);
            eae.setStime(MyDateFormat.parse("2017-11-11"));//重新更新字段信息
            eaeDAO.updateInformation(eae);//更新
            //重新获取数据，比较更新的字段
            Assert.assertEquals("2017-11-11", MyDateFormat.format(eaeDAO.getSearchEae(1).getStime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证：是否能删除数据
     */
    @Test
    @Transactional
    @Rollback
    public void testDeleteInformation() {
        int count1 = eaeDAO.getAllInformationNumber(null);
        eaeDAO.deleteInformation(1);//删除数据
        int count2 = eaeDAO.getAllInformationNumber(null);
        Assert.assertEquals(count2, count1 - 1);
    }
}
