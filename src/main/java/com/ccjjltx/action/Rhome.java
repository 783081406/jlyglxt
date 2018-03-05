package com.ccjjltx.action;

import com.ccjjltx.dao.*;
import com.ccjjltx.domain.Billboards;
import com.ccjjltx.domain.Boardwages;
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
 * 前端页面汇总
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
    @Resource(name = "roomcostDAO")
    private RoomcostDAO roomcostDAO;
    @Resource(name = "boardwagesDAO")
    private BoardwagesDAO boardwagesDAO;
    @Resource(name = "nursingfeeDAO")
    private NursingfeeDAO nursingfeeDAO;

    private List<Billboards> listBillboards = new ArrayList<>();
    private int lbSize;
    private List<Serviceitems> listServiceitems = new ArrayList<>();
    private List<Interlocution> listInterlocution = new ArrayList<>();
    //入住费用
    private int roomcost1;
    private int roomcost2;
    private int roomcost3;
    //伙食费
    private int highest;
    private int minimum;
    //护理费
    private int nursingfee1;
    private int nursingfee2;
    private int nursingfee3;
    //合计
    private int ctotal11;
    private int ctotal12;
    private int ctotal21;
    private int ctotal22;
    private int ctotal31;
    private int ctotal32;

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

    public int getRoomcost1() {
        return roomcost1;
    }

    public void setRoomcost1(int roomcost1) {
        this.roomcost1 = roomcost1;
    }

    public int getRoomcost2() {
        return roomcost2;
    }

    public void setRoomcost2(int roomcost2) {
        this.roomcost2 = roomcost2;
    }

    public int getRoomcost3() {
        return roomcost3;
    }

    public void setRoomcost3(int roomcost3) {
        this.roomcost3 = roomcost3;
    }

    public int getHighest() {
        return highest;
    }

    public void setHighest(int highest) {
        this.highest = highest;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getNursingfee1() {
        return nursingfee1;
    }

    public void setNursingfee1(int nursingfee1) {
        this.nursingfee1 = nursingfee1;
    }

    public int getNursingfee2() {
        return nursingfee2;
    }

    public void setNursingfee2(int nursingfee2) {
        this.nursingfee2 = nursingfee2;
    }

    public int getNursingfee3() {
        return nursingfee3;
    }

    public void setNursingfee3(int nursingfee3) {
        this.nursingfee3 = nursingfee3;
    }

    public int getCtotal11() {
        return ctotal11;
    }

    public void setCtotal11(int ctotal11) {
        this.ctotal11 = ctotal11;
    }

    public int getCtotal12() {
        return ctotal12;
    }

    public void setCtotal12(int ctotal12) {
        this.ctotal12 = ctotal12;
    }

    public int getCtotal21() {
        return ctotal21;
    }

    public void setCtotal21(int ctotal21) {
        this.ctotal21 = ctotal21;
    }

    public int getCtotal22() {
        return ctotal22;
    }

    public void setCtotal22(int ctotal22) {
        this.ctotal22 = ctotal22;
    }

    public int getCtotal31() {
        return ctotal31;
    }

    public void setCtotal31(int ctotal31) {
        this.ctotal31 = ctotal31;
    }

    public int getCtotal32() {
        return ctotal32;
    }

    public void setCtotal32(int ctotal32) {
        this.ctotal32 = ctotal32;
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

    /**
     * 联系我们
     *
     * @return 逻辑视图
     */
    public String contact() {
        List<Billboards> lb = billboardsDAO.getSelectInformation();
        setListBillboards(lb);
        setLbSize(lb.size() - 1);
        return SUCCESS;
    }

    /**
     * 花费
     *
     * @return 逻辑视图
     */
    public String cost() {
        List<Billboards> lb = billboardsDAO.getSelectInformation();
        setListBillboards(lb);
        setLbSize(lb.size() - 1);
        //入住费用
        setRoomcost1(roomcostDAO.getSearchRoomcost(1).getRCost());
        setRoomcost2(roomcostDAO.getSearchRoomcost(2).getRCost());
        setRoomcost3(roomcostDAO.getSearchRoomcost(3).getRCost());
        //伙食费
        Boardwages bw = boardwagesDAO.getSearchInformation(1);
        setMinimum(bw.getMinimum());
        setHighest(bw.getHighest());
        //护理费
        setNursingfee1(nursingfeeDAO.getSearchInformation(1).getNcost());
        setNursingfee2(nursingfeeDAO.getSearchInformation(2).getNcost());
        setNursingfee3(nursingfeeDAO.getSearchInformation(3).getNcost());
        //合计
        setCtotal11(getRoomcost1() + getMinimum() + getNursingfee1());
        setCtotal12(getRoomcost1() + getHighest() + getNursingfee1());
        setCtotal21(getRoomcost2() + getMinimum() + getNursingfee2());
        setCtotal22(getRoomcost2() + getHighest() + getNursingfee2());
        setCtotal31(getRoomcost3() + getMinimum() + getNursingfee3());
        setCtotal32(getRoomcost3() + getHighest() + getNursingfee3());
        return SUCCESS;
    }
}
