package com.ccjjltx.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ccjjltx on 2017/10/29.
 * 老人信息总表
 *
 * @author ccj
 * @version 1.0
 */
@Entity
public class Elder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eId;
    private String ename;
    private int isIn;

    public int geteId() {
        return eId;
    }

    public void seteId(int eId) {
        this.eId = eId;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public int getIsIn() {
        return isIn;
    }

    public void setIsIn(int isIn) {
        this.isIn = isIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Elder elder = (Elder) o;

        if (eId != elder.eId) return false;
        if (isIn != elder.isIn) return false;
        if (ename != null ? !ename.equals(elder.ename) : elder.ename != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eId;
        result = 31 * result + (ename != null ? ename.hashCode() : 0);
        result = 31 * result + isIn;
        return result;
    }
}
