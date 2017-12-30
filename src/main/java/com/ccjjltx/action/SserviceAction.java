package com.ccjjltx.action;

import com.ccjjltx.dao.SserviceDAO;
import com.ccjjltx.domain.Sservice;
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
}
