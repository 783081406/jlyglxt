package com.ccjjltx.action;

import com.ccjjltx.dao.NursingfeeDAO;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

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
}
