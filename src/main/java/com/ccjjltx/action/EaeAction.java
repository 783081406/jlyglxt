package com.ccjjltx.action;

import com.ccjjltx.dao.EaeDAO;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by ccjjltx on 2017/12/31.
 * 出入院的增删除改查
 *
 * @author ccj
 * @version 1.0
 */
public class EaeAction extends ActionSupport {
    @Resource(name = "eaeDAO")
    private EaeDAO eaeDAO;
    private int page;
    private int rows;
    private JSONObject result;

    ////////////提交过来的数据///////////
    private int eaeid;//主键
    private Date stime;//开始时间
    private Date etime;//结束时间
    private String ename;//名字

    public EaeDAO getEaeDAO() {
        return eaeDAO;
    }

    public void setEaeDAO(EaeDAO eaeDAO) {
        this.eaeDAO = eaeDAO;
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

    public int getEaeid() {
        return eaeid;
    }

    public void setEaeid(int eaeid) {
        this.eaeid = eaeid;
    }

    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    public Date getEtime() {
        return etime;
    }

    public void setEtime(Date etime) {
        this.etime = etime;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }



}
