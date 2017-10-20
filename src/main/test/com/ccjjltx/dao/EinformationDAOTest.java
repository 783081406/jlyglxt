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

}
