package com.ccjjltx.action;

import com.ccjjltx.dao.CasehistoryDAO;
import com.ccjjltx.domain.Casehistory;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/11/8.
 * 病例增删改查
 *
 * @author ccj
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class CasehistoryAction extends ActionSupport {
    @Resource(name = "casehistoryDAO")
    private CasehistoryDAO casehistoryDAO;
    //得到页数（第几页）
    //最原始首次得到是默认是1，表示第一页
    private int page;
    //得到行数（每行显示的行数）
    //最原始首次得到默认是10，表示10行显示
    private int rows;
    //保存json结果
    private JSONObject result;
    //////////////////提交过来的表中的字段/////////////////////////

    private String ename;

    //////////////////////////////////////////////////////////////
    public CasehistoryDAO getCasehistoryDAO() {
        return casehistoryDAO;
    }

    public void setCasehistoryDAO(CasehistoryDAO casehistoryDAO) {
        this.casehistoryDAO = casehistoryDAO;
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

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    /**
     * 得到去不或者特定的（搜索框触发）的信息
     *
     * @return 只返回SUCCESS
     */
    public String getAllInformation() {
        //得到起始的行数
        int offset = (getPage() - 1) * getRows();
        //得到所有的信息
        List<Casehistory> list = casehistoryDAO.getAllInformation(offset, getRows(), getEname());
        //得到总条数
        int total = casehistoryDAO.getAllInformationNumber(getEname());
        result = new JSONObject();
        result.put("total", total);
        //插入
        JSONArray jsonArray = new JSONArray();
        for (Casehistory ch : list) {
            JSONObject js = new JSONObject();
            js.put("chId", ch.getChId());
            js.put("ename", ch.getElder().getEname());
            js.put("height", ch.getHeight());
            js.put("weight", ch.getWeight());
            js.put("physician", ch.getPhysician());
            js.put("hospital", ch.getHospital());
            js.put("hospitalPhone", ch.getHospitalPhone());
            js.put("allergyDrugs", ch.getAllergyDrugs());
            js.put("majorDiseases", ch.getMajorDiseases());
            jsonArray.add(js);
        }
        result.put("rows", jsonArray);
        return SUCCESS;
    }


}
