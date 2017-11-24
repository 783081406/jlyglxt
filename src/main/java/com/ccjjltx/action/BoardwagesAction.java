package com.ccjjltx.action;

import com.ccjjltx.dao.BoardwagesDAO;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by ccjjltx on 2017/11/24.
 * 对Boardwages相关操作
 *
 * @author ccj
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class BoardwagesAction {
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
}
