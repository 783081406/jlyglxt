package com.ccjjltx.dao;

import com.ccjjltx.domain.Roomcost;
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
    @Resource(name = "roomcostDAO")
    private RoomcostDAO roomcostDAO;

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
     * 验证：测试重载的isFloorAndRoomNumber方法，当存在相同的楼号与房间号，rId不同，是否返回false
     */
    @Test
    @Transactional
    public void testIsFloorAndRoomNumber5() {
        boolean result = roominformationDAO.isFloorAndRoomNumber(1, "东", 101);
        Assert.assertFalse(result);
    }

    /**
     * 验证：测试重载的isFloorAndRoomNumber方法，当存在相同的楼号与房间号，rId相同，是否返回true
     */
    @Test
    @Transactional
    public void testIsFloorAndRoomNumber6() {
        boolean result = roominformationDAO.isFloorAndRoomNumber(2, "东", 101);
        Assert.assertTrue(result);
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
     * 验证：重载的方法，当老人的eId已经入住，但是不是对应的rI段是否返回false
     */
    @Test
    @Transactional
    public void testIsLive3() {
        boolean result = roominformationDAO.isLive(1, 2);
        Assert.assertTrue(result);
    }

    /**
     * 验证：重载的方法，当老人的eId已经入住，但是不是对应的rI段是否返回true
     */
    @Test
    @Transactional
    public void testIsLive4() {
        boolean result = roominformationDAO.isLive(1, 1);
        Assert.assertFalse(result);
    }

    /**
     * 验证：当已经存在楼号与房间号码时候是否返回1
     */
    @Test
    @Transactional
    @Rollback
    public void testAddInformation1() {
        int result = roominformationDAO.addInformation("东", 101, null, 0, 0);
        Assert.assertEquals(1, result);
    }

    /**
     * 验证：当新的房间信息的时候，老人已经入住是否返回2
     */
    @Test
    @Transactional
    @Rollback
    public void testAddInformation2() {
        int result = roominformationDAO.addInformation("ccj", 101, "ccj", 900, 2);
        Assert.assertEquals(2, result);

    }

    /**
     * 验证：当新的房间信息的时候，类型是新的，价格是新的，老人无入住是否返回3
     */
    @Test
    @Transactional
    @Rollback
    public void testAddInformation3() {
        int result = roominformationDAO.addInformation("ccj", 101, "ccj", 900, 15);
        Assert.assertEquals(3, result);

    }

    /**
     * 验证：当新的房间信息的时候，类型是旧的，价格是新的，老人无入住是否返回3
     */
    @Test
    @Transactional
    @Rollback
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

    /**
     * 验证：信息正确的时候是否能准确更新，同时Elder为入住人员
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation1() {
        Roominformation ri = roominformationDAO.getSearchRoominformation(1);
        int result = roominformationDAO.updateInformation(ri, 2);
        Assert.assertEquals(1, result);
    }

    /**
     * 验证：信息正确的时候是否能准确更新，同时Elder为null
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation2() {
        Roominformation ri = roominformationDAO.getSearchRoominformation(1);
        int result = roominformationDAO.updateInformation(ri, 0);
        Assert.assertEquals(3, result);
    }

    /**
     * 验证：信息正确的时候是否能准确更新，同时Elder为未入住
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation3() {
        Roominformation ri = roominformationDAO.getSearchRoominformation(1);
        int result = roominformationDAO.updateInformation(ri, 1);
        Assert.assertEquals(3, result);
    }

    /**
     * 验证:更新外键Roomcost表的信息的时候是否能更新
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation4() {
        Roominformation ri = roominformationDAO.getSearchRoominformation(1);
        Roomcost r = ri.getRoomcost();
        r.setRCost(810);
        Roomcost r2 = roomcostDAO.getSearchRoomcost(1);
        Assert.assertEquals(810, r2.getRCost());
    }

    /**
     * 验证：当存在相同的楼号与房间号的时候是否返回2
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation5() {
        Roominformation ri = roominformationDAO.getSearchRoominformation(1);
        ri.setFloor("西");
        ri.setRoomNumber(101);
        int result = roominformationDAO.updateInformation(ri, 0);
        Assert.assertEquals(2, result);
    }

    /**
     * 验证：删除功能
     */
    @Test
    @Transactional
    @Rollback
    public void testDeleteInformation() {
        roominformationDAO.deleteInformation(1);
    }
}
