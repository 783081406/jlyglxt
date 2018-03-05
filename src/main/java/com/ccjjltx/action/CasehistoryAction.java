package com.ccjjltx.action;

import com.ccjjltx.dao.CasehistoryDAO;
import com.ccjjltx.domain.Casehistory;
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
    private int page;//得到页数（第几页） 最原始首次得到是默认是1，表示第一页
    private int rows;//得到行数（每行显示的行数） 最原始首次得到默认是10，表示10行显示

    private JSONObject result;//保存json结果
    //////////////////提交过来的表中的字段/////////////////////////
    private int chId;
    //基本特征
    private String ename;
    private String height;
    private String weight;
    private String physician;
    private String hospital;
    private String hospitalPhone;
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

    public String getHospitalPhone() {
        return hospitalPhone;
    }

    public void setHospitalPhone(String hospitalPhone) {
        this.hospitalPhone = hospitalPhone;
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
     * 返回所有的数据
     *
     * @return json数据
     */
    public String getAllInformation2() {
        Casehistory ch = casehistoryDAO.getSearchInformation(getChId());
        result = new JSONObject();
        result.put("chId", ch.getChId());
        result.put("ename", ch.getElder().getEId());
        result.put("height", ch.getHeight());
        result.put("weight", ch.getWeight());
        result.put("physician", ch.getPhysician());
        result.put("hospital", ch.getHospital());
        result.put("hospitalPhone", ch.getHospitalPhone());
        result.put("allergyDrugs", ch.getAllergyDrugs());
        result.put("majorDiseases", ch.getMajorDiseases());
        result.put("highHanded", ch.getHighHanded());
        result.put("lowHanded", ch.getLowHanded());
        result.put("bloodOxygenValue", ch.getBloodOxygenValue());
        result.put("fastingPlasmaGlucose", ch.getFastingPlasmaGlucose());
        result.put("postprandialBoolGlucose", ch.getPostprandialBloodGlucose());
        result.put("totalCholesterol", ch.getTotalCholesterol());
        result.put("triglyceride", ch.getTriglyceride());
        result.put("hdlc", ch.getHdlc());
        result.put("ldlc", ch.getLdlc());
        return SUCCESS;
    }

    /**
     * 更新信息
     *
     * @return SUCCESS或者ERROR
     */
    public String updateInformation() {
        //得到实例化
        Casehistory db_ch = casehistoryDAO.getSearchInformation(getChId());
        //基础特征
        db_ch.setHeight(getHeight());
        db_ch.setWeight(getWeight());
        db_ch.setPhysician(getPhysician());
        db_ch.setHospital(getHospital());
        db_ch.setHospitalPhone(getHospitalPhone());
        db_ch.setAllergyDrugs(getAllergyDrugs());
        db_ch.setMajorDiseases(getMajorDiseases());
        //血氧血压
        db_ch.setHighHanded(getHighHanded());
        db_ch.setLowHanded(getLowHanded());
        db_ch.setBloodOxygenValue(getBloodOxygenValue());
        //血糖信息
        db_ch.setFastingPlasmaGlucose(getFastingPlasmaGlucose());
        db_ch.setPostprandialBloodGlucose(getPostprandialBoolGlucose());
        //血脂信息
        db_ch.setTotalCholesterol(getTotalCholesterol());
        db_ch.setTriglyceride(getTriglyceride());
        db_ch.setHdlc(getHdlc());
        db_ch.setLdlc(getLdlc());
        //执行更新操作
        casehistoryDAO.updateInformation(db_ch);
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }

    /**
     * 增加病历
     *
     * @return JSON成功或失败提示信息
     */
    public String saveInformation() {
        int eId = 0;
        try {
            //根据选择框得到的数据一定是可以转换成数字的
            eId = Integer.parseInt(getEname());
        } catch (NumberFormatException e) {
            result = JsonMessage.returnMessage(false, "姓名错误");
            return ERROR;
        }
        Casehistory ch = new Casehistory();
        ch.setHeight(getHeight());
        ch.setWeight((getWeight()));
        ch.setPhysician(getPhysician());
        ch.setHospital(getHospital());
        ch.setHospitalPhone(getHospitalPhone());
        ch.setAllergyDrugs(getAllergyDrugs());
        ch.setMajorDiseases(getMajorDiseases());
        ch.setHighHanded(getHighHanded());
        ch.setLowHanded(getLowHanded());
        ch.setBloodOxygenValue(getBloodOxygenValue());
        ch.setFastingPlasmaGlucose(getFastingPlasmaGlucose());
        ch.setPostprandialBloodGlucose(getPostprandialBoolGlucose());
        ch.setTotalCholesterol(getTotalCholesterol());
        ch.setTriglyceride(getTriglyceride());
        ch.setHdlc(getHdlc());
        ch.setLdlc(getLdlc());
        boolean cr = casehistoryDAO.addInformation(ch, eId);
        if (!cr) {
            result = JsonMessage.returnMessage(false, "已经存在该名用户的病历");
            return ERROR;
        } else {
            result = JsonMessage.returnMessage(true, "success");
            return SUCCESS;
        }
    }

    /**
     * 删除功能
     *
     * @return 返回JSON数据，成功或失败的提示信息
     */
    public String removeInformation() {
        casehistoryDAO.deleteInformation(getChId());
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }
}
