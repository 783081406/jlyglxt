package com.ccjjltx.action;

import com.ccjjltx.dao.EinformationDAO;
import com.ccjjltx.dao.UserDAO;
import com.ccjjltx.domain.Einformation;
import com.ccjjltx.domain.User;
import com.ccjjltx.utils.JsonMessage;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

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
    @Resource(name = "userDAO")
    private UserDAO userDAO;
    @Resource(name = "einformationDAO")
    private EinformationDAO einformationDAO;
    private JSONObject result;//验证的结果信息如果错误将会返回给首页

    ///////////////////////////////////////////////////////////////////
    private String userName;//登录的用户名
    private String password;//登录的密码
    //////////////////////////////////////////////////////////////////

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

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
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
                    result = JsonMessage.returnMessage2(false, "用户名错误！");
                    return ERROR;
                case 2:
                    result = JsonMessage.returnMessage2(false, "密码错误！");
                    return ERROR;
                default:
                    setSession();
                    result = JsonMessage.returnMessage2(true, "success");
                    return SUCCESS;
            }
        }
        return "first";
    }

    /**
     * 判断是否可以跳转到后台首页
     * 登录
     *
     * @return Success或Error
     */
    public String signIn() {
        String tempUserName = (String) ActionContext.getContext().getSession().get("userName");
        if (tempUserName != null) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    /**
     * 退出
     *
     * @return Success
     */
    public String signOut() {
        removeSession();//删除Session
        return SUCCESS;
    }

    //验证通过的时候设置Session
    private void setSession() {
        ActionContext.getContext().getSession().put("userName", getUserName());//账号
        User db_user = userDAO.searchUser(getUserName());
        ActionContext.getContext().getSession().put("uType", db_user.getUType());//类型
        Einformation einformation = einformationDAO.getSearchEinformation(db_user);
        ActionContext.getContext().getSession().put("name", einformation.getName());//名字
    }

    //退出登录删除Session
    private void removeSession() {
        ActionContext.getContext().getSession().remove("userName");
        ActionContext.getContext().getSession().remove("uType");
        ActionContext.getContext().getSession().remove("name");
    }
}
