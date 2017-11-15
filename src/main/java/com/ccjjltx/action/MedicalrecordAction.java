package com.ccjjltx.action;

import com.ccjjltx.dao.CasehistoryDAO;
import com.ccjjltx.dao.MedicalrecordDAO;
import com.ccjjltx.domain.Medicalrecord;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/11/8.
 * 就医记录增删查改
 *
 * @author ccj
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class MedicalrecordAction extends ActionSupport {
    @Resource(name = "medicalrecordDAO")
    private MedicalrecordDAO medicalrecordDAO;
    @Resource(name = "casehistoryDAO")
    private CasehistoryDAO casehistoryDAO;

    private int page;
    private int rows;
    private JSONObject result;
    ///////////////////提交过来的字段数据/////////////////////
    private int chId;
    private int mrId;
    private String mrpPlace;
    private String medicalDoctor;
    private String diagnosisResult;
    private String advice;

    ////////////////////////////////////////////////////
    public MedicalrecordDAO getMedicalrecordDAO() {
        return medicalrecordDAO;
    }

    public void setMedicalrecordDAO(MedicalrecordDAO medicalrecordDAO) {
        this.medicalrecordDAO = medicalrecordDAO;
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

    public int getMrId() {
        return mrId;
    }

    public void setMrId(int mrId) {
        this.mrId = mrId;
    }

    public String getMrpPlace() {
        return mrpPlace;
    }

    public void setMrpPlace(String mrpPlace) {
        this.mrpPlace = mrpPlace;
    }

    public String getMedicalDoctor() {
        return medicalDoctor;
    }

    public void setMedicalDoctor(String medicalDoctor) {
        this.medicalDoctor = medicalDoctor;
    }

    public String getDiagnosisResult() {
        return diagnosisResult;
    }

    public void setDiagnosisResult(String diagnosisResult) {
        this.diagnosisResult = diagnosisResult;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    /**
     * 得到全部的就医记录
     *
     * @return Json数据
     */
    public String getAllInformation() {
        //得到所有关于该chId的就医记录
        List<Medicalrecord> list = medicalrecordDAO.getAllInformation(getChId());
        result = new JSONObject();
        result.put("total", list.size());
        //JsonArray对象
        JSONArray jsonArray = new JSONArray();
        for (Medicalrecord mc : list) {
            JSONObject js = new JSONObject();
            js.put("mrId", mc.getMrId());
            js.put("mrpPlace", mc.getMrpPlace());
            js.put("medicalDoctor", mc.getMedicalDoctor());
            js.put("diagnosisResult", mc.getDiagnosisResult());
            js.put("advice", mc.getAdvice());
            jsonArray.add(js);
        }
        result.put("rows", jsonArray);
        return SUCCESS;
    }


}
