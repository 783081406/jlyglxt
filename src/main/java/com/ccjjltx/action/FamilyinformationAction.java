package com.ccjjltx.action;

import com.ccjjltx.dao.ElderlyinformationDAO;
import com.ccjjltx.dao.FamilyinformationDAO;
import com.ccjjltx.domain.Elderlyinformation;
import com.ccjjltx.domain.Familyinformation;
import com.ccjjltx.utils.JsonMessage;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/11/5.
 * 对家庭信息的相关操作
 *
 * @author ccj
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class FamilyinformationAction extends ActionSupport {
    @Resource(name = "familyinformationDAO")
    private FamilyinformationDAO familyinformationDAO;
    @Resource(name = "elderlyinformationDAO")
    private ElderlyinformationDAO elderlyinformationDAO;

    private JSONObject result;
    //////////////////////传送过来的数据////////////////////////////
    private int eiId;
    private int fId;
    private String familyName;
    private String relationship;
    private String faddress;
    private String phone;
    /////////////////////////////////////////////////

    public FamilyinformationDAO getFamilyinformationDAO() {
        return familyinformationDAO;
    }

    public void setFamilyinformationDAO(FamilyinformationDAO familyinformationDAO) {
        this.familyinformationDAO = familyinformationDAO;
    }

    public ElderlyinformationDAO getElderlyinformationDAO() {
        return elderlyinformationDAO;
    }

    public void setElderlyinformationDAO(ElderlyinformationDAO elderlyinformationDAO) {
        this.elderlyinformationDAO = elderlyinformationDAO;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

    public int getEiId() {
        return eiId;
    }

    public void setEiId(int eiId) {
        this.eiId = eiId;
    }

    public int getFId() {
        return fId;
    }

    public void setFId(int fId) {
        this.fId = fId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getFaddress() {
        return faddress;
    }

    public void setFaddress(String faddress) {
        this.faddress = faddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 得到所有的家庭信息
     *
     * @return 返回SUCCESS
     */
    public String getAllInformation() {
        List<Familyinformation> list = familyinformationDAO.getAllInformation(getEiId());
        result = new JSONObject();
        result.put("total", list.size());
        JSONArray jsonArray = new JSONArray();
        for (Familyinformation fi : list) {
            JSONObject js = new JSONObject();
            js.put("fId", fi.getFId());
            js.put("familyName", fi.getFamilyName());
            js.put("relationship", fi.getRelationship());
            js.put("faddress", fi.getFaddress());
            js.put("phone", fi.getPhone());
            jsonArray.add(js);
        }
        result.put("rows", jsonArray);
        return SUCCESS;
    }

    /**
     * 增加新信息
     *
     * @return Json，成功或失败的数据
     */
    public String saveInformation() {
        Elderlyinformation ei = elderlyinformationDAO.getSearchInformation(getEiId());
        //实例化
        Familyinformation fi = new Familyinformation(getFamilyName(), getPhone(), getRelationship(), getFaddress());
        fi.setElderlyinformation(ei);
        //插入数据
        familyinformationDAO.addInformation(fi);
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }

    /**
     * 更新操作
     *
     * @return 返回成功
     */
    public String updateInformation() {
        //得到实例化
        Familyinformation db_fi = familyinformationDAO.getSearchInformation(getFId());
        //更新信息
        db_fi.setFamilyName(getFamilyName());
        db_fi.setRelationship(getRelationship());
        db_fi.setFaddress(getFaddress());
        db_fi.setFaddress(getFaddress());
        db_fi.setPhone(getPhone());
        //更新操作
        familyinformationDAO.updateInformation(db_fi);
        //返回json信息
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }
}
