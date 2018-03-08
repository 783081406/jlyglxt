package com.ccjjltx.action;

import com.ccjjltx.dao.ServiceitemsDAO;
import com.ccjjltx.domain.Serviceitems;
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
 * Created by ccjjltx on 2017/11/22.
 * 对服务项目数据的增删改查
 *
 * @author ccj
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class ServiceitemsAction extends ActionSupport {
    @Resource(name = "serviceitemsDAO")
    private ServiceitemsDAO serviceitemsDAO;
    private int page;
    private int rows;
    private JSONObject result;
    //////////////////提交过来Serviceitems表中的数据//////////
    private int sid;
    private String stitle;
    private String scontent;
    private int[] sids;
    /////////////////上传////////////////////
    private File upload;// 封装上传文件域的属性
    //    private String uploadContentType;// 封装上传文件类型的属性
    private String uploadFileName;// 封装上传文件名的属性
    private String savePath;// 直接在struts.xml文件中配置的属性
    /////////////////////////////////////

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

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getStitle() {
        return stitle;
    }

    public void setStitle(String stitle) {
        this.stitle = stitle;
    }

    public String getScontent() {
        return scontent;
    }

    public void setScontent(String scontent) {
        this.scontent = scontent;
    }

    public int[] getSids() {
        return sids;
    }

    public void setSids(int[] sids) {
        this.sids = sids;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

/*    public String getUploadContentType() {
        return uploadContentType;
    }*/

/*    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }*/

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
        //得到所有的数据
        List<Serviceitems> list = serviceitemsDAO.getAllInformation(offset, getRows());
        //总条数
        int total = serviceitemsDAO.getAllInformationNumber();
        //设置result信息
        result = new JSONObject();
        result.put("total", total);
        JSONArray jsonArray = new JSONArray();
        String tempPath = "../../../reception/img/service";
        for (Serviceitems si : list) {
            JSONObject js = new JSONObject();
            js.put("sid", si.getSid());
            js.put("spath", tempPath + "/" + si.getSpath());
            js.put("stitle", si.getStitle());
            js.put("scontent", si.getScontent());
            js.put("checked", si.getIsSelect() == 0 ? false : true);
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
        serviceitemsDAO.reelectInformation();
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }

    /**
     * 提交功能，选择了展示的数据的方法
     *
     * @return json，成功或失败的
     */
    public String selectInformation() {
        serviceitemsDAO.updateIsSelect(getSids());
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }

    /**
     * 增加新数据
     *
     * @return JSON成功或失败的数据
     */
    public String saveInformation() throws IOException {
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
        MyFile.copyFile(getSavePath() + "\\" + getUploadFileName(), "E:\\pcCode\\ideaCode\\jlyglxt\\target\\jlyglxt\\reception\\img\\service" + "\\" + getUploadFileName());
        //实例化Serviceitems
        Serviceitems si = new Serviceitems(getUploadFileName(), getStitle(), getScontent());
        serviceitemsDAO.addInformation(si);
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }

    /**
     * 更新数据
     *
     * @return Json，返回成功或失败的数据记录
     */
    public String updateInformation() {
        Serviceitems si = serviceitemsDAO.getSearchInformation(getSid());
        //更新数据
        si.setStitle(getStitle());
        si.setScontent(getScontent());
        //更新数据
        serviceitemsDAO.updateInformation(si);
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }

    /**
     * 删除数据
     *
     * @return 成功或失败的json数据
     */
    public String removeInformation() {
        String imgName = serviceitemsDAO.getSearchInformation(getSid()).getSpath();
        String imgPath1 = getSavePath() + "\\" + imgName;
        String imgPath2 = "E:\\pcCode\\ideaCode\\jlyglxt\\target\\jlyglxt\\reception\\img\\service" + "\\" + imgName;
        //执行本地删除图片
        MyFile.deleteFile(imgPath1);
        MyFile.deleteFile(imgPath2);
        //执行删除数据库数据
        serviceitemsDAO.removeInformation(getSid());
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }
}
