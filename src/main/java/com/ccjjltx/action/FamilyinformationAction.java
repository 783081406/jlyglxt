package com.ccjjltx.action;

import com.ccjjltx.dao.FamilyinformationDAO;
import com.ccjjltx.domain.Familyinformation;
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
    private int eiId;
    private JSONObject result;

    public FamilyinformationDAO getFamilyinformationDAO() {
        return familyinformationDAO;
    }

    public void setFamilyinformationDAO(FamilyinformationDAO familyinformationDAO) {
        this.familyinformationDAO = familyinformationDAO;
    }

    public int getEiId() {
        return eiId;
    }

    public void setEiId(int eiId) {
        this.eiId = eiId;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
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
}
