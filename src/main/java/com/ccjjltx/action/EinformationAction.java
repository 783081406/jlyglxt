package com.ccjjltx.action;

import com.ccjjltx.dao.EinformationDAO;
import com.ccjjltx.domain.Einformation;
import com.ccjjltx.utils.MyDateFormat;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

/**
 * Created by ccjjltx on 2017/10/19.
 * 员工信息增删改查
 *
 * @author ccj
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class EinformationAction extends ActionSupport {

    @Resource(name = "einformationDAO")
    private EinformationDAO einformationDAO;
    //得到页数（第几页）
    //最原始首次得到是默认是1，表示第一页
    private int page;
    //得到行数（每行显示的行数）
    //最原始首次得到默认是10，表示10行显示
    private int rows;
    //保存json结果
    private JSONObject result;
    /////////////提交过来的Einformation表中的字段////////////////
    private String name;
    private String idCard;
    private String sex;
    private String address;
    private String pType;
    private Date hiredate;
    private String education;
    private Date birthday;
    private String userName;
    ////////////////////////////////////////////////////////////

    public EinformationDAO getEinformationDAO() {
        return einformationDAO;
    }

    public void setEinformationDAO(EinformationDAO einformationDAO) {
        this.einformationDAO = einformationDAO;
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

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPType() {
        return pType;
    }

    public void setPType(String pType) {
        this.pType = pType;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 得到全部或者特地（搜索框触发）的员工信息数据
     *
     * @return 只返回SUCCESS，因前台页面没有json的success或error信息过滤处理
     */
    public String getAllInformation() {
        //得到起始的行数，方便之后的hql语句
        int offset = (getPage() - 1) * getRows();
        //不为空表示搜索按钮触发
        //如果是空数据赋值为null,方便后面的dao层的判断
        if (getName() != null && getName().equals("")) {
            setName(null);
        }
        if (getUserName() != null && getUserName().equals("")) {
            setUserName(null);
        }
        //得到所有的员工信息
        List<Einformation> list = einformationDAO.getAllInformation(offset, getRows(), getName(), getUserName());
        //得到总条数
        int total = einformationDAO.getAllInformationNumber(getName(), getUserName());
        result = new JSONObject();
        result.put("total", total);
        //jsonarrya对象
        JSONArray jsonArray = new JSONArray();
        for (Einformation ei : list) {
            JSONObject js = new JSONObject();
            js.put("pid", ei.getPid());
            js.put("name", ei.getName());
            js.put("idCard", ei.getIdCard());
            js.put("sex", ei.getSex());
            js.put("address", ei.getAddress());
            js.put("pType", ei.getPType());
            js.put("hiredate", MyDateFormat.format(ei.getHiredate()));
            js.put("education", ei.getEducation());
            js.put("birthday", MyDateFormat.format(ei.getBirthday()));
            js.put("userName", ei.getUser().getUserName());
            jsonArray.add(js);
        }
        result.put("rows", jsonArray);
        return SUCCESS;
    }
}
