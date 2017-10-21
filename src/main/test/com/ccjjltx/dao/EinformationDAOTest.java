package com.ccjjltx.dao;

import com.ccjjltx.domain.Einformation;
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
 * Created by ccjjltx on 2017/10/20.
 * 测试EinformationDAO相关的功能
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class EinformationDAOTest {
    @Resource(name = "einformationDAO")
    private EinformationDAO einformationDAO;

    /**
     * 验证:当没有数据name和userName的时候是否能全部返回
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testGetAllInformation1() {
        List<Einformation> list = einformationDAO.getAllInformation(0, 1, null, null);
        int pid = 0;
        for (Einformation einformation : list) {
            pid = einformation.getPid();
        }
        Assert.assertEquals(1001, pid);
    }

    /**
     * 验证:当有数据name的时候是否返回正确结果
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testGetAllInformation2() {
        List<Einformation> list = einformationDAO.getAllInformation(0, 1, "卫照捷", null);
        int pid = 0;
        for (Einformation einformation : list) {
            pid = einformation.getPid();
        }
        Assert.assertEquals(1001, pid);
    }

    /**
     * 验证:当有数据userName的时候是否返回正确结果
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testGetAllInformation3() {
        List<Einformation> list = einformationDAO.getAllInformation(0, 1, null, "weizj");
        int pid = 0;
        for (Einformation einformation : list) {
            pid = einformation.getPid();
        }
        Assert.assertEquals(1001, pid);
    }

    /**
     * 验证:当有数据正确name与错误userName的时候是否null
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testGetAllInformation4() {
        List<Einformation> list = einformationDAO.getAllInformation(0, 1, "ccc", "weizj");
        //当list为空的时候，list为[]
        Assert.assertEquals("[]", list.toString());
    }

    /**
     * 验证:当有数据错误name与正确userName的时候是否null
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testGetAllInformation5() {
        List<Einformation> list = einformationDAO.getAllInformation(0, 1, "卫照捷", "ccc");
        Assert.assertNull(list);
    }

    /**
     * 验证:当有数据正确name与正确userName的时候是否返回正确值
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testGetAllInformation6() {
        List<Einformation> list = einformationDAO.getAllInformation(0, 1, "卫照捷", "weizj");
        int pid = 0;
        for (Einformation e : list) {
            pid = e.getPid();
        }
        Assert.assertEquals(1001, pid);
    }

    /**
     * 验证:当空name与userName是否返回全部信息的总数
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber1() {
        int result = einformationDAO.getAllInformationNumber(null, null);
        Assert.assertEquals(12, result);
    }

    /**
     * 验证:当只有name是否返回全部信息的总数
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber2() {
        int result = einformationDAO.getAllInformationNumber("卫照捷", null);
        Assert.assertEquals(1, result);
    }

    /**
     * 验证:当只有userName是否返回全部信息的总数
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber3() {
        int result = einformationDAO.getAllInformationNumber(null, "weizj");
        Assert.assertEquals(1, result);
    }

    /**
     * 验证:当有正确user与username是否返回全部信息的总数
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber4() {
        int result = einformationDAO.getAllInformationNumber("卫照捷", "weizj");
        Assert.assertEquals(1, result);
    }

    /**
     * 验证:当有正确user与错误username是否返回0
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber5() {
        int result = einformationDAO.getAllInformationNumber("卫照捷", "zj");
        Assert.assertEquals(0, result);
    }


    /**
     * 验证:当有错误user与正确username是否返回0
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber6() {
        int result = einformationDAO.getAllInformationNumber("天地人", "weizj");
        Assert.assertEquals(0, result);
    }
}
