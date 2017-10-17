package com.ccjjltx.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by ccjjltx on 2017/10/17.
 * 进入后台首页的过滤器
 *
 * @author ccj
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class HomeAction extends ActionSupport {
    public String checkValidity() {
        //验证合法性，是否是经过登录页面登录进来的还是非法进入的
        //如果不为空表示判断过了，可以进入
        if ((String) ActionContext.getContext().getSession().get("userName") != null) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }
}
