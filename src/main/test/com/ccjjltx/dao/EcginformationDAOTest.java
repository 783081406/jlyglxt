package com.ccjjltx.dao;

import com.ccjjltx.domain.Casehistory;
import com.ccjjltx.domain.Ecginformation;
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
 * Created by ccjjltx on 2017/11/8.
 * 测试EcginformationDAO相关功能
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class EcginformationDAOTest {
    @Resource(name = "ecginformationDAO")
    private EcginformationDAO ecginformationDAO;
    @Resource(name = "casehistoryDAO")
    private CasehistoryDAO casehistoryDAO;

    /**
     * 验证：是否能根据主键得到所有的心电信息
     */
    @Test
    @Transactional
    public void testGetAllInformation() {
        //得到chId为1的心电信息
        List<Ecginformation> list = ecginformationDAO.getAllInformation(1);
        Assert.assertNotNull(list);
    }

    /**
     * 验证：是否能新增信息
     */
    @Test
    @Transactional
    @Rollback
    public void testAddInformation() {
        try {
            int result1 = ecginformationDAO.getAllInformation(1).size();//得到chId为1的心电信息，得到其中大小
            Ecginformation eb = new Ecginformation("1", "1", "1", "1", "1", MyDateFormat.parse("2017-11-15"));
            Casehistory casehistory = casehistoryDAO.getSearchInformation(1);//得到一个casehistory实例化
            eb.setCasehistory(casehistory);
            ecginformationDAO.addInformation(eb);//增加操作
            int result2 = ecginformationDAO.getAllInformation(1).size();
            Assert.assertEquals(result1 + 1, result2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证：是否能根据主键Ecginformation实例化
     */
    @Test
    @Transactional
    public void testGetSearchEinformation() {
        Ecginformation ec = ecginformationDAO.getSearchEinformation(1);
        Assert.assertEquals(1, ec.getCasehistory().getChId());
    }

    /**
     * 验证：是否能执行更新操作
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation() {
        Ecginformation ec = ecginformationDAO.getSearchEinformation(1);
        ec.setQrs("1");//更新qrs区间
        ecginformationDAO.updateInformation(ec);//执行更新操作
        Ecginformation ec2 = ecginformationDAO.getSearchEinformation(1);
        Assert.assertEquals("1", ec2.getQrs());
    }

    /**
     * 验证：是否能执行删除操作
     */
    @Test
    @Transactional
    @Rollback
    public void testDeleteInformation() {
        int result1 = ecginformationDAO.getAllInformation(1).size();//得到chId为1的数据条数
        ecginformationDAO.deleteInformation(1);//其中ecgId为1的数据，chId也为1
        int result2 = ecginformationDAO.getAllInformation(1).size();//再次得到chId为1的数据条数
        Assert.assertEquals(result1 - 1, result2);
    }

}
