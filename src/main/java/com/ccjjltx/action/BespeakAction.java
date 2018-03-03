package com.ccjjltx.action;

import com.ccjjltx.dao.BespeakDAO;
import com.ccjjltx.domain.Bespeak;
import com.ccjjltx.utils.JsonMessage;
import com.ccjjltx.utils.MyDateFormat;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by ccjjltx on 2017/11/22.
 * 体验预约相关功能
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

    /**
     * 得到所有已处理的数据
     *
     * @return list数据
     */
    public String getAllHandleInformation() {
        int offset = (getPage() - 1) * getRows();
        List<Bespeak> list = bespeakDAO.getAllHandleInformation(offset, getRows());
        int total = bespeakDAO.getAllHandleInformationNumber();
        result = new JSONObject();
        result.put("total", total);
        JSONArray jsonArray = new JSONArray();
        for (Bespeak bs : list) {
            JSONObject js = new JSONObject();
            js.put("bid", bs.getBid());
            js.put("name", bs.getName());
            js.put("phone", bs.getPhone());
            js.put("ename", bs.getEname());
            js.put("eage", bs.getEage());
            js.put("handleUser", bs.getHandleUser());
            js.put("submitDate", MyDateFormat.format(bs.getSubmitDate()));
            js.put("handleDate", MyDateFormat.format(bs.getHandleDate()));
            jsonArray.add(js);
        }
        result.put("rows", jsonArray);
        return SUCCESS;
    }

    /**
     * 得到所有未处理的数据
     *
     * @return list数据
     */
    public String getAllUnhandleInformation() {
        int offset = (getPage() - 1) * getRows();
        List<Bespeak> list = bespeakDAO.getAllUnhandleInformation(offset, getRows());
        int total = bespeakDAO.getAllUnhandleInformationNumber();
        result = new JSONObject();
        result.put("total", total);
        JSONArray jsonArray = new JSONArray();
        for (Bespeak bs : list) {
            JSONObject js = new JSONObject();
            js.put("bid", bs.getBid());
            js.put("name", bs.getName());
            js.put("phone", bs.getPhone());
            js.put("ename", bs.getEname());
            js.put("eage", bs.getEage());
            js.put("submitDate", MyDateFormat.format(bs.getSubmitDate()));
            jsonArray.add(js);
        }
        result.put("rows", jsonArray);
        return SUCCESS;
    }

    /**
     * 提交过来的处理信息
     *
     * @return Json，成功或失败的信息
     */
    public String handleInformation() {
        //得到实例化
        Bespeak bs = bespeakDAO.getSearchInformation(getBid());
        //修改状态
        bs.setIshandle(1);
        //添加处理人与时间
        bs.setHandleUser((String) ActionContext.getContext().getSession().get("userName"));
        bs.setHandleDate(new Date());
        //更新
        bespeakDAO.handleInformation(bs);
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }

    /**
     * 新增
     *
     * @return 前台首页
     */
    public String saveInformation() {
        Bespeak bespeak = new Bespeak(getName(), getPhone(), getEname(), getEage(), new Date());
        bespeakDAO.saveInformation(bespeak);
        return "reception";
    }

}
