package com.ccjjltx.utils;

import net.sf.json.JSONObject;

/**
 * Created by ccjjltx on 2017/10/21.
 * 工具类，无构造器
 * 作用：将成功与错误信息封装成JsonObject
 *
 * @author ccj
 * @version 1.0
 */
public class JsonMessage {
    private JsonMessage() {
    }

    /**
     * 设置json返回的结果值处理中心
     *
     * @param trueOfFalse 表示是否是成功true，表示成功，false表示失败
     * @param message     表示返回的结果信息
     * @return Json对象
     */
    public static JSONObject returnMessage(boolean trueOfFalse, String message) {
        JSONObject js = new JSONObject();
        if (trueOfFalse) {
            //表示成功
            js.put("success", true);
        } else {
            //表示失败
            js.put("msg", message);
        }
        return js;
    }

    /**
     * 封装json
     *
     * @param code 状态码
     * @param msg  反馈的信息
     * @return json
     */
    public static JSONObject returnMessage2(boolean code, String msg) {
        JSONObject js = new JSONObject();
        js.put("code", code);
        js.put("msg", msg);
        return js;
    }
}
