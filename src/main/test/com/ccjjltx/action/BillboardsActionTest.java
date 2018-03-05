package com.ccjjltx.action;

import com.ccjjltx.utils.MyFile;
import net.sf.json.JSONArray;
import org.apache.struts2.StrutsSpringJUnit4TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;

/**
 * Created by ccjjltx on 2018/3/5.
 * 测试BillboardsAction方法（对宣传栏数据增删查改）
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:beans.xml")
public class BillboardsActionTest extends StrutsSpringJUnit4TestCase<BillboardsAction> {
    @Resource(name = "billboardsAction")
    private BillboardsAction billboardsAction;

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * describe：得到总数据
     */
    @Test
    @Transactional
    public void testGetAllInformation() {
        billboardsAction.setPage(1);
        billboardsAction.setRows(10);
        billboardsAction.setResult(null);
        Assert.assertEquals("success", billboardsAction.getAllInformation());
        //断言JSON返回数据
        Assert.assertEquals(10, ((JSONArray) billboardsAction.getResult().get("rows")).size());
    }

    /**
     * describe：重选功能
     */
    @Test
    @Transactional
    @Rollback
    public void testReelectInformation() {
        Assert.assertEquals("success", billboardsAction.reelectInformation());
    }

    /**
     * describe：提交功能，选择了要轮播的图片之后提交过来处理的方法
     */
    @Test
    @Transactional
    @Rollback
    public void testSelectInformation() {
        int[] bids = {1, 2};
        billboardsAction.setBids(bids);
        Assert.assertEquals("success", billboardsAction.selectInformation());
    }

    /**
     * describe：增加新数据
     */
    @Test
    @Transactional
    @Rollback
    public void testSaveInformation() {
        billboardsAction.setSavePath("E:\\pcCode\\ideaCode\\jlyglxt\\src\\main\\webapp\\reception\\img\\billboards");
        billboardsAction.setUploadFileName("123.jpg");
        File upload = new File("E:\\pcCode\\ideaCode\\jlyglxt\\src\\main\\webapp\\reception\\img\\billboards\\a2.jpg");
        billboardsAction.setUpload(upload);
        Assert.assertEquals("success", billboardsAction.saveInformation());
    }

    /**
     * describe：更新数据
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation() {
        billboardsAction.setBid(1);
        billboardsAction.setBtitle("cccccc");
        billboardsAction.setBcontent("ccccccc");
        Assert.assertEquals("success", billboardsAction.updateInformation());
    }

    /**
     * describe：删除数据
     */
    @Test
    @Transactional
    @Rollback
    public void testRemoveInformation() {
        billboardsAction.setBid(1);
        Assert.assertEquals("success", billboardsAction.removeInformation());
        //实现文件拷贝复制,恢复数据
        String backup = "E:\\pcCode\\ideaCode\\jlyglxt\\src\\main\\webapp\\reception\\img\\billboards\\a2backup.jpg";
        String oldfile = "E:\\pcCode\\ideaCode\\jlyglxt\\src\\main\\webapp\\reception\\img\\billboards\\a2.jpg";
        MyFile.copyFile(backup, oldfile);
    }
}
