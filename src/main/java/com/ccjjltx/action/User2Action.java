package com.ccjjltx.action;

import com.ccjjltx.dao.UserDAO;
import com.ccjjltx.domain.User;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/10/21.
 * 下拉框选择用户账户使用
 *
 * @author ccj
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class User2Action extends ActionSupport {
    private JSONArray result;
    @Resource(name = "userDAO")
    private UserDAO userDAO;

    public JSONArray getResult() {
        return result;
    }

    public void setResult(JSONArray result) {
        this.result = result;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * 下拉框选择用户账户使用
     * 场景：员工中心->员工信息
     *
     * @return 设置为SUCCESS
     */
    public String getIdUserName() {
        result = new JSONArray();
        List<User> list = userDAO.getAllUser();
        for (User user : list) {
            JSONObject js = new JSONObject();
            js.put("id", user.getId());
            js.put("userName", user.getUserName());
            result.add(js);
        }
        return SUCCESS;
    }
}
