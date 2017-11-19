package com.ccjjltx.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ccjjltx on 2017/11/19.
 * 宣传栏
 *
 * @author ccj
 * @version 1.0
 */
@Entity
@Table
public class Billboards implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bid;
    private String bpath;
    private String btitle;
    private String bcontent;
    private Byte isSelect;

    //无参构造函数
    public Billboards() {
    }

    //有参构造函数
    public Billboards(String bpath, String btitle, String bcontent, Byte isSelect) {
        this.bpath = bpath;
        this.btitle = btitle;
        this.bcontent = bcontent;
        this.isSelect = isSelect;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getBpath() {
        return bpath;
    }

    public void setBpath(String bpath) {
        this.bpath = bpath;
    }

    public String getBtitle() {
        return btitle;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }

    public String getBcontent() {
        return bcontent;
    }

    public void setBcontent(String bcontent) {
        this.bcontent = bcontent;
    }

    public Byte getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(Byte isSelect) {
        this.isSelect = isSelect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Billboards that = (Billboards) o;

        if (bid != that.bid) return false;
        if (bpath != null ? !bpath.equals(that.bpath) : that.bpath != null) return false;
        if (btitle != null ? !btitle.equals(that.btitle) : that.btitle != null) return false;
        if (bcontent != null ? !bcontent.equals(that.bcontent) : that.bcontent != null) return false;
        if (isSelect != null ? !isSelect.equals(that.isSelect) : that.isSelect != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bid;
        result = 31 * result + (bpath != null ? bpath.hashCode() : 0);
        result = 31 * result + (btitle != null ? btitle.hashCode() : 0);
        result = 31 * result + (bcontent != null ? bcontent.hashCode() : 0);
        result = 31 * result + (isSelect != null ? isSelect.hashCode() : 0);
        return result;
    }
}
