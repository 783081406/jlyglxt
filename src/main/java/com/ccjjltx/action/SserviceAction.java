package com.ccjjltx.action;

import com.ccjjltx.dao.SserviceDAO;
import com.ccjjltx.domain.Sservice;
import com.ccjjltx.utils.JsonMessage;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/12/30.
 * 特殊服务的增删除改查
 *
 * @author ccj
 * @version 1.0
 */

@Controller
@Scope("prototype")
public class SserviceAction extends ActionSupport {
    @Resource(name = "sserviceDAO")
    private SserviceDAO sserviceDAO;
    private int page;
    private int rows;
    private JSONObject result;

    ////////////提交过来的数据///////////
    private int ssid;
    private String stype;//类型
    private String stime;//频率
    private String remark;//备注
    private String ename;//名字
    ////////////////////////////////////

    public SserviceDAO getSserviceDAO() {
        return sserviceDAO;
    }

    public void setSserviceDAO(SserviceDAO sserviceDAO) {
        this.sserviceDAO = sserviceDAO;
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

    public int getSsid() {
        return ssid;
    }

    public void setSsid(int ssid) {
        this.ssid = ssid;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        List<Sservice> list = sserviceDAO.getAllInformation(offset, getRows(), getEname());
        //得到总条数
        int total = sserviceDAO.getAllInformationNumber(getEname());
        result = new JSONObject();
        result.put("total", total);
        JSONArray jsonArray = new JSONArray();
        for (Sservice ss : list) {
            JSONObject js = new JSONObject();
            js.put("ssid", ss.getSsid());
            js.put("ename", ss.getElder().getEname());
            js.put("stype", ss.getStype());
            js.put("stime", ss.getStime());
            js.put("remark", ss.getRemark());
            jsonArray.add(js);
        }
        result.put("rows", jsonArray);
        return SUCCESS;
    }

    /**
     * 用于增加特别服务信息
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
        Sservice sservice = new Sservice(getStype(), getStime(), getRemark());
        int thisRusult = sserviceDAO.addInformation(sservice, eId);
        if (thisRusult == 1) {
            result = JsonMessage.returnMessage(false, "该名老人的数据已经存在");
            return ERROR;
        } else {
            result = JsonMessage.returnMessage(true, "success");
            return SUCCESS;
        }
    }

    /**
     * 更新信息
     *
     * @return json数据
     */
    public String updateInformation() {
        //根据主键得到信息
        Sservice sservice = sserviceDAO.getSearchSservice(getSsid());
        //更新数据
        sservice.setStype(getStype());
        sservice.setStime(getStime());
        sservice.setRemark(getRemark());
        //更新操作
        sserviceDAO.updateInformation(sservice);
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }

    /**
     * 删除功能
     *
     * @return 返回成功
     */
    public String removeInformation() {
        sserviceDAO.deleteInformation(getSsid());
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }
}
