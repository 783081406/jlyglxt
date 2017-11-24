package com.ccjjltx.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ccjjltx on 2017/11/25.
 * 护理费
 *
 * @author ccj
 * @version 1.0
 */
@Entity
@Table
public class Nursingfee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int nid;
    private String ntype;
    private int minimum;
    private int highest;

    //无参构造函数
    public Nursingfee() {
    }

    //有参构造函数
    public Nursingfee(String ntype, int minimum, int highest) {
        this.ntype = ntype;
        this.minimum = minimum;
        this.highest = highest;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getNtype() {
        return ntype;
    }

    public void setNtype(String ntype) {
        this.ntype = ntype;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getHighest() {
        return highest;
    }

    public void setHighest(int highest) {
        this.highest = highest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Nursingfee that = (Nursingfee) o;

        if (nid != that.nid) return false;
        if (minimum != that.minimum) return false;
        if (highest != that.highest) return false;
        return ntype != null ? ntype.equals(that.ntype) : that.ntype == null;
    }

    @Override
    public int hashCode() {
        int result = nid;
        result = 31 * result + (ntype != null ? ntype.hashCode() : 0);
        result = 31 * result + minimum;
        result = 31 * result + highest;
        return result;
    }
}
