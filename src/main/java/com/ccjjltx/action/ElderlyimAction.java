package com.ccjjltx.action;

import com.ccjjltx.dao.ElderlyinformationDAO;
import com.ccjjltx.domain.Elder;
import com.ccjjltx.domain.Elderlyinformation;
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
 * Created by ccjjltx on 2017/11/5.
 * 对信息查看的增删除查改
 *
 * @author ccj
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class ElderlyimAction extends ActionSupport {
    @Resource(name = "elderlyinformationDAO")
    private ElderlyinformationDAO elderlyinformationDAO;
    private int page;//得到页数（第几页）  最原始首次得到是默认是1，表示第一页
    private int rows;//得到行数（每行显示的行数）  最原始首次得到默认是10，表示10行显示
    private JSONObject result;//保存json结果
    ///////////////////提交过来的表中的字段///////////////////////
    private int eiId;
    private String ename;
    private String sex;
    private String idcard;
    private String phone;
    private Date birthDate;
    private String habit;
    private String homeAddress;
    private String originAddress;
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

    public int getEiId() {
        return eiId;
    }

    public void setEiId(int eiId) {
        this.eiId = eiId;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getHabit() {
        return habit;
    }

    public void setHabit(String habit) {
        this.habit = habit;
    }

    public String getOriginAddress() {
        return originAddress;
    }

    public void setOriginAddress(String originAddress) {
        this.originAddress = originAddress;
    }

    /**
     * 得到全部或者特定的（搜索框触发）的信息
     *
     * @return 只返回SUCCESS，
     */
    public String getAllInformation() {
        //得到起始的行数
        int offset = (getPage() - 1) * getRows();
        //得到所有的信息
        List<Elderlyinformation> list = elderlyinformationDAO.getAllInformation(offset, getRows(), getEname());
        //得到总条数
        int total = elderlyinformationDAO.getAllInformationNumber(getEname());
        result = new JSONObject();
        result.put("total", total);
        //循环插入
        JSONArray jsonArray = new JSONArray();
        for (Elderlyinformation ei : list) {
            JSONObject js = new JSONObject();
            js.put("eiId", ei.getEiId());
            js.put("ename", ei.getElder().getEname());
            js.put("sex", ei.getSex());
            js.put("idcard", ei.getIdcard());
            js.put("phone", ei.getPhone());
            js.put("birthDate", MyDateFormat.format(ei.getBirthDate()));
            js.put("homeAddress", ei.getHomeAddress());
            js.put("habit", ei.getHabit());
            js.put("originAddress", ei.getOriginAddress());
            jsonArray.add(js);
        }
        result.put("rows", jsonArray);
        return SUCCESS;
    }

    /**
     * 增加新信息
     *
     * @return Json，成功或失败的json信息
     */
    public String saveInformation() {
        //将得到的数据实例化
        Elder e = new Elder(getEname(), 1);
        Elderlyinformation ei = new Elderlyinformation(getIdcard(), getPhone(), getSex(), getBirthDate(), getHomeAddress(), getOriginAddress());
        ei.setHabit(getHabit());
        //保存数据操作
        elderlyinformationDAO.addInformation(ei, e);
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }

    /**
     * 更新信息
     *
     * @return SUCCESS
     */
    public String updateInformation() {
        //根据传送过来的主键实例化
        Elderlyinformation db_ei = elderlyinformationDAO.getSearchInformation(getEiId());
        db_ei.getElder().setEname(getEname());
        db_ei.setSex(getSex());
        db_ei.setIdcard(getIdcard());
        db_ei.setPhone(getPhone());
        db_ei.setBirthDate(getBirthDate());
        db_ei.setHabit(getHabit());
        db_ei.setHomeAddress(getHomeAddress());
        db_ei.setOriginAddress(getOriginAddress());
        elderlyinformationDAO.updateInformation(db_ei);
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }

    /**
     * 删除功能
     *
     * @return 返回成功
     */
    public String removeInformation() {
        elderlyinformationDAO.deleteInformation(getEiId());
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }
}
