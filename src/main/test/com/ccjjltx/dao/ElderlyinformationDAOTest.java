package com.ccjjltx.dao;

import com.ccjjltx.domain.Elder;
import com.ccjjltx.domain.Elderlyinformation;
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
 * Created by ccjjltx on 2017/11/5.
 * 测试ElderlyinformationDAO相关功能
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class ElderlyinformationDAOTest {
    @Resource(name = "elderlyinformationDAO")
    private ElderlyinformationDAO elderlyinformationDAO;

    /**
     * 验证：当没有ename的时候是否返回所有的数据
     */
    @Test
    @Transactional
    public void testGetAllInformation1() {
        int size = elderlyinformationDAO.getAllInformationNumber(null);
        List<Elderlyinformation> result = elderlyinformationDAO.getAllInformation(0, 100, null);
        Assert.assertEquals(size, result.size());
    }

    /**
     * 验证：当有正确的用户名时候是否返回唯一数据
     */
    @Test
    @Transactional
    public void testGetAllInformation2() {
        int size = elderlyinformationDAO.getAllInformationNumber("张志新");
        List<Elderlyinformation> result = elderlyinformationDAO.getAllInformation(0, 100, "张志新");
        Assert.assertEquals(size, result.size());
    }

    /**
     * 验证：当名字为null时候是否返回数据库的总条数
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber1() {
        int size = elderlyinformationDAO.getAllInformation(0, 100, null).size();
        int result = elderlyinformationDAO.getAllInformationNumber(null);
        Assert.assertEquals(size, result);
    }

    /**
     * 验证：当用户名为正确且唯一,总条数结果是否为1
     */
    @Test
    @Transactional
    public void testGetAllInformationNumber2() {
        int size = elderlyinformationDAO.getAllInformation(0, 100, "张志新").size();
        int result = elderlyinformationDAO.getAllInformationNumber("张志新");
        Assert.assertEquals(size, result);
    }

    /**
     * 验证：getSearchInformation是否能根据主键得到Elderlyinformation实例化
     */
    @Test
    @Transactional
    public void testGetSearchInformation() {
        Elderlyinformation elderlyinformation = elderlyinformationDAO.getSearchInformation(1);
        Assert.assertEquals(1, elderlyinformation.getEiId());
    }

    /**
     * 验证：是否能根据Elderlyinformation实例化与Elder的id正确是是否插入成功
     */
    @Test
    @Transactional
    @Rollback
    public void testAddInformation() {
        try {
            Elderlyinformation ei = new Elderlyinformation("12341234", "1234", "男", MyDateFormat.parse("1995-01-01"), "guangzhou", "guangzhou");
            Elder e = new Elder("ccc", 1);
            elderlyinformationDAO.addInformation(ei, e);
            Assert.assertEquals(1, elderlyinformationDAO.getAllInformationNumber("ccc"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证：是否能准确更新信息
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation1() {
        Elderlyinformation db_ei = elderlyinformationDAO.getSearchInformation(1);
        db_ei.setIdcard("123123");
        elderlyinformationDAO.updateInformation(db_ei);
        Assert.assertEquals("123123", elderlyinformationDAO.getSearchInformation(1).getIdcard());
    }

    /**
     * 验证：是否能准确更新信息
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation2() {
        Elderlyinformation db_ei = elderlyinformationDAO.getSearchInformation(1);
        db_ei.getElder().setEname("cccc");
        elderlyinformationDAO.updateInformation(db_ei);
        Assert.assertEquals("cccc", elderlyinformationDAO.getSearchInformation(1).getElder().getEname());
    }

    /**
     * 验证：删除功能
     */
    @Test
    @Transactional
    @Rollback
    public void testDeleteInformation() {
        int result = elderlyinformationDAO.getAllInformationNumber(null);//得到总条数
        elderlyinformationDAO.deleteInformation(1);//删除第一条数据
        int result2 = elderlyinformationDAO.getAllInformationNumber(null);//删除后的总条数
        Assert.assertEquals(result - 1, result2);
    }
}
