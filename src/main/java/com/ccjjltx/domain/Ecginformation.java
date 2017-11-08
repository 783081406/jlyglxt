package com.ccjjltx.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ccjjltx on 2017/11/8.
 * 心电信息
 *
 * @author ccj
 * @version 1.0
 */
@Entity
@Table
public class Ecginformation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ecgId;
    private String qrs;
    private String comment;
    private String rr;
    private String analysisResult;
    private String rhythm;
    @ManyToOne(targetEntity = Casehistory.class)
    @JoinColumn(name = "chId")
    private Casehistory casehistory;

    //无参构造函数
    public Ecginformation() {
    }

    //有参构造函数
    public Ecginformation(String qrs, String comment, String rr, String analysisResult, String rhythm, Casehistory casehistory) {
        this.qrs = qrs;
        this.comment = comment;
        this.rr = rr;
        this.analysisResult = analysisResult;
        this.rhythm = rhythm;
        this.casehistory = casehistory;
    }

    public int getEcgId() {
        return ecgId;
    }

    public void setEcgId(int ecgId) {
        this.ecgId = ecgId;
    }

    public String getQrs() {
        return qrs;
    }

    public void setQrs(String qrs) {
        this.qrs = qrs;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRr() {
        return rr;
    }

    public void setRr(String rr) {
        this.rr = rr;
    }

    public String getAnalysisResult() {
        return analysisResult;
    }

    public void setAnalysisResult(String analysisResult) {
        this.analysisResult = analysisResult;
    }

    public String getRhythm() {
        return rhythm;
    }

    public void setRhythm(String rhythm) {
        this.rhythm = rhythm;
    }

    public Casehistory getCasehistory() {
        return casehistory;
    }

    public void setCasehistory(Casehistory casehistory) {
        this.casehistory = casehistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ecginformation that = (Ecginformation) o;

        if (ecgId != that.ecgId) return false;
        if (qrs != null ? !qrs.equals(that.qrs) : that.qrs != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (rr != null ? !rr.equals(that.rr) : that.rr != null) return false;
        if (analysisResult != null ? !analysisResult.equals(that.analysisResult) : that.analysisResult != null)
            return false;
        if (rhythm != null ? !rhythm.equals(that.rhythm) : that.rhythm != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = ecgId;
        result = 31 * result + (qrs != null ? qrs.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (rr != null ? rr.hashCode() : 0);
        result = 31 * result + (analysisResult != null ? analysisResult.hashCode() : 0);
        result = 31 * result + (rhythm != null ? rhythm.hashCode() : 0);
        return result;
    }
}
