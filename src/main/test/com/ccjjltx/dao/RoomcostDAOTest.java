package com.ccjjltx.dao;

import com.ccjjltx.domain.Roomcost;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by ccjjltx on 2017/10/30.
 * 测试RoomcostDAO相关的方法
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class RoomcostDAOTest {
    @Resource(name = "roomcostDAO")
    private RoomcostDAO roomcostDAO;

    /**
     * 验证：当正确的rcId号的时候是否返回Roomcost实例化
     */
    @Test
    @Transactional
    public void testGetSearchRoomcost1() {
        Roomcost roomcost = roomcostDAO.getSearchRoomcost(1);
        Assert.assertEquals(1, roomcost.getRcId());
    }

    /**
     * 验证：当正确的类型名的是否是否返回Roomcost实例化
     */
    @Test
    @Transactional
    public void testGetSearchRoomcost2() {
        Roomcost roomcost = roomcostDAO.getSearchRoomcost("标准");
        Assert.assertEquals(1, roomcost.getRcId());
    }

    /**
     * 验证：当错误的类型名的是否返回null
     */
    @Test
    @Transactional
    public void testGetSearchRoomcost3() {
        Roomcost roomcost = roomcostDAO.getSearchRoomcost("ccj");
        Assert.assertNull(roomcost);
    }

    /**
     * 验证:是否能新增房间类型与房价价格新数据
     */
    @Test
    @Transactional
    //新增，防止数据冗余，回滚
    @Rollback
    public void testAddRoomcost() {
        roomcostDAO.addRoomcost("ccj", 1200);
    }

    /**
     * 验证是否能更新已存在的数据
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateRoomcost() {
        Roomcost roomcost = roomcostDAO.getSearchRoomcost(1);
        roomcost.setRCost(3600);
        roomcostDAO.updateRoomcost(roomcost);
    }

    /**
     * 验证：是否得到三条数据
     */
    @Test
    @Transactional
    public void testGetAllInformation1() {
        int result = roomcostDAO.getAllInformation().size();
        Assert.assertEquals(3, result);
    }

    /**
     * 验证：得到的第一条数据主键是否是1
     */
    @Test
    @Transactional
    public void testGetAllInformation2() {
        Roomcost rc1 = roomcostDAO.getAllInformation().get(0);
        Assert.assertEquals(1, rc1.getRcId());
    }

    /**
     * 验证：得到的第二条数据主键是否是2
     */
    @Test
    @Transactional
    public void testGetAllInformation3() {
        Roomcost rc2 = roomcostDAO.getAllInformation().get(1);
        Assert.assertEquals(2, rc2.getRcId());
    }

    /**
     * 验证：得到的第三条数据主键是否是3
     */
    @Test
    @Transactional
    public void testGetAllInformation4() {
        Roomcost rc3 = roomcostDAO.getAllInformation().get(2);
        Assert.assertEquals(3, rc3.getRcId());
    }
}
