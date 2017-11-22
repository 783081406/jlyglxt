package com.ccjjltx.action;

import com.ccjjltx.dao.BillboardsDAO;
import com.ccjjltx.domain.Billboards;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ccjjltx on 2017/11/21.
 * 前端首页
 *
 * @author ccj
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class Rhome extends ActionSupport {
    @Resource(name = "billboardsDAO")
    private BillboardsDAO billboardsDAO;
    private List<Billboards> listBillboards = new ArrayList<>();
    private int lbSize;

    public BillboardsDAO getBillboardsDAO() {
        return billboardsDAO;
    }

    public void setBillboardsDAO(BillboardsDAO billboardsDAO) {
        this.billboardsDAO = billboardsDAO;
    }

    public List<Billboards> getListBillboards() {
        return listBillboards;
    }

    public void setListBillboards(List<Billboards> listBillboards) {
        this.listBillboards = listBillboards;
    }

    public int getLbSize() {
        return lbSize;
    }

    public void setLbSize(int lbSize) {
        this.lbSize = lbSize;
    }

    public String abc() {
        List<Billboards> lb = billboardsDAO.getSelectInformation();
        setListBillboards(lb);
        setLbSize(lb.size()-1);
        return SUCCESS;
    }
}
