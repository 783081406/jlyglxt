package com.ccjjltx.dao;

import com.ccjjltx.domain.Elder;
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
}
