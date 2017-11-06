package com.ccjjltx.dao;

import com.ccjjltx.domain.Elderlyinformation;
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
 * Created by ccjjltx on 2017/11/5.
 * 测试ElderlyinformationDAO相关功能
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class ElderlyinformationDAOTest {
    @Resource(name = "elderlyinformationDAO")
    private ElderlyinformationDAO elderlyinformationDAO;

    /**
     * 验证：当没有ename的时候是否返回所有的数据
     */
    @Test
    @Transactional
    public void testGetAllInformation1() {
        List<Elderlyinformation> list = elderlyinformationDAO.getAllInformation(0, 20, null);
        //得到实际的大小
        //编写此代码测试的时候数据库的数据为15条
        int result = list.size();
        Assert.assertEquals(15, result);
    }

    /**
     * 验证：当有正确的用户名时候是否返回唯一数据
     */
    @Test
    @Transactional
    public void testGetAllInformation2() {
        List<Elderlyinformation> list = elderlyinformationDAO.getAllInformation(0, 10, "张志新");
        int result = -1;
        for (Elderlyinformation e : list) {
            //得到那条唯一数据的eiId
            result = e.getEiId();
        }
        Assert.assertEquals(1, result);
    }

    /**
     * 验证：当错误用户名的时候是否返回null
     */
    @Test
    @Transactional
    public void testGetAllInformation3() {
        List<Elderlyinformation> list = elderlyinformationDAO.getAllInformation(0, 10, "juncc");
        Assert.assertNull(list);
    }

    /**
     * 验证：当名字为null时候是否返回数据库的总条数
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber1() {
        int result = elderlyinformationDAO.getAllInformationNumber(null);
        Assert.assertEquals(15, result);
    }

    /**
     * 验证：当用户名为正确且唯一,总条数结果是否为1
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber2() {
        int result = elderlyinformationDAO.getAllInformationNumber("张志新");
        Assert.assertEquals(1, result);
    }

    /**
     * 验证：当错误名字时候是否返回0
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber3() {
        int result = elderlyinformationDAO.getAllInformationNumber("ccjccj");
        Assert.assertEquals(0, result);
    }

    /**
     * 验证：getSearchInformation是否能根据主键得到Elderlyinformation实例化
     */
    @Test
    @Transactional
    public void testGetSearchInformation() {
        Elderlyinformation elderlyinformation = elderlyinformationDAO.getSearchInformation(1);
        Assert.assertEquals(1, elderlyinformation.getEiId());
    }

    /**
     * 验证：是否能根据Elderlyinformation实例化与Elder的id正确是是否插入成功
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testAddInformation() {
        try {
            Elderlyinformation ei = new Elderlyinformation("12341234", "1234", "男", MyDateFormat.parse("1995-01-01"), "guangzhou", "guangzhou");
            elderlyinformationDAO.addInformation(ei, 16);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
