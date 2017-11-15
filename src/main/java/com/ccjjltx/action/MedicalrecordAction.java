package com.ccjjltx.action;

import com.ccjjltx.dao.CasehistoryDAO;
import com.ccjjltx.dao.MedicalrecordDAO;
import com.ccjjltx.domain.Casehistory;
import com.ccjjltx.domain.Medicalrecord;
import com.ccjjltx.utils.JsonMessage;
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

    /**
     * 增加就医记录
     *
     * @return Json，成功或者失败提示信息
     */
    public String saveInformation() {
        Medicalrecord mc = new Medicalrecord(getMrpPlace(), getMedicalDoctor(), getDiagnosisResult(), getAdvice());
        //得到病历的实例化
        Casehistory ch = casehistoryDAO.getSearchInformation(getChId());
        mc.setCasehistory(ch);
        //执行插入操作
        medicalrecordDAO.addInformation(mc);
        //返回Json信息
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }

    /**
     * 更新操作
     *
     * @return Json，成功或者失败数据
     */
    public String updateInformation() {
        //得到就医记录的实例化
        Medicalrecord mc = medicalrecordDAO.getSearchEinformation(getMrId());
        //更新信息
        mc.setMrpPlace(getMrpPlace());
        mc.setMedicalDoctor(getMedicalDoctor());
        mc.setDiagnosisResult(getDiagnosisResult());
        mc.setAdvice(getAdvice());
        //执行更新操作
        medicalrecordDAO.updateInformation(mc);
        //返回json提示信息
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }

    /**
     * 删除功能
     *
     * @return JSON，返回成功或失败的提示信息
     */
    public String removeInformation() {
        //删除操作
        medicalrecordDAO.deleteInformation(getMrId());
        //返回删除成功的信息
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }


}
