package com.ccjjltx.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ccjjltx on 2017/11/8.
 * 就医记录
 *
 * @author ccj
 */
@Entity
@Table
public class Medicalrecord implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mrId;
    private String mrpPlace;
    private String medicalDoctor;
    private String diagnosisResult;
    private String advice;
    @ManyToOne(targetEntity = Casehistory.class)
    @JoinColumn(name = "chId")
    private Casehistory casehistory;

    //无参构造器
    public Medicalrecord() {
    }

    //有参构造器
    public Medicalrecord(String mrpPlace, String medicalDoctor, String diagnosisResult, String advice) {
        this.mrpPlace = mrpPlace;
        this.medicalDoctor = medicalDoctor;
        this.diagnosisResult = diagnosisResult;
        this.advice = advice;
    }

    public int getMrId() {
        return mrId;
    }

    public void setMrId(int mrId) {
        this.mrId = mrId;
    }

    public String getMrpPlace() {
        return mrpPlace;
    }

    public void setMrpPlace(String mrpPlace) {
        this.mrpPlace = mrpPlace;
    }

    public String getMedicalDoctor() {
        return medicalDoctor;
    }

    public void setMedicalDoctor(String medicalDoctor) {
        this.medicalDoctor = medicalDoctor;
    }

    public String getDiagnosisResult() {
        return diagnosisResult;
    }

    public void setDiagnosisResult(String diagnosisResult) {
        this.diagnosisResult = diagnosisResult;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
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

        Medicalrecord that = (Medicalrecord) o;

        if (mrId != that.mrId) return false;
        if (mrpPlace != null ? !mrpPlace.equals(that.mrpPlace) : that.mrpPlace != null) return false;
        if (medicalDoctor != null ? !medicalDoctor.equals(that.medicalDoctor) : that.medicalDoctor != null)
            return false;
        if (diagnosisResult != null ? !diagnosisResult.equals(that.diagnosisResult) : that.diagnosisResult != null)
            return false;
        if (advice != null ? !advice.equals(that.advice) : that.advice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mrId;
        result = 31 * result + (mrpPlace != null ? mrpPlace.hashCode() : 0);
        result = 31 * result + (medicalDoctor != null ? medicalDoctor.hashCode() : 0);
        result = 31 * result + (diagnosisResult != null ? diagnosisResult.hashCode() : 0);
        result = 31 * result + (advice != null ? advice.hashCode() : 0);
        return result;
    }
}
