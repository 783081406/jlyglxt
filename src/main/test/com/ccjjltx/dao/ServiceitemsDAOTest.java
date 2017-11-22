package com.ccjjltx.dao;

import com.ccjjltx.domain.Serviceitems;
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
 * Created by ccjjltx on 2017/11/22.
 * 测试Serviceitems相关的功能
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class ServiceitemsDAOTest {
    @Resource(name = "serviceitemsDAO")
    private ServiceitemsDAO serviceitemsDAO;

    /**
     * 验证：是否能返回所有的数据
     */
    @Test
    @Transactional
    public void testGetAllInformation() {
        //测试时候数据库的数据为12条
        int result = serviceitemsDAO.getAllInformation(0, 12).size();
        Assert.assertEquals(12, result);
    }

    /**
     * 验证是否能返回得到的总条数
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber() {
        //测试的时候数据库的数据为12条
        int result = serviceitemsDAO.getAllInformationNumber();
        Assert.assertEquals(12, result);
    }

    /**
     * 验证：是否所有的值都改为0
     */
    @Test
    @Transactional
    @Rollback
    public void testReelectInformation() {
        //得到得到的第一条数据
        //测试时，该条数据的isSelect是1;
        List<Serviceitems> list = serviceitemsDAO.getAllInformation(0, 1);
        int sisSelect = 2;
        for (Serviceitems si : list) {
            sisSelect = si.getIsSelect();
        }
        //执行重选
        serviceitemsDAO.reelectInformation();
        //再次得到数据，查看是否为0
        List<Serviceitems> list2 = serviceitemsDAO.getAllInformation(0, 1);
        int sisSelect2 = 2;
        for (Serviceitems si : list2) {
            sisSelect2 = si.getIsSelect();
        }
        Assert.assertEquals(sisSelect - 1, sisSelect2);
    }


}
