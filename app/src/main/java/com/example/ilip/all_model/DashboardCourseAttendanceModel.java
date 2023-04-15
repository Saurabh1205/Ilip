package com.example.ilip.all_model;


import java.io.Serializable;

public class DashboardCourseAttendanceModel implements Serializable {

    String courseTitle,courseType,courseUniversityCode,courseFacultyName,courseRatio1,courseRatio2,coursePercentage;
    int BackGroundImage;
    String subjectEmployeeId,subjectDetailsId,subjectGroupId,SubjectApplicableNo,SubjectBatchDetailId;
    String Compulsory_Optional_Flag;

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCourseUniversityCode() {
        return courseUniversityCode;
    }

    public void setCourseUniversityCode(String courseUniversityCode) {
        this.courseUniversityCode = courseUniversityCode;
    }

    public String getCourseFacultyName() {
        return courseFacultyName;
    }

    public void setCourseFacultyName(String courseFacultyName) {
        this.courseFacultyName = courseFacultyName;
    }

    public String getCourseRatio1() {
        return courseRatio1;
    }

    public void setCourseRatio1(String courseRatio1) {
        this.courseRatio1 = courseRatio1;
    }

    public String getCourseRatio2() {
        return courseRatio2;
    }

    public void setCourseRatio2(String courseRatio2) {
        this.courseRatio2 = courseRatio2;
    }

    public String getCoursePercentage() {
        return coursePercentage;
    }

    public void setCoursePercentage(String coursePercentage) {
        this.coursePercentage = coursePercentage;
    }

    public int getBackGroundImage() {
        return BackGroundImage;
    }

    public void setBackGroundImage(int backGroundImage) {
        BackGroundImage = backGroundImage;
    }

    String dayWiseSubDtl,dayWiseSubType,dayWiseEmpName,dayWiseASON_date,dayWisePeriodTypeCode,dayWiseTimeDuration,dayWiseAttendStatusSCode,dayWiseAttendanceStatus;
    String topicName,topicDescription,syllabusDescription;
    int daywiseImageAttendStus;

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicDescription() {
        return topicDescription;
    }

    public void setTopicDescription(String topicDescription) {
        this.topicDescription = topicDescription;
    }

    public String getSyllabusDescription() {
        return syllabusDescription;
    }

    public void setSyllabusDescription(String syllabusDescription) {
        this.syllabusDescription = syllabusDescription;
    }

    public String getCompulsory_Optional_Flag() {
        return Compulsory_Optional_Flag;
    }

    public void setCompulsory_Optional_Flag(String compulsory_Optional_Flag) {
        Compulsory_Optional_Flag = compulsory_Optional_Flag;
    }

    public int getDaywiseImageAttendStus() {
        return daywiseImageAttendStus;
    }

    public void setDaywiseImageAttendStus(int daywiseImageAttendStus) {
        this.daywiseImageAttendStus = daywiseImageAttendStus;
    }

    public String getDayWiseSubDtl() {
        return dayWiseSubDtl;
    }

    public void setDayWiseSubDtl(String dayWiseSubDtl) {
        this.dayWiseSubDtl = dayWiseSubDtl;
    }

    public String getDayWiseSubType() {
        return dayWiseSubType;
    }

    public void setDayWiseSubType(String dayWiseSubType) {
        this.dayWiseSubType = dayWiseSubType;
    }

    public String getDayWiseEmpName() {
        return dayWiseEmpName;
    }

    public void setDayWiseEmpName(String dayWiseEmpName) {
        this.dayWiseEmpName = dayWiseEmpName;
    }

    public String getDayWiseASON_date() {
        return dayWiseASON_date;
    }

    public void setDayWiseASON_date(String dayWiseASON_date) {
        this.dayWiseASON_date = dayWiseASON_date;
    }

    public String getDayWisePeriodTypeCode() {
        return dayWisePeriodTypeCode;
    }

    public void setDayWisePeriodTypeCode(String dayWisePeriodTypeCode) {
        this.dayWisePeriodTypeCode = dayWisePeriodTypeCode;
    }

    public String getDayWiseTimeDuration() {
        return dayWiseTimeDuration;
    }

    public void setDayWiseTimeDuration(String dayWiseTimeDuration) {
        this.dayWiseTimeDuration = dayWiseTimeDuration;
    }

    public String getDayWiseAttendStatusSCode() {
        return dayWiseAttendStatusSCode;
    }

    public void setDayWiseAttendStatusSCode(String dayWiseAttendStatusSCode) {
        this.dayWiseAttendStatusSCode = dayWiseAttendStatusSCode;
    }

    public String getDayWiseAttendanceStatus() {
        return dayWiseAttendanceStatus;
    }

    public void setDayWiseAttendanceStatus(String dayWiseAttendanceStatus) {
        this.dayWiseAttendanceStatus = dayWiseAttendanceStatus;
    }

    String semWiseStatistic,semWiseAttendance;

    public String getSemWiseStatistic() {
        return semWiseStatistic;
    }

    public void setSemWiseStatistic(String semWiseStatistic) {
        this.semWiseStatistic = semWiseStatistic;
    }

    public String getSemWiseAttendance() {
        return semWiseAttendance;
    }

    public void setSemWiseAttendance(String semWiseAttendance) {
        this.semWiseAttendance = semWiseAttendance;
    }

    public String getSubjectEmployeeId() {
        return subjectEmployeeId;
    }

    public void setSubjectEmployeeId(String subjectEmployeeId) {
        this.subjectEmployeeId = subjectEmployeeId;
    }

    public String getSubjectDetailsId() {
        return subjectDetailsId;
    }

    public void setSubjectDetailsId(String subjectDetailsId) {
        this.subjectDetailsId = subjectDetailsId;
    }

    public String getSubjectGroupId() {
        return subjectGroupId;
    }

    public void setSubjectGroupId(String subjectGroupId) {
        this.subjectGroupId = subjectGroupId;
    }

    public String getSubjectApplicableNo() {
        return SubjectApplicableNo;
    }

    public void setSubjectApplicableNo(String subjectApplicableNo) {
        SubjectApplicableNo = subjectApplicableNo;
    }

    public String getSubjectBatchDetailId() {
        return SubjectBatchDetailId;
    }

    public void setSubjectBatchDetailId(String subjectBatchDetailId) {
        SubjectBatchDetailId = subjectBatchDetailId;
    }

    @Override
    public String toString() {
        return "DashboardCourseAttendanceModel{" +
                "courseTitle='" + courseTitle + '\'' +
                ", courseType='" + courseType + '\'' +
                ", courseUniversityCode='" + courseUniversityCode + '\'' +
                ", courseFacultyName='" + courseFacultyName + '\'' +
                ", courseRatio1='" + courseRatio1 + '\'' +
                ", courseRatio2='" + courseRatio2 + '\'' +
                ", coursePercentage='" + coursePercentage + '\'' +
                ", BackGroundImage=" + BackGroundImage +
                ", subjectEmployeeId='" + subjectEmployeeId + '\'' +
                ", subjectDetailsId='" + subjectDetailsId + '\'' +
                ", subjectGroupId='" + subjectGroupId + '\'' +
                ", SubjectApplicableNo='" + SubjectApplicableNo + '\'' +
                ", SubjectBatchDetailId='" + SubjectBatchDetailId + '\'' +
                ", Compulsory_Optional_Flag='" + Compulsory_Optional_Flag + '\'' +
                ", dayWiseSubDtl='" + dayWiseSubDtl + '\'' +
                ", dayWiseSubType='" + dayWiseSubType + '\'' +
                ", dayWiseEmpName='" + dayWiseEmpName + '\'' +
                ", dayWiseASON_date='" + dayWiseASON_date + '\'' +
                ", dayWisePeriodTypeCode='" + dayWisePeriodTypeCode + '\'' +
                ", dayWiseTimeDuration='" + dayWiseTimeDuration + '\'' +
                ", dayWiseAttendStatusSCode='" + dayWiseAttendStatusSCode + '\'' +
                ", dayWiseAttendanceStatus='" + dayWiseAttendanceStatus + '\'' +
                ", topicName='" + topicName + '\'' +
                ", topicDescription='" + topicDescription + '\'' +
                ", syllabusDescription='" + syllabusDescription + '\'' +
                ", daywiseImageAttendStus=" + daywiseImageAttendStus +
                ", semWiseStatistic='" + semWiseStatistic + '\'' +
                ", semWiseAttendance='" + semWiseAttendance + '\'' +
                '}';
    }
}
