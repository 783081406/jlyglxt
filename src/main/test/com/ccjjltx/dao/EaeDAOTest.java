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
        //测试该方法的时候该表的数据为15
        int result = eaeDAO.getAllInformation(0, 20, null).size();
        Assert.assertEquals(15, result);
    }

    /**
     * 验证：是否能返回所有的数据(ename不为空时)
     */
    @Test
    @Transactional
    public void testGetAllInformation2() {
        //测试该方法的时候名字为张志新的只有一个
        int result = eaeDAO.getAllInformation(0, 20, "张志新").size();
        Assert.assertEquals(1, result);
    }

    /**
     * 验证：是否返回null(ename不存在时)
     */
    @Test
    @Transactional
    public void testGetAllInformation3() {
        //无此用户名返回null
        List<Eae> result = eaeDAO.getAllInformation(0, 20, "ccj");
        Assert.assertNull(result);
    }

    /**
     * 验证：是否能返回所有的数据总条数(ename为空的时候)
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber1() {
        int result = eaeDAO.getAllInformationNumber(null);
        Assert.assertEquals(15, result);
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
     * 验证：是否能返回所有的数据总条数(ename不存在时候)
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber3() {
        int result = eaeDAO.getAllInformationNumber("ccj");
        Assert.assertEquals(0, result);
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
        Assert.assertFalse(eaeDAO.isExist(21));
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
            int result = eaeDAO.addInformation(eae, 1);
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
            int result = eaeDAO.addInformation(eae, 21);
            Assert.assertEquals(2, result);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
}
