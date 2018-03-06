package com.ccjjltx.action;

import com.ccjjltx.dao.UserDAO;
import com.ccjjltx.domain.User;
import com.ccjjltx.utils.JsonMessage;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/10/17.
 * 账户数据的增删改查
 *
 * @author ccj
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class UserAction extends ActionSupport {
    @Resource(name = "userDAO")
    private UserDAO userDAO;
    private int page;//得到页数（第几页）   最原始首次得到是默认是1，表示第一页
    private int rows;//得到行数（每行显示的行数）   最原始首次得到默认是10，表示10行显示
    private String userName;//得到的用户名
    private String password;//得到的密码
    private int uType;//得到的类型
    private int id;//得到的id号
    private JSONObject result;//保存json结果
    private String newpass;//新密码

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

    public String getNewpass() {
        return newpass;
    }

    public void setNewpass(String newpass) {
        this.newpass = newpass;
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
        List<User> list = userDAO.getAllUser(offset, getRows(), getUserName());
        //得到所有的User的总条数
        int total = userDAO.getAllUserNumber(getUserName());
        result = new JSONObject();
        result.put("total", total);
        //jsonarrya对象
        JSONArray jsonArray = new JSONArray();
        for (User user : list) {
            JSONObject js = new JSONObject();
            js.put("id", user.getId());
            js.put("userName", user.getUserName());
            js.put("password", user.getPassword());
            js.put("uType", user.getUType()==1?"管理员":"普通");
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
                result = JsonMessage.returnMessage(false, "类型只能填入1或2");
                return ERROR;
            case 2:
                result = JsonMessage.returnMessage(false, "数据库中已经存在相同的用户名了");
                return ERROR;
            default:
                result = JsonMessage.returnMessage(true, "success");
                return SUCCESS;
        }
    }

    /**
     * 更新信息
     *
     * @return json结果信息
     */
    public String updateUser() {
        if (!(getUType() == 1 || getUType() == 2)) {
            result = JsonMessage.returnMessage(false, "插入失败：类型必须是1或者2");
            return ERROR;
        }
        //实例化得到的信息
        User user = new User();
        user.setId(getId());
        user.setUserName(getUserName());
        user.setPassword(getPassword());
        user.setUType(getUType());
        //执行更新操作
        if (userDAO.updateUser(user)) {
            //如果返回true，表示更新成功执行if里面的语句
            result = JsonMessage.returnMessage(true, "success");
            return SUCCESS;
        } else {
            //如果返回false，表示更新失败，执行下面语句
            result = JsonMessage.returnMessage(false, "更新失败");
            return ERROR;
        }
    }

    /**
     * 删除功能
     *
     * @return 失败或成功
     */
    public String removeUser() {
        if (userDAO.deleteUser(getId())) {
            //如果成功执行删除语句执行以下语句
            result = JsonMessage.returnMessage(true, "success");
            return SUCCESS;
        } else {
            //执行删除语句失败执行以下方法
            result = JsonMessage.returnMessage(false, "删除失败");
            return ERROR;
        }
    }

    /**
     * 更新密码
     *
     * @return json数据
     */
    public String updatePassword() {
        User user = userDAO.searchUser((String) ActionContext.getContext().getSession().get("userName"));
        user.setPassword(getNewpass());
        //更新
        userDAO.updateUser(user);
        result = JsonMessage.returnMessage(true, "success");
        return null;
    }
}
