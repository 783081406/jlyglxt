package com.ccjjltx.action;

import com.ccjjltx.utils.MyDateFormat;
import net.sf.json.JSONArray;
import org.apache.struts2.StrutsSpringJUnit4TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;

/**
 * Created by ccjjltx on 2018/3/5.
 * 测试EaeAction方法（出入院的增删除改查）
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class EaeActionTest extends StrutsSpringJUnit4TestCase<EaeAction> {
    @Resource(name = "eaeAction")
    private EaeAction eaeAction;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * describe：得到全部或者特定的（搜索框触发）的信息
     */
    @Test
    @Transactional
    public void testGetAllInformation() {
        eaeAction.setPage(1);
        eaeAction.setRows(5);
        eaeAction.setResult(null);
        Assert.assertEquals("success", eaeAction.getAllInformation());
        Assert.assertEquals(5, ((JSONArray) eaeAction.getResult().get("rows")).size());
    }

    /**
     * describe：用于增加出入院信息
     * success
     */
    @Test
    @Transactional
    @Rollback
    public void testAddInformation1() {
        try {
            eaeAction.setEname("16");
            eaeAction.setStime(MyDateFormat.parse("2017-10-11"));
            eaeAction.setEtime(MyDateFormat.parse("2017-10-11"));
            eaeAction.setIsIn(1);
            Assert.assertEquals("success", eaeAction.addInformation());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * describe：用于增加出入院信息
     * error：无效用户名
     */
    @Test
    @Transactional
    @Rollback
    public void testAddInformation2() {
        eaeAction.setEname("abc");
        Assert.assertEquals("error", eaeAction.addInformation());
    }

    /**
     * describe：用于增加出入院信息
     * error:该名老人的数据已经存在
     */
    @Test
    @Transactional
    @Rollback
    public void testAddInformation3() {
        try {
            eaeAction.setEname("1");
            eaeAction.setStime(MyDateFormat.parse("2017-10-11"));
            eaeAction.setEtime(MyDateFormat.parse("2017-10-11"));
            eaeAction.setIsIn(1);
            Assert.assertEquals("error", eaeAction.addInformation());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * describe：更新信息
     */
    @Test
    @Transactional
    @Rollback
    public void testupdateInformation() {
        try {
            eaeAction.setEaeid(1);
            eaeAction.setStime(MyDateFormat.parse("2017-10-11"));
            eaeAction.setEtime(MyDateFormat.parse("2017-12-30"));
            eaeAction.setIsIn(2);
            Assert.assertEquals("success", eaeAction.updateInformation());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * describe：删除功能
     */
    @Test
    @Transactional
    @Rollback
    public void testRemoveInformation() {
        eaeAction.setEaeid(1);
        Assert.assertEquals("success",eaeAction.removeInformation());
    }

}
