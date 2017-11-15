package com.ccjjltx.action;

import com.ccjjltx.dao.CasehistoryDAO;
import com.ccjjltx.dao.EcginformationDAO;
import com.ccjjltx.domain.Casehistory;
import com.ccjjltx.domain.Ecginformation;
import com.ccjjltx.utils.JsonMessage;
import com.ccjjltx.utils.MyDateFormat;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by ccjjltx on 2017/11/8.
 * 心电信息增删改查
 *
 * @author ccj
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class EcginformationAction extends ActionSupport {
    @Resource(name = "ecginformationDAO")
    private EcginformationDAO ecginformationDAO;
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
    ///////////////////提交过来的字段数据/////////////////////
    private int chId;
    private int ecgId;
    private String qrs;
    private String comment;
    private String rr;
    private String analysisResult;
    private String rhythm;
    private Date rdate;
    ////////////////////////////////////////////////////

    public EcginformationDAO getEcginformationDAO() {
        return ecginformationDAO;
    }

    public void setEcginformationDAO(EcginformationDAO ecginformationDAO) {
        this.ecginformationDAO = ecginformationDAO;
    }

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

    public int getChId() {
        return chId;
    }

    public void setChId(int chId) {
        this.chId = chId;
    }

    public int getEcgId() {
        return ecgId;
    }

    public void setEcgId(int ecgId) {
        this.ecgId = ecgId;
    }

    public String getQrs() {
        return qrs;
    }

    public void setQrs(String qrs) {
        this.qrs = qrs;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRr() {
        return rr;
    }

    public void setRr(String rr) {
        this.rr = rr;
    }

    public String getAnalysisResult() {
        return analysisResult;
    }

    public void setAnalysisResult(String analysisResult) {
        this.analysisResult = analysisResult;
    }

    public String getRhythm() {
        return rhythm;
    }

    public void setRhythm(String rhythm) {
        this.rhythm = rhythm;
    }

    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }

    /**
     * 得到全部心电信息
     *
     * @return Json数据
     */
    public String getAllInformation() {
        //得到所有关于该chId的心电信息
        List<Ecginformation> list = ecginformationDAO.getAllInformation(getChId());
        result = new JSONObject();
        result.put("total", list.size());
        //jsonArray对象
        JSONArray jsonArray = new JSONArray();
        for (Ecginformation ec : list) {
            JSONObject js = new JSONObject();
            js.put("ecgId", ec.getEcgId());
            js.put("qrs", ec.getQrs());
            js.put("comment", ec.getComment());
            js.put("rr", ec.getRr());
            js.put("analysisResult", ec.getAnalysisResult());
            js.put("rhythm", ec.getRhythm());
            js.put("rdate", MyDateFormat.format(ec.getRdate()));
            jsonArray.add(js);
        }
        result.put("rows", jsonArray);
        return SUCCESS;
    }

    /**
     * 新增心电信息
     *
     * @return JSON，成功或者失败的提示信息
     */
    public String saveInformation() {
        Ecginformation ec = new Ecginformation(getQrs(), getComment(), getRr(), getAnalysisResult(), getRhythm(), getRdate());
        //得到病历的实例化
        Casehistory casehistory = casehistoryDAO.getSearchInformation(getChId());
        ec.setCasehistory(casehistory);
        //执行更新操作
        ecginformationDAO.addInformation(ec);
        //返回Json信息
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }

    /**
     * 更新信息
     *
     * @return JSON，成功或失败的信息提示
     */
    public String updateInformation() {
        //得到心电信息的实例化
        Ecginformation db_ec = ecginformationDAO.getSearchEinformation(getEcgId());
        //更新信息
        db_ec.setQrs(getQrs());
        db_ec.setComment(getComment());
        db_ec.setRr(getRr());
        db_ec.setAnalysisResult(getAnalysisResult());
        db_ec.setRhythm(getRhythm());
        db_ec.setRdate(getRdate());
        //执行更新操作
        ecginformationDAO.updateInformation(db_ec);
        //返回json提示信息
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }

    /**
     * 删除功能
     *
     * @return Json，返回成功或失败的提示信息
     */
    public String removeInformation() {
        //删除操作
        ecginformationDAO.deleteInformation(getEcgId());
        //返回删除成功的信息
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }
}
