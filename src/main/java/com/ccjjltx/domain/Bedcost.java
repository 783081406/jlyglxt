package com.ccjjltx.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ccjjltx on 2017/10/29.
 * 床位类型&价格
 *
 * @author ccj
 * @version 1.0
 */
@Entity
@Table
public class Bedcost implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bcId;
    private String bType;
    private int bCost;

    public int getBcId() {
        return bcId;
    }

    public void setBcId(int bcId) {
        this.bcId = bcId;
    }

    public String getBType() {
        return bType;
    }

    public void setBType(String bType) {
        this.bType = bType;
    }

    public int getBCost() {
        return bCost;
    }

    public void setBCost(int bCost) {
        this.bCost = bCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bedcost bedcost = (Bedcost) o;

        if (bcId != bedcost.bcId) return false;
        if (bCost != bedcost.bCost) return false;
        if (bType != null ? !bType.equals(bedcost.bType) : bedcost.bType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bcId;
        result = 31 * result + (bType != null ? bType.hashCode() : 0);
        result = 31 * result + bCost;
        return result;
    }
}
