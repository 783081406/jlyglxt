package com.ccjjltx.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

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
    public void testCopyFile1() {
        try {
            MyFile myFile = new MyFile();//弥补计算规则出错
            File file = new File("E:\\123\\456\\4.jpg");
            Assert.assertFalse(file.exists());//断言不存在
            MyFile.copyFile("E:\\123\\4.jpg", "E:\\123\\456\\4.jpg");//执行复制
            Assert.assertTrue(file.exists());//复制之后存在
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证：是否具有删除功能
     */
    @Test
    public void testDeleteFile() {
        try {
            MyFile.copyFile("E:\\123\\789\\5backup,jpg", "E:\\123\\789\\5.jpg");//执行复制
            MyFile.deleteFile("E:\\123\\789\\5.jpg");
            File file = new File("E:\\123\\789\\5.jpg");
            Assert.assertFalse(file.exists());//断言不存在
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
