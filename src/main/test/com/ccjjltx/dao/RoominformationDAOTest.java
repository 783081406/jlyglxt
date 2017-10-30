package com.ccjjltx.dao;

import com.ccjjltx.domain.Roominformation;
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

    /**
     * 验证：当用楼号和房间号都存在的时候是否返回true；
     */
    @Test
    @Transactional
    public void testIsFloorAndRoomNumber1() {
        boolean result = roominformationDAO.isFloorAndRoomNumber("东", 101);
        Assert.assertTrue(result);
    }

    /**
     * 验证：当楼号是新的，房间号是存在的是否返回false；
     */
    @Test
    @Transactional
    public void testIsFloorAndRoomNumber2() {
        boolean result = roominformationDAO.isFloorAndRoomNumber("清风阁", 101);
        Assert.assertFalse(result);
    }

    /**
     * 验证：当楼号是存在的，房间号是不存在的是否返回false；
     */
    @Test
    @Transactional
    public void testIsFloorAndRoomNumber3() {
        boolean result = roominformationDAO.isFloorAndRoomNumber("东", 120);
        Assert.assertFalse(result);
    }

    /**
     * 验证：当楼号与房间号都是不存在的是否返回false；
     */
    @Test
    @Transactional
    public void testIsFloorAndRoomNumber4() {
        boolean result = roominformationDAO.isFloorAndRoomNumber("清风阁", 120);
        Assert.assertFalse(result);
    }

    /**
     * 验证:当老人的eid正确的是否，且已经入住是否返回true
     */
    @Test
    @Transactional
    public void testIsLive1() {
        boolean result = roominformationDAO.isLive(1);
        Assert.assertTrue(result);
    }

    /**
     * 验证;当老人的eid正确的前提下，且没有入住是否返回false
     */
    @Test
    @Transactional
    public void testIsLive2() {
        boolean result = roominformationDAO.isLive(15);
        Assert.assertFalse(result);
    }

    /**
     * 验证：当已经存在楼号与房间号码时候是否返回1
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testAddInformation1() {
        int result = roominformationDAO.addInformation("东", 101, null, 0, 0);
        Assert.assertEquals(1, result);
    }

    /**
     * 验证：当新的房间信息的时候，老人已经入住是否返回2
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testAddInformation2() {
        int result = roominformationDAO.addInformation("ccj", 101, "ccj", 900, 2);
        Assert.assertEquals(2, result);

    }

    /**
     * 验证：当新的房间信息的时候，类型是新的，价格是新的，老人无入住是否返回3
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testAddInformation3() {
        int result = roominformationDAO.addInformation("ccj", 101, "ccj", 900, 15);
        Assert.assertEquals(3, result);

    }

    /**
     * 验证：当新的房间信息的时候，类型是旧的，价格是新的，老人无入住是否返回3
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testAddInformation4() {
        int result = roominformationDAO.addInformation("ccj", 101, "标准", 1000, 15);
        Assert.assertEquals(3, result);
    }

    /**
     * 验证：当正确的rId的时候是否返回正确的Roominformation实例化
     */
    @Test
    @Transactional
    public void testGetSearchRoominformation() {
        Roominformation ri = roominformationDAO.getSearchRoominformation(1);
        Assert.assertEquals(1, ri.getRId());
    }
}
