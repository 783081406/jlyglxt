package com.ccjjltx.dao;

import com.ccjjltx.domain.Ecginformation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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


}
