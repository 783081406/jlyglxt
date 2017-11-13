package com.ccjjltx.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ccjjltx on 2017/11/8.
 * 病例详细表
 *
 * @author ccj
 * @version 1.0
 */
@Entity
@Table
public class Casehistory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chId;
    private String physician;
    private String hospital;
    private String hospitalPhone;
    private String allergyDrugs;
    private String majorDiseases;
    private String height;
    private String weight;
    private String highHanded;
    private String lowHanded;
    private String bloodOxygenValue;
    private String fastingPlasmaGlucose;
    private String postprandialBloodGlucose;
    private String totalCholesterol;
    private String triglyceride;
    private String hdlc;
    private String ldlc;
    @OneToOne(targetEntity = Elder.class)
    @JoinColumn(name = "eId")
    private Elder elder;

    //无参数构造函数
    public Casehistory() {
    }

    //有参数构造函数
    public Casehistory(String physician, String hospital, String hospitalPhone, String allergyDrugs, String majorDiseases, String height, String weight) {
        this.physician = physician;
        this.hospital = hospital;
        this.hospitalPhone = hospitalPhone;
        this.allergyDrugs = allergyDrugs;
        this.majorDiseases = majorDiseases;
        this.height = height;
        this.weight = weight;
    }

    public Casehistory(String physician, String hospital, String hospitalPhone, String allergyDrugs, String majorDiseases, String height, String weight, String highHanded, String lowHanded, String bloodOxygenValue, String fastingPlasmaGlucose, String postprandialBloodGlucose, String totalCholesterol, String triglyceride, String hdlc, String ldlc, Elder elder) {
        this.physician = physician;
        this.hospital = hospital;
        this.hospitalPhone = hospitalPhone;
        this.allergyDrugs = allergyDrugs;
        this.majorDiseases = majorDiseases;
        this.height = height;
        this.weight = weight;
        this.highHanded = highHanded;
        this.lowHanded = lowHanded;
        this.bloodOxygenValue = bloodOxygenValue;
        this.fastingPlasmaGlucose = fastingPlasmaGlucose;
        this.postprandialBloodGlucose = postprandialBloodGlucose;
        this.totalCholesterol = totalCholesterol;
        this.triglyceride = triglyceride;
        this.hdlc = hdlc;
        this.ldlc = ldlc;
        this.elder = elder;
    }

    public int getChId() {
        return chId;
    }

    public void setChId(int chId) {
        this.chId = chId;
    }

    public String getPhysician() {
        return physician;
    }

    public void setPhysician(String physician) {
        this.physician = physician;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getHospitalPhone() {
        return hospitalPhone;
    }

    public void setHospitalPhone(String hospitalPhone) {
        this.hospitalPhone = hospitalPhone;
    }

    public String getAllergyDrugs() {
        return allergyDrugs;
    }

    public void setAllergyDrugs(String allergyDrugs) {
        this.allergyDrugs = allergyDrugs;
    }

    public String getMajorDiseases() {
        return majorDiseases;
    }

    public void setMajorDiseases(String majorDiseases) {
        this.majorDiseases = majorDiseases;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHighHanded() {
        return highHanded;
    }

    public void setHighHanded(String highHanded) {
        this.highHanded = highHanded;
    }

    public String getLowHanded() {
        return lowHanded;
    }

    public void setLowHanded(String lowHanded) {
        this.lowHanded = lowHanded;
    }

    public String getBloodOxygenValue() {
        return bloodOxygenValue;
    }

    public void setBloodOxygenValue(String bloodOxygenValue) {
        this.bloodOxygenValue = bloodOxygenValue;
    }

    public String getFastingPlasmaGlucose() {
        return fastingPlasmaGlucose;
    }

    public void setFastingPlasmaGlucose(String fastingPlasmaGlucose) {
        this.fastingPlasmaGlucose = fastingPlasmaGlucose;
    }

    public String getPostprandialBloodGlucose() {
        return postprandialBloodGlucose;
    }

    public void setPostprandialBloodGlucose(String postprandialBloodGlucose) {
        this.postprandialBloodGlucose = postprandialBloodGlucose;
    }

    public String getTotalCholesterol() {
        return totalCholesterol;
    }

    public void setTotalCholesterol(String totalCholesterol) {
        this.totalCholesterol = totalCholesterol;
    }

    public String getTriglyceride() {
        return triglyceride;
    }

    public void setTriglyceride(String triglyceride) {
        this.triglyceride = triglyceride;
    }

    public String getHdlc() {
        return hdlc;
    }

    public void setHdlc(String hdlc) {
        this.hdlc = hdlc;
    }

    public String getLdlc() {
        return ldlc;
    }

    public void setLdlc(String ldlc) {
        this.ldlc = ldlc;
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

        Casehistory that = (Casehistory) o;

        if (chId != that.chId) return false;
        if (physician != null ? !physician.equals(that.physician) : that.physician != null) return false;
        if (hospital != null ? !hospital.equals(that.hospital) : that.hospital != null) return false;
        if (hospitalPhone != null ? !hospitalPhone.equals(that.hospitalPhone) : that.hospitalPhone != null) return false;
        if (allergyDrugs != null ? !allergyDrugs.equals(that.allergyDrugs) : that.allergyDrugs != null) return false;
        if (majorDiseases != null ? !majorDiseases.equals(that.majorDiseases) : that.majorDiseases != null)
            return false;
        if (height != null ? !height.equals(that.height) : that.height != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        if (highHanded != null ? !highHanded.equals(that.highHanded) : that.highHanded != null) return false;
        if (lowHanded != null ? !lowHanded.equals(that.lowHanded) : that.lowHanded != null) return false;
        if (bloodOxygenValue != null ? !bloodOxygenValue.equals(that.bloodOxygenValue) : that.bloodOxygenValue != null)
            return false;
        if (fastingPlasmaGlucose != null ? !fastingPlasmaGlucose.equals(that.fastingPlasmaGlucose) : that.fastingPlasmaGlucose != null)
            return false;
        if (postprandialBloodGlucose != null ? !postprandialBloodGlucose.equals(that.postprandialBloodGlucose) : that.postprandialBloodGlucose != null)
            return false;
        if (totalCholesterol != null ? !totalCholesterol.equals(that.totalCholesterol) : that.totalCholesterol != null)
            return false;
        if (triglyceride != null ? !triglyceride.equals(that.triglyceride) : that.triglyceride != null) return false;
        if (hdlc != null ? !hdlc.equals(that.hdlc) : that.hdlc != null) return false;
        if (ldlc != null ? !ldlc.equals(that.ldlc) : that.ldlc != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = chId;
        result = 31 * result + (physician != null ? physician.hashCode() : 0);
        result = 31 * result + (hospital != null ? hospital.hashCode() : 0);
        result = 31 * result + (hospitalPhone != null ? hospitalPhone.hashCode() : 0);
        result = 31 * result + (allergyDrugs != null ? allergyDrugs.hashCode() : 0);
        result = 31 * result + (majorDiseases != null ? majorDiseases.hashCode() : 0);
        result = 31 * result + (height != null ? height.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (highHanded != null ? highHanded.hashCode() : 0);
        result = 31 * result + (lowHanded != null ? lowHanded.hashCode() : 0);
        result = 31 * result + (bloodOxygenValue != null ? bloodOxygenValue.hashCode() : 0);
        result = 31 * result + (fastingPlasmaGlucose != null ? fastingPlasmaGlucose.hashCode() : 0);
        result = 31 * result + (postprandialBloodGlucose != null ? postprandialBloodGlucose.hashCode() : 0);
        result = 31 * result + (totalCholesterol != null ? totalCholesterol.hashCode() : 0);
        result = 31 * result + (triglyceride != null ? triglyceride.hashCode() : 0);
        result = 31 * result + (hdlc != null ? hdlc.hashCode() : 0);
        result = 31 * result + (ldlc != null ? ldlc.hashCode() : 0);
        return result;
    }
}
