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
    //基本特征
    private String ename;
    private String height;
    private String weight;
    private String physician;
    private String hospital;
    private String hospitalName;
    private String allergyDrugs;
    private String majorDiseases;
    //血压血氧
    private String highHanded;
    private String lowHanded;
    private String bloodOxygenValue;
    //血糖信息
    private String fastingPlasmaGlucose;
    private String postprandialBoolGlucose;
    //血脂信息
    private String totalCholesterol;
    private String triglyceride;
    private String hdlc;
    private String ldlc;

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

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPhysician() {
        return physician;
    }

    public void setPhysician(String physician) {
        this.physician = physician;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getAllergyDrugs() {
        return allergyDrugs;
    }

    public void setAllergyDrugs(String allergyDrugs) {
        this.allergyDrugs = allergyDrugs;
    }

    public String getMajorDiseases() {
        return majorDiseases;
    }

    public void setMajorDiseases(String majorDiseases) {
        this.majorDiseases = majorDiseases;
    }

    public String getHighHanded() {
        return highHanded;
    }

    public void setHighHanded(String highHanded) {
        this.highHanded = highHanded;
    }

    public String getLowHanded() {
        return lowHanded;
    }

    public void setLowHanded(String lowHanded) {
        this.lowHanded = lowHanded;
    }

    public String getBloodOxygenValue() {
        return bloodOxygenValue;
    }

    public void setBloodOxygenValue(String bloodOxygenValue) {
        this.bloodOxygenValue = bloodOxygenValue;
    }

    public String getFastingPlasmaGlucose() {
        return fastingPlasmaGlucose;
    }

    public void setFastingPlasmaGlucose(String fastingPlasmaGlucose) {
        this.fastingPlasmaGlucose = fastingPlasmaGlucose;
    }

    public String getPostprandialBoolGlucose() {
        return postprandialBoolGlucose;
    }

    public void setPostprandialBoolGlucose(String postprandialBoolGlucose) {
        this.postprandialBoolGlucose = postprandialBoolGlucose;
    }

    public String getTotalCholesterol() {
        return totalCholesterol;
    }

    public void setTotalCholesterol(String totalCholesterol) {
        this.totalCholesterol = totalCholesterol;
    }

    public String getTriglyceride() {
        return triglyceride;
    }

    public void setTriglyceride(String triglyceride) {
        this.triglyceride = triglyceride;
    }

    public String getHdlc() {
        return hdlc;
    }

    public void setHdlc(String hdlc) {
        this.hdlc = hdlc;
    }

    public String getLdlc() {
        return ldlc;
    }

    public void setLdlc(String ldlc) {
        this.ldlc = ldlc;
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

    /**
     * 根据所有的
     *
     * @return
     */
    public String getAllInformation2() {
        return SUCCESS;
    }

}
