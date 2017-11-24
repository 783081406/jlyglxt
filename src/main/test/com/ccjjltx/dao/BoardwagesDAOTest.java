package com.ccjjltx.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by ccjjltx on 2017/11/24.
 * 测试BoardwagesDAO相关方法
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class BoardwagesDAOTest {
    @Resource(name = "boardwagesDAO")
    private BoardwagesDAO boardwagesDAO;

    /**
     * 验证：是否返回所有的数据
     */
    @Test
    @Transactional
    public void testGetAllInformation() {
        //测试该方法时，数据库的数据为1条
        int result = boardwagesDAO.getAllInformation().size();
        Assert.assertEquals(1, result);
    }

}
