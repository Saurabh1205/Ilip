package com.example.ilip.activities.schedule;

import java.io.Serializable;

public class ScheduleClassModel implements Serializable {

    String dayScheduleCount,offStatus,extraLectureCount;
    String date,timeSlot,subDescription,subjectType,universityCode,employeeNameShort,roomCode,subjectBatchName;
    String RecessDuration,RecessDescription,Weekly_Off_Status;


    public String getDayScheduleCount() {
        return dayScheduleCount;
    }

    public void setDayScheduleCount(String dayScheduleCount) {
        this.dayScheduleCount = dayScheduleCount;
    }

    public String getOffStatus() {
        return offStatus;
    }

    public void setOffStatus(String offStatus) {
        this.offStatus = offStatus;
    }

    public String getExtraLectureCount() {
        return extraLectureCount;
    }

    public void setExtraLectureCount(String extraLectureCount) {
        this.extraLectureCount = extraLectureCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getSubDescription() {
        return subDescription;
    }

    public void setSubDescription(String subDescription) {
        this.subDescription = subDescription;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public String getUniversityCode() {
        return universityCode;
    }

    public void setUniversityCode(String universityCode) {
        this.universityCode = universityCode;
    }

    public String getEmployeeNameShort() {
        return employeeNameShort;
    }

    public void setEmployeeNameShort(String employeeNameShort) {
        this.employeeNameShort = employeeNameShort;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getSubjectBatchName() {
        return subjectBatchName;
    }

    public void setSubjectBatchName(String subjectBatchName) {
        this.subjectBatchName = subjectBatchName;
    }

    public String getRecessDuration() {
        return RecessDuration;
    }

    public void setRecessDuration(String recessDuration) {
        RecessDuration = recessDuration;
    }

    public String getRecessDescription() {
        return RecessDescription;
    }

    public void setRecessDescription(String recessDescription) {
        RecessDescription = recessDescription;
    }

    public String getWeekly_Off_Status() {
        return Weekly_Off_Status;
    }

    public void setWeekly_Off_Status(String weekly_Off_Status) {
        Weekly_Off_Status = weekly_Off_Status;
    }

    @Override
    public String toString() {
        return "ScheduleClassModel{" +
                "dayScheduleCount='" + dayScheduleCount + '\'' +
                ", offStatus='" + offStatus + '\'' +
                ", extraLectureCount='" + extraLectureCount + '\'' +
                ", date='" + date + '\'' +
                ", timeSlot='" + timeSlot + '\'' +
                ", subDescription='" + subDescription + '\'' +
                ", subjectType='" + subjectType + '\'' +
                ", universityCode='" + universityCode + '\'' +
                ", employeeNameShort='" + employeeNameShort + '\'' +
                ", roomCode='" + roomCode + '\'' +
                ", subjectBatchName='" + subjectBatchName + '\'' +
                ", RecessDuration='" + RecessDuration + '\'' +
                ", RecessDescription='" + RecessDescription + '\'' +
                ", Weekly_Off_Status='" + Weekly_Off_Status + '\'' +
                '}';
    }
}
