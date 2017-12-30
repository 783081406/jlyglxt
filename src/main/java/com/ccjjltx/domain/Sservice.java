package com.ccjjltx.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ccjjltx on 2017/12/30.
 * 特别服务
 *
 * @author ccj
 * @version 1.0
 */
@Entity
@Table
public class Sservice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ssid;
    private String stype;
    private String stime;
    private String remark;
    //定义关联实体类Elder
    @OneToOne(targetEntity = Elder.class)
    //映射外键名字
    @JoinColumn(name = "eId")
    private Elder elder;

    //无参构造函数
    public Sservice() {
    }

    //有参构造函数
    public Sservice(String stype, String stime, String remark) {
        this.stype = stype;
        this.stime = stime;
        this.remark = remark;
    }

    public int getSsid() {
        return ssid;
    }

    public void setSsid(int ssid) {
        this.ssid = ssid;
    }

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Elder getElder() {
        return elder;
    }

    public void setElder(Elder elder) {
        this.elder = elder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sservice sservice = (Sservice) o;

        if (ssid != sservice.ssid) return false;
        if (stype != null ? !stype.equals(sservice.stype) : sservice.stype != null) return false;
        if (stime != null ? !stime.equals(sservice.stime) : sservice.stime != null) return false;
        if (remark != null ? !remark.equals(sservice.remark) : sservice.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ssid;
        result = 31 * result + (stype != null ? stype.hashCode() : 0);
        result = 31 * result + (stime != null ? stime.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
