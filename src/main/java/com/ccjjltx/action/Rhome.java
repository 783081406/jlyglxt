package com.ccjjltx.action;

import com.ccjjltx.dao.BillboardsDAO;
import com.ccjjltx.dao.InterlocutionDAO;
import com.ccjjltx.dao.ServiceitemsDAO;
import com.ccjjltx.domain.Billboards;
import com.ccjjltx.domain.Interlocution;
import com.ccjjltx.domain.Serviceitems;
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
    @Resource(name = "serviceitemsDAO")
    private ServiceitemsDAO serviceitemsDAO;
    @Resource(name = "interlocutionDAO")
    private InterlocutionDAO interlocutionDAO;

    private List<Billboards> listBillboards = new ArrayList<>();
    private int lbSize;
    private List<Serviceitems> listServiceitems = new ArrayList<>();
    private List<Interlocution> listInterlocution = new ArrayList<>();

    public BillboardsDAO getBillboardsDAO() {
        return billboardsDAO;
    }

    public void setBillboardsDAO(BillboardsDAO billboardsDAO) {
        this.billboardsDAO = billboardsDAO;
    }

    public ServiceitemsDAO getServiceitemsDAO() {
        return serviceitemsDAO;
    }

    public void setServiceitemsDAO(ServiceitemsDAO serviceitemsDAO) {
        this.serviceitemsDAO = serviceitemsDAO;
    }

    public InterlocutionDAO getInterlocutionDAO() {
        return interlocutionDAO;
    }

    public void setInterlocutionDAO(InterlocutionDAO interlocutionDAO) {
        this.interlocutionDAO = interlocutionDAO;
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

    public List<Serviceitems> getListServiceitems() {
        return listServiceitems;
    }

    public void setListServiceitems(List<Serviceitems> listServiceitems) {
        this.listServiceitems = listServiceitems;
    }

    public List<Interlocution> getListInterlocution() {
        return listInterlocution;
    }

    public void setListInterlocution(List<Interlocution> listInterlocution) {
        this.listInterlocution = listInterlocution;
    }

    /**
     * 首页
     *
     * @return index逻辑视图
     */
    public String index() {
        List<Billboards> lb = billboardsDAO.getSelectInformation();
        setListBillboards(lb);
        setLbSize(lb.size() - 1);
        setListServiceitems(serviceitemsDAO.getSelectInformation());
        return SUCCESS;
    }

    /**
     * 环境概括
     *
     * @return environment逻辑视图
     */
    public String environment() {
        List<Billboards> lb = billboardsDAO.getSelectInformation();
        setListBillboards(lb);
        setLbSize(lb.size() - 1);
        return SUCCESS;
    }

    /**
     * 问题与回答
     *
     * @return qa逻辑视图
     */
    public String qa() {
        List<Billboards> lb = billboardsDAO.getSelectInformation();
        setListBillboards(lb);
        setLbSize(lb.size() - 1);
        setListInterlocution(interlocutionDAO.getSelectInformation());
        return SUCCESS;
    }

}
