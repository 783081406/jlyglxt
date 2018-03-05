package com.ccjjltx.action;

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

/**
 * Created by ccjjltx on 2018/3/5.
 * 测试InterlocutionAction方法（对常见问题数据的增删改查）
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class InterlocutionActionTest extends StrutsSpringJUnit4TestCase<InterlocutionAction> {
    @Resource(name = "interlocutionAction")
    private InterlocutionAction interlocutionAction;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * describe：得到所有的信息数据
     */
    @Test
    @Transactional
    public void testGetAllInformation() {
        interlocutionAction.setPage(1);
        interlocutionAction.setRows(4);
        interlocutionAction.setResult(null);
        Assert.assertEquals("success", interlocutionAction.getAllInformation());
        Assert.assertEquals(4, ((JSONArray) interlocutionAction.getResult().get("rows")).size());
    }

    /**
     * describe：重选功能
     */
    @Test
    @Transactional
    @Rollback
    public void testReelectInformation() {
        Assert.assertEquals("success", interlocutionAction.reelectInformation());
    }

    /**
     * describe：提交功能，选择要展示的数据
     */
    @Test
    @Transactional
    @Rollback
    public void testSelectInformation() {
        int[] qaids = {1, 2};
        interlocutionAction.setQaids(qaids);
        Assert.assertEquals("success", interlocutionAction.selectInformation());
    }

    /**
     * describe：增加新数据
     */
    @Test
    @Transactional
    @Rollback
    public void testSaveInformation() {
        interlocutionAction.setQuestion("123");
        interlocutionAction.setAnswer("123");
        Assert.assertEquals("success", interlocutionAction.saveInformation());
    }

    /**
     * describe：更新数据
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation() {
        interlocutionAction.setQaid(1);
        interlocutionAction.setQuestion("123123");
        interlocutionAction.setAnswer("123123");
        Assert.assertEquals("success", interlocutionAction.updateInformation());
    }

    /**
     * describe：删除数据
     */
    @Test
    @Transactional
    @Rollback
    public void testRemoveInformation() {
        interlocutionAction.setQaid(1);
        Assert.assertEquals("success", interlocutionAction.removeInformation());
    }
}
