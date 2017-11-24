package com.ccjjltx.dao;

import com.ccjjltx.domain.Nursingfee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by ccjjltx on 2017/11/25.
 * 测试NursingfeeDAO相关的方法
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class NursingfeeDAOTest {
    @Resource(name = "nursingfeeDAO")
    private NursingfeeDAO nursingfeeDAO;

    /**
     * 验证：是否能得到所有的数据
     */
    @Test
    @Transactional
    public void testGetAllInformation() {
        //测试该方法时候，数据库的数据为3条
        int result = nursingfeeDAO.getAllInformation().size();
        Assert.assertEquals(3, result);
    }

    /**
     * 验证：根据根据主键得到实例化
     */
    @Test
    @Transactional
    public void testGetSearchInformation() {
        Nursingfee nf = nursingfeeDAO.getSearchInformation(1);
        Assert.assertEquals(1, nf.getNid());
    }

}
