package com.example.ilip.activities.attendance.subjectWise.subjectAndProfessorActivity;

import java.io.Serializable;

public class SubjectProfessorListModel implements Serializable {

    String professorId,professorName,professorDesignation,professorEmailID,professorMobile,professorAboutMe,ProfessorImageURL;

    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getProfessorDesignation() {
        return professorDesignation;
    }

    public void setProfessorDesignation(String professorDesignation) {
        this.professorDesignation = professorDesignation;
    }

    public String getProfessorEmailID() {
        return professorEmailID;
    }

    public void setProfessorEmailID(String professorEmailID) {
        this.professorEmailID = professorEmailID;
    }

    public String getProfessorMobile() {
        return professorMobile;
    }

    public void setProfessorMobile(String professorMobile) {
        this.professorMobile = professorMobile;
    }

    public String getProfessorAboutMe() {
        return professorAboutMe;
    }

    public void setProfessorAboutMe(String professorAboutMe) {
        this.professorAboutMe = professorAboutMe;
    }

    public String getProfessorImageURL() {
        return ProfessorImageURL;
    }

    public void setProfessorImageURL(String professorImageURL) {
        ProfessorImageURL = professorImageURL;
    }

    @Override
    public String toString() {
        return "SubjectProfessorListModel{" +
                "professorId='" + professorId + '\'' +
                ", professorName='" + professorName + '\'' +
                ", professorDesignation='" + professorDesignation + '\'' +
                ", professorEmailID='" + professorEmailID + '\'' +
                ", professorMobile='" + professorMobile + '\'' +
                ", professorAboutMe='" + professorAboutMe + '\'' +
                ", ProfessorImageURL='" + ProfessorImageURL + '\'' +
                '}';
    }
}
