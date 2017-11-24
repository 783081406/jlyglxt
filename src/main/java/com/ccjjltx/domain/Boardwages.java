package com.ccjjltx.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ccjjltx on 2017/11/24.
 * 伙食费
 *
 * @author ccj
 * @version 1.0
 */
@Entity
@Table
public class Boardwages implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bwid;
    private int highest;
    private int minimum;

    //无参构造函数
    public Boardwages() {
    }

    //有参构造函数
    public Boardwages(Integer highest, Integer minimum) {
        this.highest = highest;
        this.minimum = minimum;
    }

    public int getBwid() {
        return bwid;
    }

    public void setBwid(int bwid) {
        this.bwid = bwid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Boardwages that = (Boardwages) o;

        if (bwid != that.bwid) return false;
        if (highest != that.highest) return false;
        return minimum == that.minimum;
    }

    @Override
    public int hashCode() {
        int result = bwid;
        result = 31 * result + highest;
        result = 31 * result + minimum;
        return result;
    }
}