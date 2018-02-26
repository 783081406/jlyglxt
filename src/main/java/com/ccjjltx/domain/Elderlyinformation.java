package com.ccjjltx.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by ccjjltx on 2017/11/5.
 * 老人详细表
 *
 * @author ccj
 * @version 1.0
 */
@Entity
@Table
public class Elderlyinformation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eiId;
    private String idcard;
    private String phone;
    private String sex;
    private Date birthDate;
    private String habit;
    private String homeAddress;
    private String originAddress;
    //定义关联实体类Elder
    @OneToOne(targetEntity = Elder.class)
    //映射外键名字
    @JoinColumn(name = "eId")
    private Elder elder;

    //无参构造函数
    public Elderlyinformation() {
    }

    //有参构造函数
    public Elderlyinformation(String idcard, String phone, String sex, Date birthDate, String homeAddress, String originAddress) {
        this.idcard = idcard;
        this.phone = phone;
        this.sex = sex;
        this.birthDate = birthDate;
        this.homeAddress = homeAddress;
        this.originAddress = originAddress;
    }

    public int getEiId() {
        return eiId;
    }

    public void setEiId(int eiId) {
        this.eiId = eiId;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getHabit() {
        return habit;
    }

    public void setHabit(String habit) {
        this.habit = habit;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getOriginAddress() {
        return originAddress;
    }

    public void setOriginAddress(String originAddress) {
        this.originAddress = originAddress;
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

        Elderlyinformation that = (Elderlyinformation) o;

        if (eiId != that.eiId) return false;
        if (idcard != null ? !idcard.equals(that.idcard) : that.idcard != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (birthDate != null ? !birthDate.equals(that.birthDate) : that.birthDate != null) return false;
        if (homeAddress != null ? !homeAddress.equals(that.homeAddress) : that.homeAddress != null) return false;
        if (originAddress != null ? !originAddress.equals(that.originAddress) : that.originAddress != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eiId;
        result = 31 * result + (idcard != null ? idcard.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (homeAddress != null ? homeAddress.hashCode() : 0);
        result = 31 * result + (originAddress != null ? originAddress.hashCode() : 0);
        return result;
    }
}
