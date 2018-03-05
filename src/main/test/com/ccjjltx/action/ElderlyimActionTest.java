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
 * 测试ElderlyimAction方法（对信息查看的增删除查改）
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class ElderlyimActionTest extends StrutsSpringJUnit4TestCase<ElderlyimAction> {
    @Resource(name = "elderlyimAction")
    private ElderlyimAction elderlyimAction;

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
        elderlyimAction.setPage(1);
        elderlyimAction.setRows(3);
        //elderlyimAction.setEname("张志新");
        elderlyimAction.setResult(null);
        Assert.assertEquals("success", elderlyimAction.getAllInformation());
        Assert.assertEquals(3, ((JSONArray) elderlyimAction.getResult().get("rows")).size());
    }

    /**
     * describe：增加新信息
     */
    @Test
    @Transactional
    @Rollback
    public void testSaveInformation() {
        try {
            elderlyimAction.setEname("ccj");
            elderlyimAction.setIdcard("123123");
            elderlyimAction.setPhone("123123");
            elderlyimAction.setSex("男");
            elderlyimAction.setBirthDate(MyDateFormat.parse("2017-12-12"));
            elderlyimAction.setHomeAddress("123456");
            elderlyimAction.setOriginAddress("123456");
            elderlyimAction.setHabit("123");
            Assert.assertEquals("success", elderlyimAction.saveInformation());
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
    public void testUpdateInformation() {
        try {
            elderlyimAction.setEiId(1);
            elderlyimAction.setEname("123");
            elderlyimAction.setIdcard("123");
            elderlyimAction.setPhone("123");
            elderlyimAction.setSex("男");
            elderlyimAction.setBirthDate(MyDateFormat.parse("2017-12-12"));
            elderlyimAction.setHomeAddress("123456");
            elderlyimAction.setOriginAddress("123456");
            elderlyimAction.setHabit("123");
            Assert.assertEquals("success", elderlyimAction.updateInformation());
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
    public void removeInformation() {
        elderlyimAction.setEiId(1);
        Assert.assertEquals("success", elderlyimAction.removeInformation());
    }

}
