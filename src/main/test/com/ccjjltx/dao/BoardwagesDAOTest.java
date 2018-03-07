package com.ccjjltx.dao;

import com.ccjjltx.domain.Boardwages;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
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

    /**
     * 验证：是否能根据主键得到实例化
     */
    @Test
    @Transactional
    public void testGetSearchInformation() {
        Boardwages bw = boardwagesDAO.getSearchInformation(1);
        Assert.assertEquals(1, bw.getBwid());
    }

    /**
     * 验证：是否能更新数据
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation() {
        Boardwages bw = boardwagesDAO.getSearchInformation(1);//得到一个实例化
        bw.setMinimum(10);//修改信息
        //执行方法更新
        boardwagesDAO.updateInformation(bw);
        Assert.assertEquals(10, boardwagesDAO.getSearchInformation(1).getMinimum());
    }

}
