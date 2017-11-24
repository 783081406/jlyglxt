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
    int highest;
    int minimum;
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

    public int getHighest() {
        return highest;
    }

    public void setHighest(int highest) {
        this.highest = highest;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }
}
