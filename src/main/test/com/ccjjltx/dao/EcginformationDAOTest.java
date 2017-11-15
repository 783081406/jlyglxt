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
        //测试的时候，数据库的数据为2，所以得到的大小应该为2
        Assert.assertEquals(2, list.size());
    }

    /**
     * 验证：是否能新增信息
     */
    @Test
    @Transactional
    @Rollback
    public void testAddInformation() {
        try {
            //得到chId为1的心电信息，得到其中大小
            int result1 = ecginformationDAO.getAllInformation(1).size();
            Ecginformation eb = new Ecginformation("1", "1", "1", "1", "1", MyDateFormat.parse("2017-11-15"));
            //得到一个casehistory实例化
            Casehistory casehistory = casehistoryDAO.getSearchInformation(1);
            eb.setCasehistory(casehistory);
            //增加操作
            ecginformationDAO.addInformation(eb);
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
        //更新qrs区间
        ec.setQrs("1");
        //执行更新操作
        ecginformationDAO.updateInformation(ec);
        Ecginformation ec2 = ecginformationDAO.getSearchEinformation(1);
        Assert.assertEquals("1", ec2.getQrs());
    }

}
