package com.ccjjltx.dao;

import com.ccjjltx.domain.Elderlyinformation;
import com.ccjjltx.domain.Familyinformation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
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
}