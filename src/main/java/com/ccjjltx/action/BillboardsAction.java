package com.ccjjltx.action;

import com.ccjjltx.dao.BillboardsDAO;
import com.ccjjltx.domain.Billboards;
import com.ccjjltx.utils.JsonMessage;
import com.ccjjltx.utils.MyFile;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by ccjjltx on 2017/11/20.
 * 对宣传栏数据增删查改
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
    // 封装上传文件域的属性
    private File upload;
    // 封装上传文件类型的属性
    private String uploadContentType;
    // 封装上传文件名的属性
    private String uploadFileName;
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

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
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
     * @return json，成功或失败的信息
     */
    public String selectInformation() {
        billboardsDAO.updateIsSelect(getBids());
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }

    /**
     * 增加新数据
     *
     * @return JSON成功或失败的信息
     */
    public String saveInformation() {
        try {
            //保存图片操作
            FileOutputStream fos = new FileOutputStream(getSavePath() + "\\" + getUploadFileName());
            FileInputStream fis = new FileInputStream(getUpload());
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            //使用文件复制
            MyFile.copyFile(getSavePath() + "\\" + getUploadFileName(), "E:\\pcCode\\ideaCode\\jlyglxt\\target\\jlyglxt\\reception\\img\\billboards" + "\\" + getUploadFileName());
            //实例化类Billboards
            Billboards billboards = new Billboards(getUploadFileName(), getBtitle(), getBcontent());
            //执行增加操作
            billboardsDAO.addInformation(billboards);
            //返回json数据
            result = JsonMessage.returnMessage(true, "success");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * 更新数据
     *
     * @return Json，返回成功或失败的数据记录
     */
    public String updateInformation() {
        //根据提交过来的bid得到实例化
        Billboards bb = billboardsDAO.getSearchInformation(getBid());
        //更新数据
        bb.setBtitle(getBtitle());
        bb.setBcontent(getBcontent());
        //更新数据
        billboardsDAO.updateInformation(bb);
        //返回json成功信息
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }

    /**
     * 删除数据
     *
     * @return 成功或失败的json信息
     */
    public String removeInformation() {
        //得到数据图片名
        String imgName = billboardsDAO.getSearchInformation(getBid()).getBpath();
        String imgPath1 = getSavePath() + "\\" + imgName;
        String imgPath2 = "E:\\pcCode\\ideaCode\\jlyglxt\\target\\jlyglxt\\reception\\img\\billboards" + "\\" + imgName;
        //执行本地删除图片
        MyFile.deleteFile(imgPath1);
        MyFile.deleteFile(imgPath2);
        //执行删除数据库数据
        billboardsDAO.removeInformation(getBid());
        //返回json数据
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }
}
