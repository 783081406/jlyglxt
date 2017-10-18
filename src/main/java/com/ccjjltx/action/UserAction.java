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

    public int getUType() {
        return uType;
    }

    public void setUType(int uType) {
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

    /**
     * 增加User
     *
     * @return Json，成功或者失败的数据
     */
    public String saveUser() {
        //执行UserDao的增加User方法，返回数字，1,2或3，分别表示uType输入错误，已经存在相同用户名，插入成功
        int queryResult = userDAO.addUser(getUserName(), getPassword(), getUType());
        switch (queryResult) {
            case 1:
                result = returnMessage(false, "类型只能填入1或2");
                return ERROR;
            case 2:
                result = returnMessage(false, "数据库中已经存在相同的用户名了");
                return ERROR;
            default:
                result = returnMessage(true, "success");
                return SUCCESS;
        }
    }

    /**
     * 更新信息
     *
     * @return json结果信息
     */
    public String updateUser() {
        //实例化得到的信息
        User user = new User();
        user.setId(getId());
        user.setUserName(getUserName());
        user.setPassword(getPassword());
        user.setuType(getUType());
        //执行更新操作
        if (userDAO.updateUser(user)) {
            //如果返回true，表示更新成功执行if里面的语句
            result = returnMessage(true, "success");
            return SUCCESS;
        } else {
            //如果返回false，表示更新失败，执行下面语句
            result = returnMessage(false, "更新失败");
            return ERROR;
        }
    }

    public String removeUser() {
        return SUCCESS;
    }

    /**
     * 设置json返回的结果值处理中心
     *
     * @param trueOfFalse 表示是否是成功true，表示成功，false表示失败
     * @param message     表示返回的结果信息
     * @return Json对象
     */
    public JSONObject returnMessage(boolean trueOfFalse, String message) {
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
}
