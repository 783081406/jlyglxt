package com.ccjjltx.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by ccjjltx on 2017/12/31.
 * 出入院
 *
 * @author ccj
 * @version 1.0
 */
@Entity
public class Eae implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eaeid;
    private Date stime;
    private Date etime;
    @OneToOne(targetEntity = Elder.class)
    @JoinColumn(name = "eId")
    private Elder elder;

    public Eae() {
    }

    public int getEaeid() {
        return eaeid;
    }

    public void setEaeid(int eaeid) {
        this.eaeid = eaeid;
    }

    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    public Date getEtime() {
        return etime;
    }

    public void setEtime(Date etime) {
        this.etime = etime;
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

        Eae eae = (Eae) o;

        if (eaeid != eae.eaeid) return false;
        if (stime != null ? !stime.equals(eae.stime) : eae.stime != null) return false;
        if (etime != null ? !etime.equals(eae.etime) : eae.etime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eaeid;
        result = 31 * result + (stime != null ? stime.hashCode() : 0);
        result = 31 * result + (etime != null ? etime.hashCode() : 0);
        return result;
    }
}
