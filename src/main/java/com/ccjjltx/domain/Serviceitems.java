package com.ccjjltx.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ccjjltx on 2017/11/22.
 * 服务项目
 *
 * @author ccj
 * @version 1.0
 */
@Entity
@Table
public class Serviceitems implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sid;
    private String spath;
    private String stitle;
    private String scontent;
    private int isSelect;

    //无参构造函数
    public Serviceitems() {

    }

    //有参构造函数
    public Serviceitems(String spath, String stitle, String scontent) {
        this.spath = spath;
        this.stitle = stitle;
        this.scontent = scontent;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSpath() {
        return spath;
    }

    public void setSpath(String spath) {
        this.spath = spath;
    }

    public String getStitle() {
        return stitle;
    }

    public void setStitle(String stitle) {
        this.stitle = stitle;
    }

    @Basic
    @Column(name = "scontent")
    public String getScontent() {
        return scontent;
    }

    public void setScontent(String scontent) {
        this.scontent = scontent;
    }

    @Basic
    @Column(name = "isSelect")
    public int getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(int isSelect) {
        this.isSelect = isSelect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Serviceitems that = (Serviceitems) o;

        if (sid != that.sid) return false;
        if (isSelect != that.isSelect) return false;
        if (spath != null ? !spath.equals(that.spath) : that.spath != null) return false;
        if (stitle != null ? !stitle.equals(that.stitle) : that.stitle != null) return false;
        if (scontent != null ? !scontent.equals(that.scontent) : that.scontent != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sid;
        result = 31 * result + (spath != null ? spath.hashCode() : 0);
        result = 31 * result + (stitle != null ? stitle.hashCode() : 0);
        result = 31 * result + (scontent != null ? scontent.hashCode() : 0);
        result = 31 * result + isSelect;
        return result;
    }
}
