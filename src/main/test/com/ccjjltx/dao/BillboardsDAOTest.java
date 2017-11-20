package com.ccjjltx.dao;

import com.ccjjltx.domain.Billboards;
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
 * Created by ccjjltx on 2017/11/20.
 * 测试BillboardsDAO相关的功能
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class BillboardsDAOTest {
    @Resource(name = "billboardsDAO")
    private BillboardsDAO billboardsDAO;

    /**
     * 验证：是否能返回所有的数据
     */
    @Test
    @Transactional
    public void testGetAllInformation() {
        //测试时候数据库的数据为10条
        int result = billboardsDAO.getAllInformation(0, 10).size();
        Assert.assertEquals(10, result);
    }

    /**
     * 验证：测试是否能得到总条数
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber() {
        //测试的术后数据库的数据为12条
        int result = billboardsDAO.getAllInformationNumber();
        Assert.assertEquals(12, result);
    }

    /**
     * 验证：是否所有的值都改为0
     */
    @Test
    @Transactional
    @Rollback
    public void testReelectInformation() {
        //得到倒序的第一条数据
        //测试时，该条数据的isSelect是1;
        List<Billboards> list = billboardsDAO.getAllInformation(0, 1);
        int bisSelect = 2;
        for (Billboards bb : list) {
            bisSelect = bb.getIsSelect();
        }
        //执行重选
        billboardsDAO.reelectInformation();
        //再次数据，查看是否为0;
        List<Billboards> list2 = billboardsDAO.getAllInformation(0, 1);
        int bisSelect2 = 2;
        for (Billboards bb : list2) {
            bisSelect2 = bb.getIsSelect();
        }
        Assert.assertEquals(bisSelect - 1, bisSelect2);
    }

    /**
     * 验证：是否能根据主键得到实例化
     */
    @Test
    @Transactional
    public void testGetSearchInformation() {
        //得到实例化
        Billboards bb = billboardsDAO.getSearchInformation(1);
        Assert.assertEquals(1, bb.getBid());
    }

    /**
     * 验证：是否能根据主键修改isSelect的值
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateIsSelect() {
        //测试该方法时，数据库的bid为1的数据，isSelect为0
        int result = billboardsDAO.getSearchInformation(1).getIsSelect();
        int[] data = new int[]{1};
        //执行更新方法
        billboardsDAO.updateIsSelect(data);
        //进过上面的更新操作之后，isSelect为1
        int result2 = billboardsDAO.getSearchInformation(1).getIsSelect();
        Assert.assertEquals(result, result2 - 1);
    }

}
