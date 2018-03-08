package com.ccjjltx.action;

import com.ccjjltx.utils.MyFile;
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
import java.io.File;
import java.io.IOException;

/**
 * Created by ccjjltx on 2018/3/6.
 * 测试ServiceitemsAction方法（对服务项目数据的增删改查）
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class ServiceitemsActionTest extends StrutsSpringJUnit4TestCase<ServiceitemsAction> {
    @Resource(name = "serviceitemsAction")
    private ServiceitemsAction serviceitemsAction;

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
        serviceitemsAction.setRows(6);
        serviceitemsAction.setPage(1);
        serviceitemsAction.setResult(null);
        Assert.assertEquals("success", serviceitemsAction.getAllInformation());
        Assert.assertEquals(6, ((JSONArray) serviceitemsAction.getResult().get("rows")).size());
    }

    /**
     * describe：重选功能
     */
    @Test
    @Transactional
    @Rollback
    public void testReelectInformation() {
        Assert.assertEquals("success", serviceitemsAction.reelectInformation());
    }

    /**
     * describe：提交功能，选择了展示的数据的方法
     */
    @Test
    @Transactional
    @Rollback
    public void testSelectInformation() {
        int[] sids = {1, 2, 3, 4};
        serviceitemsAction.setSids(sids);
        Assert.assertEquals("success", serviceitemsAction.selectInformation());
    }

    /**
     * describe：增加新数据
     */
    @Test
    @Transactional
    @Rollback
    public void testSaveInformation() {
        try {
            serviceitemsAction.setUploadFileName("123.jpg");
            serviceitemsAction.setSavePath("E:\\pcCode\\ideaCode\\jlyglxt\\src\\main\\webapp\\reception\\img\\service");
            File upload = new File("E:\\pcCode\\ideaCode\\jlyglxt\\src\\main\\webapp\\reception\\img\\service\\f1.jpg");
            serviceitemsAction.setUpload(upload);
            Assert.assertEquals("success", serviceitemsAction.saveInformation());
            MyFile.deleteFile("E:\\pcCode\\ideaCode\\jlyglxt\\src\\main\\webapp\\reception\\img\\service\\123.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * describe：更新数据
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateInformation() {
        serviceitemsAction.setSid(1);
        serviceitemsAction.setStitle("cccccc");
        serviceitemsAction.setScontent("ccccccc");
        Assert.assertEquals("success", serviceitemsAction.updateInformation());
    }

    /**
     * describe：删除数据
     */
    @Test
    @Transactional
    @Rollback
    public void testRemoveInformation() {
        try {
            serviceitemsAction.setSid(1);
            Assert.assertEquals("success", serviceitemsAction.removeInformation());
            //实现文件拷贝复制,恢复数据
            String backup = "E:\\pcCode\\ideaCode\\jlyglxt\\src\\main\\webapp\\reception\\img\\service\\f1backup.jpg";
            String oldfile = "E:\\pcCode\\ideaCode\\jlyglxt\\src\\main\\webapp\\reception\\img\\service\\f1.jpg";
            MyFile.copyFile(backup, oldfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
