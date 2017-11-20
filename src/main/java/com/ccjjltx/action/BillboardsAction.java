package com.ccjjltx.action;

import com.ccjjltx.dao.BillboardsDAO;
import com.ccjjltx.domain.Billboards;
import com.ccjjltx.utils.JsonMessage;
import com.ccjjltx.utils.ResourcePath;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/11/20.
 * 到宣传栏数据增删查改
 *
 * @author ccj
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class BillboardsAction extends ActionSupport {
    @Resource(name = "billboardsDAO")
    private BillboardsDAO billboardsDAO;
    private int page;
    private int rows;
    private JSONObject result;
    //////////////////提交过来billboards表中的数据//////////
    private int bid;
    private String bpath;
    private String btitle;
    private String bcontent;
    private int isSelect;
    private int[] bids;
    ////////////////////上传//////////////////////////////////

    // 直接在struts.xml文件中配置的属性
    private String savePath;

    /////////////////////////////////////////////////////
    public BillboardsDAO getBillboardsDAO() {
        return billboardsDAO;
    }

    public void setBillboardsDAO(BillboardsDAO billboardsDAO) {
        this.billboardsDAO = billboardsDAO;
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

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getBpath() {
        return bpath;
    }

    public void setBpath(String bpath) {
        this.bpath = bpath;
    }

    public String getBtitle() {
        return btitle;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }

    public String getBcontent() {
        return bcontent;
    }

    public void setBcontent(String bcontent) {
        this.bcontent = bcontent;
    }

    public int getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(int isSelect) {
        this.isSelect = isSelect;
    }

    public int[] getBids() {
        return bids;
    }

    public void setBids(int[] bids) {
        this.bids = bids;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    /**
     * 得到总数据
     *
     * @return 返回Json数据
     */
    public String getAllInformation() {
        //得到起始的行数
        int offset = (getPage() - 1) * getRows();
        //得到所有数据
        List<Billboards> list = billboardsDAO.getAllInformation(offset, getRows());
        //得到总条数
        int total = billboardsDAO.getAllInformationNumber();
        result = new JSONObject();
        result.put("total", total);
        JSONArray jsonArray = new JSONArray();
        String tempPath = "../../../reception/img/billboards";
        for (Billboards bb : list) {
            JSONObject js = new JSONObject();
            js.put("bid", bb.getBid());
            js.put("bpath", tempPath + "/" + bb.getBpath());
            js.put("btitle", bb.getBtitle());
            js.put("bcontent", bb.getBcontent());
            js.put("bcontent", bb.getBcontent());
            js.put("checked", bb.getIsSelect() == 0 ? false : true);
            jsonArray.add(js);
        }
        result.put("rows", jsonArray);
        return SUCCESS;
    }

    /**
     * 重选功能
     *
     * @return 成功或失败的提示信息
     */
    public String reelectInformation() {
        //执行重选功能
        billboardsDAO.reelectInformation();
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }

    /**
     * 提交功能，选择了要轮播的图片之后提交过来处理的方法
     *
     * @return json，成功或失败的
     */
    public String selectInformation() {
        billboardsDAO.updateIsSelect(getBids());
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }
}
