package com.ccjjltx.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ccjjltx on 2017/10/29.
 * 房间类型&价格
 *
 * @author ccj
 * @version 1.0
 */
@Entity
@Table
public class Roomcost implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rcId;
    private String rType;
    private int rCost;

    public int getRcId() {
        return rcId;
    }

    public void setRcId(int rcId) {
        this.rcId = rcId;
    }

    public String getRType() {
        return rType;
    }

    public void setRType(String rType) {
        this.rType = rType;
    }

    public int getRCost() {
        return rCost;
    }

    public void setRCost(int rCost) {
        this.rCost = rCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Roomcost roomcost = (Roomcost) o;

        if (rcId != roomcost.rcId) return false;
        if (rCost != roomcost.rCost) return false;
        if (rType != null ? !rType.equals(roomcost.rType) : roomcost.rType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rcId;
        result = 31 * result + (rType != null ? rType.hashCode() : 0);
        result = 31 * result + rCost;
        return result;
    }
}
