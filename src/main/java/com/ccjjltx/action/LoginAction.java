package com.ccjjltx.action;

import com.ccjjltx.dao.EinformationDAO;
import com.ccjjltx.dao.UserDAO;
import com.ccjjltx.domain.Einformation;
import com.ccjjltx.domain.User;
import com.opensymphony.xwork2.ActionContext;
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
    //EinformationDAO
    @Resource(name = "einformationDAO")
    private EinformationDAO einformationDAO;

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
    public String checkForntLogin() {
        if (getUserName() != null && getPassword() != null) {
            int iResult = userDAO.checkUser(getUserName(), getPassword());
            switch (iResult) {
                case 1:
                    setError("用户名错误！");
                    return ERROR;
                case 2:
                    setError("密码错误！");
                    return ERROR;
                default:
                    setSession();
                    return SUCCESS;
            }
        }
        return "first";
    }

    /**
     * 设置错误信息
     *
     * @param message 错误信息
     */
    public void setError(String message) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        JSONObject json = JSONObject.fromObject(map);//将map对象转换成json类型数据
        result = json.toString();//给result赋值，传递给页面
    }

    //验证通过的时候设置Session
    public void setSession() {
        ActionContext.getContext().getSession().put("userName", getUserName());//账号
        User db_user = userDAO.searchUser(getUserName());
        ActionContext.getContext().getSession().put("uType", db_user.getUType());//类型
        Einformation einformation = einformationDAO.getSearchEinformation(db_user);
        ActionContext.getContext().getSession().put("name", einformation.getName());//名字
    }

    //退出登录删除Session
    public void removeSession() {
        ActionContext.getContext().getSession().remove("userName");
        ActionContext.getContext().getSession().remove("uType");
        ActionContext.getContext().getSession().remove("name");
    }
}
