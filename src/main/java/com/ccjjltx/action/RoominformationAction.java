package com.ccjjltx.action;

import com.ccjjltx.dao.RoominformationDAO;
import com.ccjjltx.domain.Elder;
import com.ccjjltx.domain.Roominformation;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.swing.plaf.SeparatorUI;
import java.util.Date;
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
    //得到页数（第几页）
    //最原始首次得到是默认是1，表示第一页
    private int page;
    //得到行数（每行显示的行数）
    //最原始首次得到默认是10，表示10行显示
    private int rows;
    //保存json结果
    private JSONObject result;

    /////////////提交过来的Roominformation表中的字段////////////////
    private String ename;
    ////////////////////////////////////////////////////////////


    public RoominformationDAO getRoominformationDAO() {
        return roominformationDAO;
    }

    public void setRoominformationDAO(RoominformationDAO roominformationDAO) {
        this.roominformationDAO = roominformationDAO;
    }

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
            System.out.println(1);
            js.put("floor", ri.getFloor());
            System.out.println(2);
            js.put("roomNumber", ri.getRoomNumber());
            System.out.println(3);
            js.put("rType", ri.getRoomcost().getRType());
            System.out.println(4);
            js.put("rCost", ri.getRoomcost().getRCost());
            System.out.println(5);
            elder = ri.getElder();
            if (elder != null) {
                js.put("ename", elder.getEname());
            } else {
                js.put("ename", null);
            }
            jsonArray.add(js);
        }
        result.put("rows", jsonArray);
        System.out.println(result);
        return SUCCESS;
    }

}
