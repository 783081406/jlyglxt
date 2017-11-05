package com.ccjjltx.action;

import com.ccjjltx.dao.ElderlyinformationDAO;
import com.ccjjltx.domain.Elderlyinformation;
import com.ccjjltx.utils.MyDateFormat;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
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
public class elderlyimAction extends ActionSupport {
    @Resource(name = "elderlyinformationDAO")
    private ElderlyinformationDAO elderlyinformationDAO;
    //得到页数（第几页）
    //最原始首次得到是默认是1，表示第一页
    private int page;
    //得到行数（每行显示的行数）
    //最原始首次得到默认是10，表示10行显示
    private int rows;
    //保存json结果
    private JSONObject result;
    ///////////////////提交过来的表中的字段///////////////////////
    private String ename;
    //////////////////////////////////////////////////////////////

    public ElderlyinformationDAO getElderlyinformationDAO() {
        return elderlyinformationDAO;
    }

    public void setElderlyinformationDAO(ElderlyinformationDAO elderlyinformationDAO) {
        this.elderlyinformationDAO = elderlyinformationDAO;
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
            js.put("originAddress", ei.getOriginAddress());
            jsonArray.add(js);
        }
        result.put("rows", jsonArray);
        return SUCCESS;
    }
}
