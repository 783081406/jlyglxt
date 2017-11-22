package com.ccjjltx.action;

import com.ccjjltx.dao.ServiceitemsDAO;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;

/**
 * Created by ccjjltx on 2017/11/22.
 * 对服务项目数据的增删改查
 *
 * @author ccj
 * @version 1.0
 */
@Controller
@Scope("prototype")

public class ServiceitemsAction {
    @Resource(name = "serviceitemsDAO")
    private ServiceitemsDAO serviceitemsDAO;
    private int page;
    private int rows;
    private JSONObject result;
    //////////////////提交过来Serviceitems表中的数据//////////
    private int sid;
    private String spath;
    private String stitle;
    private String scontent;
    private int isSelect;
    private int[] sids;
    /////////////////上传////////////////////
    // 封装上传文件域的属性
    private File upload;
    // 封装上传文件类型的属性
    private String uploadContentType;
    // 封装上传文件名的属性
    private String uploadFileName;
    // 直接在struts.xml文件中配置的属性
    private String savePath;

    public ServiceitemsDAO getServiceitemsDAO() {
        return serviceitemsDAO;
    }

    public void setServiceitemsDAO(ServiceitemsDAO serviceitemsDAO) {
        this.serviceitemsDAO = serviceitemsDAO;
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

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSpath() {
        return spath;
    }

    public void setSpath(String spath) {
        this.spath = spath;
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

    public int getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(int isSelect) {
        this.isSelect = isSelect;
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
}
