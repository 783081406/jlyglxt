package com.ccjjltx.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ccjjltx on 2017/11/22.
 * 体验预约
 *
 * @author ccj
 * @version 1.0
 */
@Entity
@Table
public class Bespeak implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bid;
    private String name;
    private String phone;
    private String ename;
    private Integer eage;
    private int ishandle;
    private String handleUser;
    private Date submitDate;
    private Date handleDate;

    //无参构造函数
    public Bespeak() {
    }

    //有参构造函数
    public Bespeak(String name, String phone, String ename, Integer eage, Date submitDate) {
        this.name = name;
        this.phone = phone;
        this.ename = ename;
        this.eage = eage;
        this.submitDate = submitDate;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Integer getEage() {
        return eage;
    }

    public void setEage(Integer eage) {
        this.eage = eage;
    }

    public int getIshandle() {
        return ishandle;
    }

    public void setIshandle(int ishandle) {
        this.ishandle = ishandle;
    }

    public String getHandleUser() {
        return handleUser;
    }

    public void setHandleUser(String handleUser) {
        this.handleUser = handleUser;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public Date getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(Date handleDate) {
        this.handleDate = handleDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bespeak bespeak = (Bespeak) o;

        if (bid != bespeak.bid) return false;
        if (ishandle != bespeak.ishandle) return false;
        if (name != null ? !name.equals(bespeak.name) : bespeak.name != null) return false;
        if (phone != null ? !phone.equals(bespeak.phone) : bespeak.phone != null) return false;
        if (ename != null ? !ename.equals(bespeak.ename) : bespeak.ename != null) return false;
        if (eage != null ? !eage.equals(bespeak.eage) : bespeak.eage != null) return false;
        if (handleUser != null ? !handleUser.equals(bespeak.handleUser) : bespeak.handleUser != null) return false;
        if (submitDate != null ? !submitDate.equals(bespeak.submitDate) : bespeak.submitDate != null) return false;
        if (handleDate != null ? !handleDate.equals(bespeak.handleDate) : bespeak.handleDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bid;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (ename != null ? ename.hashCode() : 0);
        result = 31 * result + (eage != null ? eage.hashCode() : 0);
        result = 31 * result + ishandle;
        result = 31 * result + (handleUser != null ? handleUser.hashCode() : 0);
        result = 31 * result + (submitDate != null ? submitDate.hashCode() : 0);
        result = 31 * result + (handleDate != null ? handleDate.hashCode() : 0);
        return result;
    }
}
