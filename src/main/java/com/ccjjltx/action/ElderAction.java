package com.ccjjltx.action;

import com.ccjjltx.dao.ElderDAO;
import com.ccjjltx.domain.Elder;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/10/30.
 * 下拉框选择老人名字的时候使用
 *
 * @author ccj
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class ElderAction extends ActionSupport {
    private JSONArray result;
    @Resource(name = "elderDAO")
    private ElderDAO elderDAO;

    public JSONArray getResult() {
        return result;
    }

    public void setResult(JSONArray result) {
        this.result = result;
    }

    /**
     * 下拉框选择用户帐户使用
     * 场景：信息中心->房间信息
     *
     * @return 设置为SUCCESS
     */
    public String getIdElderName() {
        result = new JSONArray();
        List<Elder> list = elderDAO.getAllElder();
        for (Elder elder : list) {
            JSONObject js = new JSONObject();
            js.put("eId", elder.getEId());
            js.put("ename", elder.getEname());
            result.add(js);
        }
        return SUCCESS;
    }
}
