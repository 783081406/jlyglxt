package com.ccjjltx.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * Created by ccjjltx on 2017/10/19.
 *
 * @author ccj
 * @version 1.0
 */
@Entity
@Table
public class Einformation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;
    private String name;
    private String idCard;
    private String sex;
    private String pType;
    private Date hiredate;
    private String education;
    private Date birthday;
    //定义该实体关联的User实体类
    @OneToOne(targetEntity = User.class)
    //映射外键名字
    @JoinColumn(name = "uid")
    private User user;

    public Einformation() {
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Einformation that = (Einformation) o;

        if (pid != that.pid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (idCard != null ? !idCard.equals(that.idCard) : that.idCard != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (pType != null ? !pType.equals(that.pType) : that.pType != null) return false;
        if (hiredate != null ? !hiredate.equals(that.hiredate) : that.hiredate != null) return false;
        if (education != null ? !education.equals(that.education) : that.education != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pid;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (idCard != null ? idCard.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (pType != null ? pType.hashCode() : 0);
        result = 31 * result + (hiredate != null ? hiredate.hashCode() : 0);
        result = 31 * result + (education != null ? education.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        return result;
    }
}
