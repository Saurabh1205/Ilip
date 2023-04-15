package com.example.ilip.activities.syllabus;

import java.io.Serializable;

public class UniversitySyllabusModel implements Serializable {
    String SR_NO,SYLLABUS_DESCRIPTION,WEIGHTAGE,TOPIC_NAME,TOPIC_DESCRIPTION;
    String PLANED_START_DATE,PLANED_END_DATE,TOPIC_COVERED_DATE;
    public String getSR_NO() {
        return SR_NO;
    }

    public void setSR_NO(String SR_NO) {
        this.SR_NO = SR_NO;
    }

    public String getSYLLABUS_DESCRIPTION() {
        return SYLLABUS_DESCRIPTION;
    }

    public void setSYLLABUS_DESCRIPTION(String SYLLABUS_DESCRIPTION) {
        this.SYLLABUS_DESCRIPTION = SYLLABUS_DESCRIPTION;
    }

    public String getWEIGHTAGE() {
        return WEIGHTAGE;
    }

    public void setWEIGHTAGE(String WEIGHTAGE) {
        this.WEIGHTAGE = WEIGHTAGE;
    }

    public String getTOPIC_NAME() {
        return TOPIC_NAME;
    }

    public void setTOPIC_NAME(String TOPIC_NAME) {
        this.TOPIC_NAME = TOPIC_NAME;
    }

    public String getTOPIC_DESCRIPTION() {
        return TOPIC_DESCRIPTION;
    }

    public void setTOPIC_DESCRIPTION(String TOPIC_DESCRIPTION) {
        this.TOPIC_DESCRIPTION = TOPIC_DESCRIPTION;
    }

    public String getPLANED_START_DATE() {
        return PLANED_START_DATE;
    }

    public void setPLANED_START_DATE(String PLANED_START_DATE) {
        this.PLANED_START_DATE = PLANED_START_DATE;
    }

    public String getPLANED_END_DATE() {
        return PLANED_END_DATE;
    }

    public void setPLANED_END_DATE(String PLANED_END_DATE) {
        this.PLANED_END_DATE = PLANED_END_DATE;
    }

    public String getTOPIC_COVERED_DATE() {
        return TOPIC_COVERED_DATE;
    }

    public void setTOPIC_COVERED_DATE(String TOPIC_COVERED_DATE) {
        this.TOPIC_COVERED_DATE = TOPIC_COVERED_DATE;
    }

    @Override
    public String toString() {
        return "UniversitySyllabusModel{" +
                "SR_NO='" + SR_NO + '\'' +
                ", SYLLABUS_DESCRIPTION='" + SYLLABUS_DESCRIPTION + '\'' +
                ", WEIGHTAGE='" + WEIGHTAGE + '\'' +
                ", TOPIC_NAME='" + TOPIC_NAME + '\'' +
                ", TOPIC_DESCRIPTION='" + TOPIC_DESCRIPTION + '\'' +
                ", PLANED_START_DATE='" + PLANED_START_DATE + '\'' +
                ", PLANED_END_DATE='" + PLANED_END_DATE + '\'' +
                ", TOPIC_COVERED_DATE='" + TOPIC_COVERED_DATE + '\'' +
                '}';
    }
}
