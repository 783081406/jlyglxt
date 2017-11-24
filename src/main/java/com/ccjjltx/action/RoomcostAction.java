package com.ccjjltx.action;

import com.ccjjltx.dao.RoomcostDAO;
import com.ccjjltx.domain.Roomcost;
import com.ccjjltx.utils.JsonMessage;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/11/24.
 * 入住费用的增删改查
 *
 * @author ccj
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class RoomcostAction extends ActionSupport {
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

    /**
     * 得到所有的数据
     *
     * @return Json数据
     */
    public String getAllInformation() {
        result = new JSONObject();
        //得到所有的信息，共三条
        List<Roomcost> list = roomcostDAO.getAllInformation();
        //第一条数据
        Roomcost rc1 = list.get(0);
        result.put("rtype1", rc1.getRCost());
        //第二条数据
        Roomcost rc2 = list.get(1);
        result.put("rtype2", rc2.getRCost());
        //第三条数据
        Roomcost rc3 = list.get(2);
        result.put("rtype3", rc3.getRCost());
        return SUCCESS;
    }

    /**
     * 更新信息
     *
     * @return Json成功或失败信息
     */
    public String updateInformation() {
        //得到"标准"的实例化
        Roomcost rc1 = roomcostDAO.getSearchRoomcost(1);
        //设置"标准"的数据
        rc1.setRCost(getRtype1());
        //执行更新操作
        roomcostDAO.updateRoomcost(rc1);
        //得到"中等"的实例化
        Roomcost rc2 = roomcostDAO.getSearchRoomcost(2);
        rc2.setRCost(getRtype2());
        roomcostDAO.updateRoomcost(rc2);
        //得到"中等"的实例化
        Roomcost rc3 = roomcostDAO.getSearchRoomcost(3);
        rc3.setRCost(getRtype3());
        roomcostDAO.updateRoomcost(rc3);
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }
}
