package com.ccjjltx.action;

import com.ccjjltx.dao.UserDAO;
import com.ccjjltx.domain.User;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ccjjltx on 2017/10/17.
 * 用户数据的增删改查
 *
 * @author ccj
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class UserAction extends ActionSupport {
    @Resource(name = "userDAO")
    private UserDAO userDAO;
    //得到页数（第几页）
    //最原始首次得到是默认是1，表示第一页
    private int page;
    //得到行数（每行显示的行数）
    //最原始首次得到默认是10，表示10行显示
    private int rows;
    //得到的用户名
    private String userName;
    //得到的密码
    private String password;
    //得到的类型
    private int uType;
    //得到的id号
    private int id;
    //保存json结果
    private JSONObject result;

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

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

    public int getuType() {
        return uType;
    }

    public void setuType(int uType) {
        this.uType = uType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

    /**
     * 得到所有的用户信息
     *
     * @return success
     */
    public String getUser() {
        //得到起始的行数，方便之后的hql语句
        int offset = (getPage() - 1) * getRows();
        //得到所有的User
        List<User> list = userDAO.getAllUser(offset, getRows());
        //得到所有的User的总条数
        int total = userDAO.getAllUserNumber();
        result = new JSONObject();
        result.put("total", total);
        //jsonarrya对象
        JSONArray jsonArray = new JSONArray();
        for (User user : list) {
            JSONObject js = new JSONObject();
            js.put("id", user.getId());
            js.put("userName", user.getUserName());
            js.put("password", user.getPassword());
            js.put("uType", user.getuType());
            jsonArray.add(js);
        }
        result.put("rows", jsonArray);
        return SUCCESS;
    }

    public String saveUser() {
        System.out.println(getUserName());
        result = new JSONObject();
        result.put("success", true);
        return SUCCESS;
    }

    public String updateUser() {
        return SUCCESS;
    }

    public String removeUser() {
        return SUCCESS;
    }

}
