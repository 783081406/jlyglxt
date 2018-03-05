package com.ccjjltx.action;

import com.ccjjltx.dao.BoardwagesDAO;
import com.ccjjltx.domain.Boardwages;
import com.ccjjltx.utils.JsonMessage;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/11/24.
 * 对Boardwages相关操作（伙食费用）
 *
 * @author ccj
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class BoardwagesAction extends ActionSupport {
    @Resource(name = "boardwagesDAO")
    private BoardwagesDAO boardwagesDAO;
    private JSONObject result;
    ///////////////提交过来的数据////////////
    private int highest;
    private int minimum;
    ////////////////////////////////////////

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

    /**
     * 得到所有的信息
     *
     * @return Json数据
     */
    public String getAllInformation() {
        result = new JSONObject();
        //得到所有信息，目前共一条
        List<Boardwages> list = boardwagesDAO.getAllInformation();
        //得到第一条数据
        Boardwages bw1 = list.get(0);
        result.put("minimum", bw1.getMinimum());
        result.put("highest", bw1.getHighest());
        return SUCCESS;
    }

    /**
     * 更新信息
     *
     * @return Json成功或失败信息
     */
    public String updateInformation() {
        //得到第一条数据
        Boardwages bw1 = boardwagesDAO.getSearchInformation(1);
        //设置新
        bw1.setMinimum(getMinimum());
        bw1.setHighest(getHighest());
        //执行更新
        boardwagesDAO.updateInformation(bw1);
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }
}
