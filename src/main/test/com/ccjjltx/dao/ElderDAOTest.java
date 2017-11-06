package com.ccjjltx.dao;

import com.ccjjltx.domain.Elder;
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
 * 测试ElderlyDAO相关的功能
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class ElderDAOTest {
    @Resource(name = "elderDAO")
    private ElderDAO elderDAO;

    /**
     * 验证：当输入错误用户时候是否返回null
     */
    @Test
    @Transactional
    public void testGetSearchElder1() {
        Elder elder = elderDAO.getSearchElder("ccj");
        Assert.assertNull(elder);
    }

    /**
     * 验证：当输入错误用户时候是否返回null
     */
    @Test
    @Transactional
    public void testGetSearchElder2() {
        Elder elder = elderDAO.getSearchElder("张志新");
        Assert.assertEquals(1, elder.getEId());
    }

    /**
     * 验证:是否返回所有的Elder信息
     */
    @Test
    @Transactional
    public void testGetAllElder() {
        List<Elder> list = elderDAO.getAllElder();
        Assert.assertEquals(15, list.size());
    }

    /**
     * 验证:当正确的eId的是否是否返回正确的Elder实例化
     */
    @Test
    @Transactional
    public void testGetSearchElder3() {
        Elder db_elder = elderDAO.getSearchElder(1);
        Assert.assertEquals(1, db_elder.getEId());
    }

    /**
     * 验证：当正确的用户名等是否能存储
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testAddInformation() {
        Elder e = new Elder("ccc", 1);
        elderDAO.addInformation(e);
        Assert.assertEquals("ccc", elderDAO.getSearchElder("ccc").getEname());
    }

    /**
     * 验证：是否能正确更新信息
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testUpdateInformation() {
        Elder e = elderDAO.getSearchElder(16);
        e.setEname("ccc");
        elderDAO.updateInformation(e);
        Assert.assertEquals("ccc", elderDAO.getSearchElder(16).getEname());
    }

}
