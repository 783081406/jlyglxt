package com.ccjjltx.utils;

import org.junit.Test;

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
        MyFile.copyFile("E:\\123\\4.jpg", "E:\\123\\456\\4.jpg");
    }

    /**
     * 验证：是否具有删除功能
     */
    @Test
    public void testDeleteFile() {
        MyFile.deleteFile("F:\\4.jpg");
    }

}
