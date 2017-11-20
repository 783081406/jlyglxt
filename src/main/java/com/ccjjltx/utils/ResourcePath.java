package com.ccjjltx.utils;

/**
 * Created by ccjjltx on 2017/11/20.
 * 将java路径转为html或window能识别的路径
 *
 * @author ccj
 * @version 1.0
 */
public class ResourcePath {
    //将构造器私有化，不可实例，工具类，单例模式
    private ResourcePath() {

    }

    public static String parse(String str) {
        return str.replace("\\\\", "\\");
    }
}
