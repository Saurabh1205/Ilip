package com.example.ilip.all_model;

import java.io.Serializable;

public class CommonModel implements Serializable {

    String loginType="Student",studentLoginId="",studentInstituteCode="",studentMobileNo="",studentPassword="";
    String fourPinPassword="";
    String OTP_Pin="";
    String ProfileImageURL;
    String minDate,MaxDate;
    String daysAttend,daysOutOff,attendancePercent;

    String NotificationSummary;
    String ScreenCallFrom="";
    String ClientDB;

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getStudentLoginId() {
        return studentLoginId;
    }

    public void setStudentLoginId(String studentLoginId) {
        this.studentLoginId = studentLoginId;
    }

    public String getStudentInstituteCode() {
        return studentInstituteCode;
    }

    public void setStudentInstituteCode(String studentInstituteCode) {
        this.studentInstituteCode = studentInstituteCode;
    }

    public String getStudentMobileNo() {
        return studentMobileNo;
    }

    public void setStudentMobileNo(String studentMobileNo) {
        this.studentMobileNo = studentMobileNo;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getFourPinPassword() {
        return fourPinPassword;
    }

    public void setFourPinPassword(String fourPinPassword) {
        this.fourPinPassword = fourPinPassword;
    }

    public String getOTP_Pin() {
        return OTP_Pin;
    }

    public void setOTP_Pin(String OTP_Pin) {
        this.OTP_Pin = OTP_Pin;
    }

    public String getProfileImageURL() {
        return ProfileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        ProfileImageURL = profileImageURL;
    }

    public String getMinDate() {
        return minDate;
    }

    public void setMinDate(String minDate) {
        this.minDate = minDate;
    }

    public String getMaxDate() {
        return MaxDate;
    }

    public void setMaxDate(String maxDate) {
        MaxDate = maxDate;
    }

    public String getDaysAttend() {
        return daysAttend;
    }

    public void setDaysAttend(String daysAttend) {
        this.daysAttend = daysAttend;
    }

    public String getDaysOutOff() {
        return daysOutOff;
    }

    public void setDaysOutOff(String daysOutOff) {
        this.daysOutOff = daysOutOff;
    }

    public String getAttendancePercent() {
        return attendancePercent;
    }

    public void setAttendancePercent(String attendancePercent) {
        this.attendancePercent = attendancePercent;
    }

    public String getNotificationSummary() {
        return NotificationSummary;
    }

    public void setNotificationSummary(String notificationSummary) {
        NotificationSummary = notificationSummary;
    }

    public String getScreenCallFrom() {
        return ScreenCallFrom;
    }

    public void setScreenCallFrom(String screenCallFrom) {
        ScreenCallFrom = screenCallFrom;
    }
    ///////////////After Login variables ///////////////////

    String BRANCH_STANDARD_ID,STUDENT_CODE,CENTRE_CODE,CENTRE_GROUP_CODE,BRANCHSTANDARDID,STUDENTCODE,
            MAIN_SEMESTER_MST_ID,ACAD_SESSION_ID,ACC_BALSHEET_MST_ID,STUDENT_ID,BRANCH_STANDARD_GRP_ID,BRANCH_STANDARD_GROUP_CODE;

    public String getBRANCH_STANDARD_ID() {
        return BRANCH_STANDARD_ID;
    }

    public void setBRANCH_STANDARD_ID(String BRANCH_STANDARD_ID) {
        this.BRANCH_STANDARD_ID = BRANCH_STANDARD_ID;
    }

    public String getSTUDENT_CODE() {
        return STUDENT_CODE;
    }

    public void setSTUDENT_CODE(String STUDENT_CODE) {
        this.STUDENT_CODE = STUDENT_CODE;
    }

    public String getCENTRE_CODE() {
        return CENTRE_CODE;
    }


    public void setCENTRE_CODE(String CENTRE_CODE) {
        this.CENTRE_CODE = CENTRE_CODE;
    }

    public String getCENTRE_GROUP_CODE() {
        return CENTRE_GROUP_CODE;
    }

    public void setCENTRE_GROUP_CODE(String CENTRE_GROUP_CODE) {
        this.CENTRE_GROUP_CODE = CENTRE_GROUP_CODE;
    }

    public String getBRANCHSTANDARDID() {
        return BRANCHSTANDARDID;
    }

    public void setBRANCHSTANDARDID(String BRANCHSTANDARDID) {
        this.BRANCHSTANDARDID = BRANCHSTANDARDID;
    }

    public String getSTUDENTCODE() {
        return STUDENTCODE;
    }

    public void setSTUDENTCODE(String STUDENTCODE) {
        this.STUDENTCODE = STUDENTCODE;
    }

    public String getMAIN_SEMESTER_MST_ID() {
        return MAIN_SEMESTER_MST_ID;
    }

    public void setMAIN_SEMESTER_MST_ID(String MAIN_SEMESTER_MST_ID) {
        this.MAIN_SEMESTER_MST_ID = MAIN_SEMESTER_MST_ID;
    }

    public String getACAD_SESSION_ID() {
        return ACAD_SESSION_ID;
    }

    public void setACAD_SESSION_ID(String ACAD_SESSION_ID) {
        this.ACAD_SESSION_ID = ACAD_SESSION_ID;
    }

    public String getACC_BALSHEET_MST_ID() {
        return ACC_BALSHEET_MST_ID;
    }

    public void setACC_BALSHEET_MST_ID(String ACC_BALSHEET_MST_ID) {
        this.ACC_BALSHEET_MST_ID = ACC_BALSHEET_MST_ID;
    }

    public String getSTUDENT_ID() {
        return STUDENT_ID;
    }

    public void setSTUDENT_ID(String STUDENT_ID) {
        this.STUDENT_ID = STUDENT_ID;
    }

    public String getBRANCH_STANDARD_GRP_ID() {
        return BRANCH_STANDARD_GRP_ID;
    }

    public void setBRANCH_STANDARD_GRP_ID(String BRANCH_STANDARD_GRP_ID) {
        this.BRANCH_STANDARD_GRP_ID = BRANCH_STANDARD_GRP_ID;
    }

    public String getBRANCH_STANDARD_GROUP_CODE() {
        return BRANCH_STANDARD_GROUP_CODE;
    }

    public void setBRANCH_STANDARD_GROUP_CODE(String BRANCH_STANDARD_GROUP_CODE) {
        this.BRANCH_STANDARD_GROUP_CODE = BRANCH_STANDARD_GROUP_CODE;
    }
    /////////////Attendance data///////////////
    String SemesterCode,CurrentSession;

    public String getSemesterCode() {
        return SemesterCode;
    }

    public void setSemesterCode(String semesterCode) {
        SemesterCode = semesterCode;
    }

    public String getCurrentSession() {
        return CurrentSession;
    }

    public void setCurrentSession(String currentSession) {
        CurrentSession = currentSession;
    }

    ////////////////Subject Wise//////////////////
    String SelectedSubjectName,SubjectType,SubjectEmployeeName,SubjectDetailId,SubjectGroupId,SubjectApplicableNo,SubjectBatchDtlId,SubjectEmployeeIds;
    public String getSelectedSubjectName() {
        return SelectedSubjectName;
    }


    public void setSelectedSubjectName(String selectedSubjectName) {
        SelectedSubjectName = selectedSubjectName;
    }

    public String getSubjectDetailId() {
        return SubjectDetailId;
    }

    public void setSubjectDetailId(String subjectDetailId) {
        SubjectDetailId = subjectDetailId;
    }

    public String getSubjectGroupId() {
        return SubjectGroupId;
    }

    public void setSubjectGroupId(String subjectGroupId) {
        SubjectGroupId = subjectGroupId;
    }

    public String getSubjectApplicableNo() {
        return SubjectApplicableNo;
    }

    public void setSubjectApplicableNo(String subjectApplicableNo) {
        SubjectApplicableNo = subjectApplicableNo;
    }

    public String getSubjectBatchDtlId() {
        return SubjectBatchDtlId;
    }

    public void setSubjectBatchDtlId(String subjectBatchDtlId) {
        SubjectBatchDtlId = subjectBatchDtlId;
    }

    public String getSubjectEmployeeIds() {
        return SubjectEmployeeIds;
    }

    public void setSubjectEmployeeIds(String subjectEmployeeIds) {
        SubjectEmployeeIds = subjectEmployeeIds;
    }

    public String getSubjectType() {
        return SubjectType;
    }

    public void setSubjectType(String subjectType) {
        SubjectType = subjectType;
    }

    public String getSubjectEmployeeName() {
        return SubjectEmployeeName;
    }

    public void setSubjectEmployeeName(String subjectEmployeeName) {
        SubjectEmployeeName = subjectEmployeeName;
    }

    public String getClientDB() {
        return ClientDB;
    }

    public void setClientDB(String clientDB) {
        ClientDB = clientDB;
    }

    @Override
    public String toString() {
        return "CommonModel{" +
                "loginType='" + loginType + '\'' +
                ", studentLoginId='" + studentLoginId + '\'' +
                ", studentInstituteCode='" + studentInstituteCode + '\'' +
                ", studentMobileNo='" + studentMobileNo + '\'' +
                ", studentPassword='" + studentPassword + '\'' +
                ", fourPinPassword='" + fourPinPassword + '\'' +
                ", OTP_Pin='" + OTP_Pin + '\'' +
                ", ProfileImageURL='" + ProfileImageURL + '\'' +
                ", minDate='" + minDate + '\'' +
                ", MaxDate='" + MaxDate + '\'' +
                ", daysAttend='" + daysAttend + '\'' +
                ", daysOutOff='" + daysOutOff + '\'' +
                ", attendancePercent='" + attendancePercent + '\'' +
                ", NotificationSummary='" + NotificationSummary + '\'' +
                ", ScreenCallFrom='" + ScreenCallFrom + '\'' +
                ", ClientDB='" + ClientDB + '\'' +
                ", BRANCH_STANDARD_ID='" + BRANCH_STANDARD_ID + '\'' +
                ", STUDENT_CODE='" + STUDENT_CODE + '\'' +
                ", CENTRE_CODE='" + CENTRE_CODE + '\'' +
                ", CENTRE_GROUP_CODE='" + CENTRE_GROUP_CODE + '\'' +
                ", BRANCHSTANDARDID='" + BRANCHSTANDARDID + '\'' +
                ", STUDENTCODE='" + STUDENTCODE + '\'' +
                ", MAIN_SEMESTER_MST_ID='" + MAIN_SEMESTER_MST_ID + '\'' +
                ", ACAD_SESSION_ID='" + ACAD_SESSION_ID + '\'' +
                ", ACC_BALSHEET_MST_ID='" + ACC_BALSHEET_MST_ID + '\'' +
                ", STUDENT_ID='" + STUDENT_ID + '\'' +
                ", BRANCH_STANDARD_GRP_ID='" + BRANCH_STANDARD_GRP_ID + '\'' +
                ", BRANCH_STANDARD_GROUP_CODE='" + BRANCH_STANDARD_GROUP_CODE + '\'' +
                ", SemesterCode='" + SemesterCode + '\'' +
                ", CurrentSession='" + CurrentSession + '\'' +
                ", SelectedSubjectName='" + SelectedSubjectName + '\'' +
                ", SubjectType='" + SubjectType + '\'' +
                ", SubjectEmployeeName='" + SubjectEmployeeName + '\'' +
                ", SubjectDetailId='" + SubjectDetailId + '\'' +
                ", SubjectGroupId='" + SubjectGroupId + '\'' +
                ", SubjectApplicableNo='" + SubjectApplicableNo + '\'' +
                ", SubjectBatchDtlId='" + SubjectBatchDtlId + '\'' +
                ", SubjectEmployeeIds='" + SubjectEmployeeIds + '\'' +
                '}';
    }
}
