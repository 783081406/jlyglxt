package com.ccjjltx.action;

import com.ccjjltx.dao.BespeakDAO;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by ccjjltx on 2017/11/22.
 * 体检预约相关功能
 *
 * @author ccj
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class BespeakAction extends ActionSupport {
    @Resource(name = "bespeakDAO")
    private BespeakDAO bespeakDAO;
    private int page;
    private int rows;
    private JSONObject result;
    ///////////////////////////提交过来的数据///////////////////
    private int bid;
    private String name;
    private String phone;
    private String ename;
    private Integer eage;

    //////////////////////////////////////////////////////////
    public BespeakDAO getBespeakDAO() {
        return bespeakDAO;
    }

    public void setBespeakDAO(BespeakDAO bespeakDAO) {
        this.bespeakDAO = bespeakDAO;
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

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Integer getEage() {
        return eage;
    }

    public void setEage(Integer eage) {
        this.eage = eage;
    }

}
