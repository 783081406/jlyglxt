package com.ccjjltx.action;

import com.ccjjltx.dao.UserDAO;
import com.ccjjltx.domain.User;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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
    //验证的结果信息如果错误将会返回给首页
    private String result;
    //得到UserDAO类
    @Resource(name = "userDAO")
    private UserDAO userDAO;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 验证用户登录
     *
     * @return 返回success或者error，分别打包成json和redict
     */
    public String CheckForntLogin() {
        if (getUserName() != null && getPassword() != null) {
            User user = new User(getUserName(), getPassword());
            int iResult = userDAO.checkUser(user);
            System.out.println(2);
            switch (iResult) {
                case 1:
                    setError("用户名错误！");
                    return ERROR;
                case 2:
                    setError("密码错误！");
                    return ERROR;
                default:
                    return SUCCESS;
            }
        }
        return "first";
    }

    public void setError(String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
        result = json.toString();//给result赋值，传递给页面
    }
}
