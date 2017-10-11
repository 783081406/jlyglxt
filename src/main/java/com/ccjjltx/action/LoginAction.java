package com.ccjjltx.action;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * Created by ccjjltx on 2017/10/11.
 * 登录
 *
 * @author ccj
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class LoginAction extends ActionSupport {
    //登录的用户名
    private String userName;
    //登录的密码
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 验证用户登录
     *
     * @return 返回success或者error，分别打包成json和redict
     */
    public String CheckForntLogin() {
        System.out.println(getUserName());
        System.out.println(getPassword());
        return SUCCESS;
    }
}
