package com.ccjjltx.dao;

import com.ccjjltx.domain.Einformation;
import com.ccjjltx.utils.MyDateFormat;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
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
    public void testGetAllInformation1() {
        int size = einformationDAO.getAllInformationNumber(null, null);
        List<Einformation> result = einformationDAO.getAllInformation(0, 100, null, null);
        Assert.assertEquals(size, result.size());
    }

    /**
     * 验证:当有数据name的时候是否返回正确结果
     */
    @Test
    @Transactional
    public void testGetAllInformation2() {
        int size = einformationDAO.getAllInformationNumber("卫照捷", null);
        List<Einformation> result = einformationDAO.getAllInformation(0, 100, "卫照捷", null);
        Assert.assertEquals(size, result.size());
    }

    /**
     * 验证:当有数据userName的时候是否返回正确结果
     */
    @Test
    @Transactional
    public void testGetAllInformation3() {
        int size = einformationDAO.getAllInformationNumber(null, "weizj");
        List<Einformation> result = einformationDAO.getAllInformation(0, 100, null, "weizj");
        Assert.assertEquals(size, result.size());
    }

    /**
     * 验证:当有数据正确name与正确userName的时候是否返回正确值
     */
    @Test
    @Transactional
    public void testGetAllInformation6() {
        int size = einformationDAO.getAllInformationNumber("卫照捷", "weizj");
        List<Einformation> result = einformationDAO.getAllInformation(0, 100, "卫照捷", "weizj");
        Assert.assertEquals(size, result.size());
    }

    /**
     * 验证:当空name与userName是否返回全部信息的总数
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber1() {
        int size = einformationDAO.getAllInformation(0, 100, null, null).size();
        int result = einformationDAO.getAllInformationNumber(null, null);
        Assert.assertEquals(size, result);
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
     * 验证:当正确id号时
     */
    @Test
    @Transactional
    @Rollback
    public void testAddInformation() {
        try {
            Einformation einformation = new Einformation("陈彩君", "1234567894152", "男", "广东省广州市从化区", "护工", MyDateFormat.parse("2017-07-07"), "本科", MyDateFormat.parse("2017-07-07"));
            einformationDAO.addInformation(einformation, 2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证:更新数据库
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation() {
        try {
            Einformation einformation = new Einformation("陈彩君", "1234567894561423453", "男", "123", "123",
                    MyDateFormat.parse("2017-10-24"), "12", MyDateFormat.parse("2017-10-24"));
            einformation.setPid(1016);
            einformationDAO.updateInformation(einformation, 2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证是否能够删除
     */
    @Test
    @Transactional
    @Rollback
    public void testDeleteInformation() {
        einformationDAO.deleteInformation(1001);
    }
}
