package com.ccjjltx.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ccjjltx on 2017/11/5.
 * 家庭详细表
 *
 * @author ccj
 * @version 1.0
 */
@Entity
@Table
public class Familyinformation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fId;
    private String familyName;
    private String phone;
    private String relationship;
    private String faddress;
    //定义关联实体类
    @ManyToOne(targetEntity = Elderlyinformation.class)
    //映射外键名字
    @JoinColumn(name = "eiId")
    private Elderlyinformation elderlyinformation;

    //无参构造函数
    public Familyinformation() {
    }

    //有参构造函数
    public Familyinformation(String familyName, String phone, String relationship, String faddress) {
        this.familyName = familyName;
        this.phone = phone;
        this.relationship = relationship;
        this.faddress = faddress;
    }

    public int getFId() {
        return fId;
    }

    public void setFId(int fId) {
        this.fId = fId;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getFaddress() {
        return faddress;
    }

    public void setFaddress(String faddress) {
        this.faddress = faddress;
    }

    public Elderlyinformation getElderlyinformation() {
        return elderlyinformation;
    }

    public void setElderlyinformation(Elderlyinformation elderlyinformation) {
        this.elderlyinformation = elderlyinformation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Familyinformation that = (Familyinformation) o;

        if (fId != that.fId) return false;
        if (familyName != null ? !familyName.equals(that.familyName) : that.familyName != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (relationship != null ? !relationship.equals(that.relationship) : that.relationship != null) return false;
        if (faddress != null ? !faddress.equals(that.faddress) : that.faddress != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fId;
        result = 31 * result + (familyName != null ? familyName.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (relationship != null ? relationship.hashCode() : 0);
        result = 31 * result + (faddress != null ? faddress.hashCode() : 0);
        return result;
    }
}
