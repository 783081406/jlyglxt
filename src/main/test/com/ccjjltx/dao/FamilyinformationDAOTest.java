package com.ccjjltx.dao;

import com.ccjjltx.domain.Elderlyinformation;
import com.ccjjltx.domain.Familyinformation;
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
 * Created by ccjjltx on 2017/11/5.
 * 对FamilyinformationDAO相关功能进行测试
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class FamilyinformationDAOTest {
    @Resource(name = "familyinformationDAO")
    private FamilyinformationDAO familyinformationDAO;
    @Resource(name = "elderlyinformationDAO")
    private ElderlyinformationDAO elderlyinformationDAO;

    /**
     * 验证：传入正确的eiId是否得到所有Familyinformation实例化
     */
    @Test
    @Transactional
    public void testGetAllInformation() {
        List<Familyinformation> list = familyinformationDAO.getAllInformation(1);
        int resultLength = list.size();
        Assert.assertEquals(2, resultLength);
    }

    /**
     * 验证：是否能删除
     */
    @Test
    @Transactional
    @Rollback
    public void testDeleteInformation() {
        familyinformationDAO.deleteInformation(1);
        //删除之后则没有eiId为1的数据，则size应该是0
        Assert.assertEquals(0, familyinformationDAO.getAllInformation(1).size());
    }

    /**
     * 验证：当信息正确的时候是否能插入成功
     */
    @Test
    @Transactional
    @Rollback
    public void testAddInformation() {
        int result = familyinformationDAO.getAllInformation(1).size();
        Elderlyinformation ei = elderlyinformationDAO.getSearchInformation(1);
        //实例化
        Familyinformation fi = new Familyinformation("ccj", "123", "123", "123");
        fi.setElderlyinformation(ei);
        //插入数据
        familyinformationDAO.addInformation(fi);
        //得到的结果应该是未插入前加1
        Assert.assertEquals(result + 1, familyinformationDAO.getAllInformation(1).size());
    }

    /**
     * 验证：是否能根据主键得到实例化
     */
    @Test
    @Transactional
    @Rollback
    public void testSearchInformation() {
        Familyinformation db_fi = familyinformationDAO.getSearchInformation(1);
        Assert.assertEquals(1, db_fi.getFId());
    }

    /**
     * 验证：是否能更新
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation() {
        //得到实例化
        Familyinformation db_fi1 = familyinformationDAO.getSearchInformation(1);
        //更新
        db_fi1.setFamilyName("ccj");
        familyinformationDAO.updateInformation(db_fi1);
        //再次得到修改的实例化
        Familyinformation db_fi2 = familyinformationDAO.getSearchInformation(1);
        //断言
        Assert.assertEquals("ccj", db_fi2.getFamilyName());
    }
}