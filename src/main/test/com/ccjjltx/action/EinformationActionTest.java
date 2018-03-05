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
 * 测试EinformationAction方法（员工信息增删改查）
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class EinformationActionTest extends StrutsSpringJUnit4TestCase<EinformationAction> {
    @Resource(name = "einformationAction")
    private EinformationAction einformationAction;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * describe：到全部或者特地（搜索框触发）的员工信息数据
     * 搜索框：name is null , userName  is  null
     */
    @Test
    @Transactional
    public void testGetAllInformation1() {
        einformationAction.setPage(1);
        einformationAction.setRows(2);
        einformationAction.setName(null);
        einformationAction.setUserName(null);
        einformationAction.setResult(null);
        Assert.assertEquals("success", einformationAction.getAllInformation());
        Assert.assertEquals(2, ((JSONArray) einformationAction.getResult().get("rows")).size());
    }

    /**
     * describe：到全部或者特地（搜索框触发）的员工信息数据
     * 搜索框：name is not null , userName  is  not null
     */
/*    @Test
    @Transactional
    public void testGetAllInformation2() {
        einformationAction.setPage(1);
        einformationAction.setRows(3);
        einformationAction.setName("卫");
        einformationAction.setUserName("w");
        Assert.assertEquals("success", einformationAction.getAllInformation());
    }*/

    /**
     * describe：增加新员工信息
     * success
     */
    @Test
    @Transactional
    @Rollback
    public void testSaveInformation1() {
        try {
            einformationAction.setName("cccc");
            einformationAction.setIdCard("123123");
            einformationAction.setSex("男");
            einformationAction.setAddress("23123");
            einformationAction.setPType("护工");
            einformationAction.setHiredate(MyDateFormat.parse("2017-12-12"));
            einformationAction.setEducation("本科");
            einformationAction.setBirthday(MyDateFormat.parse("2017-12-12"));
            einformationAction.setUserName("2");
            Assert.assertEquals("success", einformationAction.saveInformation());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * describe：增加新员工信息
     * error
     */
    @Test
    @Transactional
    @Rollback
    public void testSaveInformation2() {
        try {
            einformationAction.setName("1");
            einformationAction.setIdCard("1");
            einformationAction.setSex("男");
            einformationAction.setAddress("1");
            einformationAction.setPType("护工");
            einformationAction.setHiredate(MyDateFormat.parse("2017-12-12"));
            einformationAction.setEducation("本科");
            einformationAction.setBirthday(MyDateFormat.parse("2017-12-12"));
            einformationAction.setUserName("abc");
            Assert.assertEquals("error", einformationAction.saveInformation());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * describe：更新信息
     * success
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation1() {
        try {
            einformationAction.setName("2");
            einformationAction.setIdCard("2");
            einformationAction.setSex("男");
            einformationAction.setAddress("2");
            einformationAction.setPType("护工");
            einformationAction.setHiredate(MyDateFormat.parse("2017-11-12"));
            einformationAction.setEducation("本科");
            einformationAction.setBirthday(MyDateFormat.parse("2017-11-12"));
            einformationAction.setUserName("4");
            Assert.assertEquals("success", einformationAction.updateInformation());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * describe：更新信息
     * error
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation2() {
        try {
            einformationAction.setName("5");
            einformationAction.setIdCard("6");
            einformationAction.setSex("男");
            einformationAction.setAddress("7");
            einformationAction.setPType("护工");
            einformationAction.setHiredate(MyDateFormat.parse("2017-11-12"));
            einformationAction.setEducation("本科");
            einformationAction.setBirthday(MyDateFormat.parse("2017-11-12"));
            einformationAction.setUserName("abc");
            Assert.assertEquals("error", einformationAction.updateInformation());
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
        einformationAction.setPid(1001);
        Assert.assertEquals("success", einformationAction.removeInformation());
    }
}
