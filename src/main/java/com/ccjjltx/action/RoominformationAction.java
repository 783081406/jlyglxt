package com.ccjjltx.action;

import com.ccjjltx.dao.RoominformationDAO;
import com.ccjjltx.domain.Elder;
import com.ccjjltx.domain.Roominformation;
import com.ccjjltx.utils.JsonMessage;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/10/29.
 * 房间信息的增删改查
 *
 * @author ccj
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class RoominformationAction extends ActionSupport {
    @Resource(name = "roominformationDAO")
    private RoominformationDAO roominformationDAO;
    private int page;//得到页数（第几页）  最原始首次得到是默认是1，表示第一页
    private int rows;//得到行数（每行显示的行数）   最原始首次得到默认是10，表示10行显示
    private JSONObject result;//保存json结果

    /////////////提交过来的Roominformation表中的字段////////////////
    private int rId;
    private String floor;
    private int roomNumber;
    private String rType;
    private int rCost;
    private String ename;
    ////////////////////////////////////////////////////////////
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

    public int getRId() {
        return rId;
    }

    public void setRId(int rId) {
        this.rId = rId;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRType() {
        return rType;
    }

    public void setRType(String rType) {
        this.rType = rType;
    }

    public int getRCost() {
        return rCost;
    }

    public void setRCost(int rCost) {
        this.rCost = rCost;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    /**
     * 得到全部或特定(搜索框触发)的房间信息数据
     *
     * @return 只返回SUCCESS
     */
    public String getAllInformation() {
        //得到起始的行数，方便之后的hql语句
        int offset = (getPage() - 1) * getRows();
        //得到所有的房间信息
        List<Roominformation> list = roominformationDAO.getAllInformation(offset, getRows(), getEname());
        //得到总条数
        int total = roominformationDAO.getAllInformationNumber(getEname());
        result = new JSONObject();
        result.put("total", total);
        //jsonarray对象
        JSONArray jsonArray = new JSONArray();
        for (Roominformation ri : list) {
            JSONObject js = new JSONObject();
            Elder elder = new Elder();
            js.put("rId", ri.getRId());
            js.put("floor", ri.getFloor());
            js.put("roomNumber", ri.getRoomNumber());
            js.put("rType", ri.getRoomcost().getRType());
            js.put("rCost", ri.getRoomcost().getRCost());
            elder = ri.getElder();
            if (elder != null) {
                js.put("ename", elder.getEname());
            } else {
                js.put("ename", null);
            }
            jsonArray.add(js);
        }
        result.put("rows", jsonArray);
        return SUCCESS;
    }

    /**
     * 用于增加新的房间信息
     *
     * @return json, 成功或者失败的提示信息
     */
    public String addInformation() {
        int eId = -1;
        try {
            //这里的ename传过来的实际上是eId
            //如果格式解析错误表示是字符串，表示格式错误
            eId = Integer.parseInt(getEname());
        } catch (NumberFormatException e) {
            eId = 0;
            result = JsonMessage.returnMessage(false, "请输入有效姓名");
            return ERROR;
        }
        int thisRusult = roominformationDAO.addInformation(getFloor(), getRoomNumber(), getRType(), getRCost(), eId);
        switch (thisRusult) {
            case 1:
                result = JsonMessage.returnMessage(false, "已经存在有相同的楼号与房间号");
                return ERROR;
            case 2:
                result = JsonMessage.returnMessage(false, "该名老人已经入住了");
                return ERROR;
            default:
                result = JsonMessage.returnMessage(true, "success");
                return SUCCESS;
        }
    }

    /**
     * 更新信息
     *
     * @return json结果信息
     */
    public String updateInformation() {
        Roominformation db_ri = roominformationDAO.getSearchRoominformation(getRId());
        db_ri.setFloor(getFloor());
        db_ri.setRoomNumber(getRoomNumber());
        db_ri.getRoomcost().setRType(getRType());
        db_ri.getRoomcost().setRCost(getRCost());
        int eId = 0;
        try {
            eId = Integer.parseInt(getEname());
        } catch (NumberFormatException e) {
            eId = 0;
        }
        //执行更新操作
        int this_result = roominformationDAO.updateInformation(db_ri, eId);
        switch (this_result) {
            case 1:
                result = JsonMessage.returnMessage(false, "该名老人已入住");
                return ERROR;
            case 2:
                result = JsonMessage.returnMessage(false, "已有相同的楼号与房间号");
                return ERROR;
            default:
                result = JsonMessage.returnMessage(true, "success");
                return SUCCESS;
        }
    }

    /**
     * 删除功能
     *
     * @return 返回成功
     */
    public String removeInformation() {
        roominformationDAO.deleteInformation(getRId());
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }
}
