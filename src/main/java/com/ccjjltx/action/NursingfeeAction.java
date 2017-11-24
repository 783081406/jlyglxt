package com.ccjjltx.action;

import com.ccjjltx.dao.NursingfeeDAO;
import com.ccjjltx.domain.Nursingfee;
import com.ccjjltx.utils.JsonMessage;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/11/25.
 * 对护理费相关操作
 *
 * @author ccj
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class NursingfeeAction extends ActionSupport {
    @Resource(name = "nursingfeeDAO")
    private NursingfeeDAO nursingfeeDAO;
    private JSONObject result;
    ///////////////提交过来的数据////////////
    private int ncost1;
    private int ncost2;
    private int ncost3;

    ////////////////////////////////////////
    public NursingfeeDAO getNursingfeeDAO() {
        return nursingfeeDAO;
    }

    public void setNursingfeeDAO(NursingfeeDAO nursingfeeDAO) {
        this.nursingfeeDAO = nursingfeeDAO;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

    public int getNcost1() {
        return ncost1;
    }

    public void setNcost1(int ncost1) {
        this.ncost1 = ncost1;
    }

    public int getNcost2() {
        return ncost2;
    }

    public void setNcost2(int ncost2) {
        this.ncost2 = ncost2;
    }

    public int getNcost3() {
        return ncost3;
    }

    public void setNcost3(int ncost3) {
        this.ncost3 = ncost3;
    }

    /**
     * 得到所有的信息
     *
     * @return Json数据
     */
    public String getAllInformation() {
        result = new JSONObject();
        //得到所有信息
        List<Nursingfee> list = nursingfeeDAO.getAllInformation();
        //得到第一条信息
        Nursingfee nf1 = list.get(0);
        result.put("ncost1", nf1.getNcost());
        //得到第二条信息
        Nursingfee nf2 = list.get(1);
        result.put("ncost2", nf2.getNcost());
        //得到第三条信息
        Nursingfee nf3 = list.get(2);
        result.put("ncost3", nf3.getNcost());
        return SUCCESS;
    }

    /**
     * 更新方法
     *
     * @return Json成功或失败信息
     */
    public String updateInformation() {
        //得到"初级护理"数据
        Nursingfee nf1 = nursingfeeDAO.getSearchInformation(1);
        nf1.setNcost(getNcost1());
        nursingfeeDAO.updateInformation(nf1);
        //得到"中级护理"数据
        Nursingfee nf2 = nursingfeeDAO.getSearchInformation(2);
        nf2.setNcost(getNcost2());
        nursingfeeDAO.updateInformation(nf2);
        //得到"高级护理"数据
        Nursingfee nf3 = nursingfeeDAO.getSearchInformation(3);
        nf3.setNcost(getNcost3());
        nursingfeeDAO.updateInformation(nf3);
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }
}
