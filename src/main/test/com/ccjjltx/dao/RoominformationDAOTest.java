package com.ccjjltx.dao;

import com.ccjjltx.domain.Roominformation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/10/29.
 * 测试RoominformationDAO相关的功能
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class RoominformationDAOTest {

    @Resource(name = "roominformationDAO")
    private RoominformationDAO roominformationDAO;

    /**
     * 验证:无ename的时候是否返回正确的信息
     */
    @Test
    @Transactional
    public void testGetAllInformation1() {
        List<Roominformation> list = roominformationDAO.getAllInformation(0, 1, null);
        int rid = 0;
        for (Roominformation roominformation : list) {
            rid = roominformation.getRId();
        }
        Assert.assertEquals(1, rid);
    }

    /**
     * 验证:错误ename的时候是否返回null
     */
    @Test
    @Transactional
    public void testGetAllInformation2() {
        List<Roominformation> list = roominformationDAO.getAllInformation(0, 1, "123456");
        Assert.assertNull(list);
    }

    /**
     * 验证:无ename的时候是否返回正确的信息
     */
    @Test
    @Transactional
    public void testGetAllInformation3() {
        List<Roominformation> list = roominformationDAO.getAllInformation(0, 1, "张志新");
        int rid = 0;
        for (Roominformation roominformation : list) {
            rid = roominformation.getRId();
        }
        Assert.assertEquals(1, rid);
    }

    /**
     * 验证：无enmae的是否是否返回正确的总条数
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber1() {
        int result = roominformationDAO.getAllInformationNumber(null);
        Assert.assertEquals(32, result);
    }

    /**
     * 验证：无enmae的是否是否返回正确的总条数
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber2() {
        int result = roominformationDAO.getAllInformationNumber("张志新");
        Assert.assertEquals(1, result);
    }

}
