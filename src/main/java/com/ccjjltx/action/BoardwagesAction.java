package com.ccjjltx.action;

import com.ccjjltx.dao.BoardwagesDAO;
import com.ccjjltx.domain.Boardwages;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/11/24.
 * 对Boardwages相关操作
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
    int highest;
    int minimum;

    ////////////////////////////////////////
    public BoardwagesDAO getBoardwagesDAO() {
        return boardwagesDAO;
    }

    public void setBoardwagesDAO(BoardwagesDAO boardwagesDAO) {
        this.boardwagesDAO = boardwagesDAO;
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
}
