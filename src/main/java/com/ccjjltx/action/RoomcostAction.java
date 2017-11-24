package com.ccjjltx.action;

import com.ccjjltx.dao.RoomcostDAO;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by ccjjltx on 2017/11/24.
 * 入住费用的增删改查
 *
 * @author ccj
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class RoomcostAction {
    @Resource(name = "roomcostDAO")
    private RoomcostDAO roomcostDAO;
    private JSONObject result;
    /////////////提交过来的数据////////////
    private int rtype1;
    private int rtype2;
    private int rtype3;
    ///////////////////////////////////////
    public RoomcostDAO getRoomcostDAO() {
        return roomcostDAO;
    }

    public void setRoomcostDAO(RoomcostDAO roomcostDAO) {
        this.roomcostDAO = roomcostDAO;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

    public int getRtype1() {
        return rtype1;
    }

    public void setRtype1(int rtype1) {
        this.rtype1 = rtype1;
    }

    public int getRtype2() {
        return rtype2;
    }

    public void setRtype2(int rtype2) {
        this.rtype2 = rtype2;
    }

    public int getRtype3() {
        return rtype3;
    }

    public void setRtype3(int rtype3) {
        this.rtype3 = rtype3;
    }
}
