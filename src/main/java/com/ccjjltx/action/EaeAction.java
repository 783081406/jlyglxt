package com.ccjjltx.action;

import com.ccjjltx.dao.EaeDAO;
import com.ccjjltx.domain.Eae;
import com.ccjjltx.utils.JsonMessage;
import com.ccjjltx.utils.MyDateFormat;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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

    /**
     * 得到全部或特定(搜索框触发)的特殊服务数据
     *
     * @return 只返回SUCCESS
     */
    public String getAllInformation() {
        int offset = (getPage() - 1) * getRows();
        List<Eae> list = eaeDAO.getAllInformation(offset, getRows(), getEname());
        //得到总条数
        int total = eaeDAO.getAllInformationNumber(getEname());
        result = new JSONObject();
        result.put("total", total);
        JSONArray jsonArray = new JSONArray();
        for (Eae eae : list) {
            JSONObject js = new JSONObject();
            js.put("eaeid", eae.getEaeid());
            js.put("ename", eae.getElder().getEname());
            js.put("stime", MyDateFormat.format(eae.getStime()));
            js.put("etime", MyDateFormat.format(eae.getEtime()));
            jsonArray.add(js);
        }
        result.put("rows", jsonArray);
        return SUCCESS;
    }

    /**
     * 用于增加出入院信息
     *
     * @return json, 成功或者失败的提示信息
     */
    public String addInformation() {
        int eId = -1;
        try {
            eId = Integer.parseInt(getEname());
        } catch (NumberFormatException e) {
            result = JsonMessage.returnMessage(false, "请输入有效姓名");
            return ERROR;
        }
        Eae eae = new Eae(getStime(), getEtime());
        int thisRusult = eaeDAO.addInformation(eae, eId);
        if (thisRusult != 1) {
            result = JsonMessage.returnMessage(true, "success");
            return SUCCESS;
        } else {
            result = JsonMessage.returnMessage(false, "该名老人的数据已经存在");
            return ERROR;
        }
    }

    /**
     * 更新信息
     *
     * @return json数据
     */
    public String updateInformation() {
        //根据主键得到信息
        Eae eae = eaeDAO.getSearchEae(getEaeid());
        //更新数据
        eae.setStime(getStime());
        eae.setEtime(getEtime());
        //更新操作
        eaeDAO.updateInformation(eae);
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }

    /**
     * 删除功能
     *
     * @return 返回成功
     */
    public String removeInformation() {
        eaeDAO.deleteInformation(getEaeid());
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }
}
