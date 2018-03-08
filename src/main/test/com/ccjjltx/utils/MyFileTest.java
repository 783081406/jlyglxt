package com.ccjjltx.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * Created by ccjjltx on 2017/11/20.
 * MyFile工具类
 *
 * @author ccj
 * @version 1.0
 */
public class MyFileTest {
    /**
     * 验证：是否具有复制文件功能
     */
    @Test
    public void testCopyFile() {
        File file = new File("E:\\123\\456\\4.jpg");
        Assert.assertFalse(file.exists());//断言不存在
        MyFile.copyFile("E:\\123\\4.jpg", "E:\\123\\456\\4.jpg");//执行复制
        Assert.assertTrue(file.exists());//复制之后存在
        //为了方便下次测试，删除456下的图片
        MyFile.deleteFile("E:\\123\\456\\4.jpg");
    }

    /**
     * 验证：是否具有删除功能
     */
    @Test
    public void testDeleteFile() {
        MyFile.copyFile("E:\\123\\789\\5backup,jpg", "E:\\123\\789\\5.jpg");//执行复制
        MyFile.deleteFile("E:\\123\\789\\5.jpg");
        File file = new File("E:\\123\\789\\5.jpg");
        Assert.assertFalse(file.exists());//断言不存在
    }

}
