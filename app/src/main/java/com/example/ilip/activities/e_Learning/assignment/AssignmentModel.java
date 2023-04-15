package com.example.ilip.activities.e_Learning.assignment;

import java.util.List;

public class AssignmentModel {
    /**
     * status : 1
     * StudAssign_Dtls : [{"ASSIGN_ID":"1099","ASSIGN_TYPE":"TEXT","ASSIGN_DESCRIPTION":"SMD Subject description","ASSIGN_SUBMISSION_TYPE":"SOFT COPY","ASSIGN_SUBMI_FROM_DATE":"27/02/2019","ASSIGN_SUBMI_UPTO_DATE":"16/03/2019","SUBJECT_DESCRIPTION":"Software Modeling and Design (2015) THEORY","ASSIGN_FILE_DETAILS":"19_17_E_11246_1\\E1039140001_27219_1116557091419.pdf","EMP_REMARK":"assignment has been published.Submit the assignment upto last date of submission.","FACULTY_NAME":"Dr. SHEKHAR S SADAWATE","DOWNLOAD_PATH":"192.168.1.151\\d\\EmployeeDocuments\\Assignment\\Employee\\19_17_E_11246_1\\E1039140001_27219_1116557091419.pdf","BRANCH_STANDARD_ID":"17","SUBJECT_DETAIL_ID":"11246","APPLICABLE_NUMBER":"1","SEMESTER_TYPE":"E","ASSIGN_SUBMISSION_ID":"0","SUBMISSION_DATE":"","ACAD_ASG_FACUL_REMARK_ID":"","FACULTY_REMARK":"","CORRECTION_REMARK":"","ASG_SUBMISSION_PATH":"","TASK_FILE_NAME":"java.pdf","STU_SUBMISSION_STATUS":"","GRADE_DETAIL_ID":"","GRADE_CODE":"","OBTAINED_MARKS":"","EVALUATION_TYPE":"","MARKS_OUT_OF":""},{"ASSIGN_ID":"1100","ASSIGN_TYPE":"TEXT","ASSIGN_DESCRIPTION":"SMD Assignment2","ASSIGN_SUBMISSION_TYPE":"SOFT COPY","ASSIGN_SUBMI_FROM_DATE":"27/02/2019","ASSIGN_SUBMI_UPTO_DATE":"16/03/2019","SUBJECT_DESCRIPTION":"Software Modeling and Design (2015) THEORY","ASSIGN_FILE_DETAILS":"19_17_E_11246_1\\E1039140001_27219_111978012570.pdf","EMP_REMARK":"submit the assignment upto last date.","FACULTY_NAME":"Dr. SHEKHAR S SADAWATE","DOWNLOAD_PATH":"192.168.1.151\\d\\EmployeeDocuments\\Assignment\\Employee\\19_17_E_11246_1\\E1039140001_27219_111978012570.pdf","BRANCH_STANDARD_ID":"17","SUBJECT_DETAIL_ID":"11246","APPLICABLE_NUMBER":"1","SEMESTER_TYPE":"E","ASSIGN_SUBMISSION_ID":"0","SUBMISSION_DATE":"","ACAD_ASG_FACUL_REMARK_ID":"","FACULTY_REMARK":"","CORRECTION_REMARK":"","ASG_SUBMISSION_PATH":"","TASK_FILE_NAME":"java.pdf","STU_SUBMISSION_STATUS":"","GRADE_DETAIL_ID":"","GRADE_CODE":"","OBTAINED_MARKS":"","EVALUATION_TYPE":"","MARKS_OUT_OF":""},{"ASSIGN_ID":"1101","ASSIGN_TYPE":"PROJECT","ASSIGN_DESCRIPTION":"SMD assignmnet3","ASSIGN_SUBMISSION_TYPE":"HARD COPY","ASSIGN_SUBMI_FROM_DATE":"27/02/2019","ASSIGN_SUBMI_UPTO_DATE":"16/03/2019","SUBJECT_DESCRIPTION":"Software Modeling and Design (2015) THEORY","ASSIGN_FILE_DETAILS":"19_17_E_11246_1\\E1039140001_27219_1120359511748.pdf","EMP_REMARK":"submit assignment no 3 up to last date.","FACULTY_NAME":"Dr. SHEKHAR S SADAWATE","DOWNLOAD_PATH":"192.168.1.151\\d\\EmployeeDocuments\\Assignment\\Employee\\19_17_E_11246_1\\E1039140001_27219_1120359511748.pdf","BRANCH_STANDARD_ID":"17","SUBJECT_DETAIL_ID":"11246","APPLICABLE_NUMBER":"1","SEMESTER_TYPE":"E","ASSIGN_SUBMISSION_ID":"0","SUBMISSION_DATE":"","ACAD_ASG_FACUL_REMARK_ID":"","FACULTY_REMARK":"","CORRECTION_REMARK":"","ASG_SUBMISSION_PATH":"","TASK_FILE_NAME":"java.pdf","STU_SUBMISSION_STATUS":"","GRADE_DETAIL_ID":"","GRADE_CODE":"","OBTAINED_MARKS":"","EVALUATION_TYPE":"","MARKS_OUT_OF":""}]
     */

    private int status;
    private List<StudAssignDtlsBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<StudAssignDtlsBean> getData() {
        return data;
    }

    public void setData(List<StudAssignDtlsBean> data) {
        this.data = data;
    }

    public static class StudAssignDtlsBean {
        /**
         * ASSIGN_ID : 1099
         * ASSIGN_TYPE : TEXT
         * ASSIGN_DESCRIPTION : SMD Subject description
         * ASSIGN_SUBMISSION_TYPE : SOFT COPY
         * ASSIGN_SUBMI_FROM_DATE : 27/02/2019
         * ASSIGN_SUBMI_UPTO_DATE : 16/03/2019
         * SUBJECT_DESCRIPTION : Software Modeling and Design (2015) THEORY
         * ASSIGN_FILE_DETAILS : 19_17_E_11246_1\E1039140001_27219_1116557091419.pdf
         * EMP_REMARK : assignment has been published.Submit the assignment upto last date of submission.
         * FACULTY_NAME : Dr. SHEKHAR S SADAWATE
         * DOWNLOAD_PATH : 192.168.1.151\d\EmployeeDocuments\Assignment\Employee\19_17_E_11246_1\E1039140001_27219_1116557091419.pdf
         * BRANCH_STANDARD_ID : 17
         * SUBJECT_DETAIL_ID : 11246
         * APPLICABLE_NUMBER : 1
         * SEMESTER_TYPE : E
         * ASSIGN_SUBMISSION_ID : 0
         * SUBMISSION_DATE :
         * ACAD_ASG_FACUL_REMARK_ID :
         * FACULTY_REMARK :
         * CORRECTION_REMARK :
         * ASG_SUBMISSION_PATH :
         * TASK_FILE_NAME : java.pdf
         * STU_SUBMISSION_STATUS :
         * GRADE_DETAIL_ID :
         * GRADE_CODE :
         * OBTAINED_MARKS :
         * EVALUATION_TYPE :
         * MARKS_OUT_OF :
         */

        private String ASSIGN_ID;
        private String ASSIGN_TYPE;
        private String ASSIGN_DESCRIPTION;
        private String ASSIGN_SUBMISSION_TYPE;
        private String ASSIGN_SUBMI_FROM_DATE;
        private String ASSIGN_SUBMI_UPTO_DATE;
        private String SUBJECT_DESCRIPTION;
        private String ASSIGN_FILE_DETAILS;
        private String EMP_REMARK;
        private String FACULTY_NAME;
        private String DOWNLOAD_PATH;
        private String BRANCH_STANDARD_ID;
        private String SUBJECT_DETAIL_ID;
        private String APPLICABLE_NUMBER;
        private String SEMESTER_TYPE;
        private String ASSIGN_SUBMISSION_ID;
        private String SUBMISSION_DATE;
        private String ACAD_ASG_FACUL_REMARK_ID;
        private String FACULTY_REMARK;
        private String CORRECTION_REMARK;
        private String ASG_SUBMISSION_PATH;
        private String TASK_FILE_NAME;
        private String STU_SUBMISSION_STATUS;
        private String GRADE_DETAIL_ID;
        private String GRADE_CODE;
        private String OBTAINED_MARKS;
        private String EVALUATION_TYPE;
        private String MARKS_OUT_OF;
        private String STUD_UPLOAD_FILE;
        private String STATIC_IP;
        private String DOCU_UPLOAD_STATIC_PATH;

        public String getSTATIC_IP() {
            return STATIC_IP;
        }

        public void setSTATIC_IP(String STATIC_IP) {
            this.STATIC_IP = STATIC_IP;
        }

        public String getDOCU_UPLOAD_STATIC_PATH() {
            return DOCU_UPLOAD_STATIC_PATH;
        }

        public void setDOCU_UPLOAD_STATIC_PATH(String DOCU_UPLOAD_STATIC_PATH) {
            this.DOCU_UPLOAD_STATIC_PATH = DOCU_UPLOAD_STATIC_PATH;
        }

        public String getSTUD_UPLOAD_FILE() {
            return STUD_UPLOAD_FILE;
        }

        public void setSTUD_UPLOAD_FILE(String STUD_UPLOAD_FILE) {
            this.STUD_UPLOAD_FILE = STUD_UPLOAD_FILE;
        }

        public String getASSIGN_ID() {
            return ASSIGN_ID;
        }

        public void setASSIGN_ID(String ASSIGN_ID) {
            this.ASSIGN_ID = ASSIGN_ID;
        }

        public String getASSIGN_TYPE() {
            return ASSIGN_TYPE;
        }

        public void setASSIGN_TYPE(String ASSIGN_TYPE) {
            this.ASSIGN_TYPE = ASSIGN_TYPE;
        }

        public String getASSIGN_DESCRIPTION() {
            return ASSIGN_DESCRIPTION;
        }

        public void setASSIGN_DESCRIPTION(String ASSIGN_DESCRIPTION) {
            this.ASSIGN_DESCRIPTION = ASSIGN_DESCRIPTION;
        }

        public String getASSIGN_SUBMISSION_TYPE() {
            return ASSIGN_SUBMISSION_TYPE;
        }

        public void setASSIGN_SUBMISSION_TYPE(String ASSIGN_SUBMISSION_TYPE) {
            this.ASSIGN_SUBMISSION_TYPE = ASSIGN_SUBMISSION_TYPE;
        }

        public String getASSIGN_SUBMI_FROM_DATE() {
            return ASSIGN_SUBMI_FROM_DATE;
        }

        public void setASSIGN_SUBMI_FROM_DATE(String ASSIGN_SUBMI_FROM_DATE) {
            this.ASSIGN_SUBMI_FROM_DATE = ASSIGN_SUBMI_FROM_DATE;
        }

        public String getASSIGN_SUBMI_UPTO_DATE() {
            return ASSIGN_SUBMI_UPTO_DATE;
        }

        public void setASSIGN_SUBMI_UPTO_DATE(String ASSIGN_SUBMI_UPTO_DATE) {
            this.ASSIGN_SUBMI_UPTO_DATE = ASSIGN_SUBMI_UPTO_DATE;
        }

        public String getSUBJECT_DESCRIPTION() {
            return SUBJECT_DESCRIPTION;
        }

        public void setSUBJECT_DESCRIPTION(String SUBJECT_DESCRIPTION) {
            this.SUBJECT_DESCRIPTION = SUBJECT_DESCRIPTION;
        }

        public String getASSIGN_FILE_DETAILS() {
            return ASSIGN_FILE_DETAILS;
        }

        public void setASSIGN_FILE_DETAILS(String ASSIGN_FILE_DETAILS) {
            this.ASSIGN_FILE_DETAILS = ASSIGN_FILE_DETAILS;
        }

        public String getEMP_REMARK() {
            return EMP_REMARK;
        }

        public void setEMP_REMARK(String EMP_REMARK) {
            this.EMP_REMARK = EMP_REMARK;
        }

        public String getFACULTY_NAME() {
            return FACULTY_NAME;
        }

        public void setFACULTY_NAME(String FACULTY_NAME) {
            this.FACULTY_NAME = FACULTY_NAME;
        }

        public String getDOWNLOAD_PATH() {
            return DOWNLOAD_PATH;
        }

        public void setDOWNLOAD_PATH(String DOWNLOAD_PATH) {
            this.DOWNLOAD_PATH = DOWNLOAD_PATH;
        }

        public String getBRANCH_STANDARD_ID() {
            return BRANCH_STANDARD_ID;
        }

        public void setBRANCH_STANDARD_ID(String BRANCH_STANDARD_ID) {
            this.BRANCH_STANDARD_ID = BRANCH_STANDARD_ID;
        }

        public String getSUBJECT_DETAIL_ID() {
            return SUBJECT_DETAIL_ID;
        }

        public void setSUBJECT_DETAIL_ID(String SUBJECT_DETAIL_ID) {
            this.SUBJECT_DETAIL_ID = SUBJECT_DETAIL_ID;
        }

        public String getAPPLICABLE_NUMBER() {
            return APPLICABLE_NUMBER;
        }

        public void setAPPLICABLE_NUMBER(String APPLICABLE_NUMBER) {
            this.APPLICABLE_NUMBER = APPLICABLE_NUMBER;
        }

        public String getSEMESTER_TYPE() {
            return SEMESTER_TYPE;
        }

        public void setSEMESTER_TYPE(String SEMESTER_TYPE) {
            this.SEMESTER_TYPE = SEMESTER_TYPE;
        }

        public String getASSIGN_SUBMISSION_ID() {
            return ASSIGN_SUBMISSION_ID;
        }

        public void setASSIGN_SUBMISSION_ID(String ASSIGN_SUBMISSION_ID) {
            this.ASSIGN_SUBMISSION_ID = ASSIGN_SUBMISSION_ID;
        }

        public String getSUBMISSION_DATE() {
            return SUBMISSION_DATE;
        }

        public void setSUBMISSION_DATE(String SUBMISSION_DATE) {
            this.SUBMISSION_DATE = SUBMISSION_DATE;
        }

        public String getACAD_ASG_FACUL_REMARK_ID() {
            return ACAD_ASG_FACUL_REMARK_ID;
        }

        public void setACAD_ASG_FACUL_REMARK_ID(String ACAD_ASG_FACUL_REMARK_ID) {
            this.ACAD_ASG_FACUL_REMARK_ID = ACAD_ASG_FACUL_REMARK_ID;
        }

        public String getFACULTY_REMARK() {
            return FACULTY_REMARK;
        }

        public void setFACULTY_REMARK(String FACULTY_REMARK) {
            this.FACULTY_REMARK = FACULTY_REMARK;
        }

        public String getCORRECTION_REMARK() {
            return CORRECTION_REMARK;
        }

        public void setCORRECTION_REMARK(String CORRECTION_REMARK) {
            this.CORRECTION_REMARK = CORRECTION_REMARK;
        }

        public String getASG_SUBMISSION_PATH() {
            return ASG_SUBMISSION_PATH;
        }

        public void setASG_SUBMISSION_PATH(String ASG_SUBMISSION_PATH) {
            this.ASG_SUBMISSION_PATH = ASG_SUBMISSION_PATH;
        }

        public String getTASK_FILE_NAME() {
            return TASK_FILE_NAME;
        }

        public void setTASK_FILE_NAME(String TASK_FILE_NAME) {
            this.TASK_FILE_NAME = TASK_FILE_NAME;
        }

        public String getSTU_SUBMISSION_STATUS() {
            return STU_SUBMISSION_STATUS;
        }

        public void setSTU_SUBMISSION_STATUS(String STU_SUBMISSION_STATUS) {
            this.STU_SUBMISSION_STATUS = STU_SUBMISSION_STATUS;
        }

        public String getGRADE_DETAIL_ID() {
            return GRADE_DETAIL_ID;
        }

        public void setGRADE_DETAIL_ID(String GRADE_DETAIL_ID) {
            this.GRADE_DETAIL_ID = GRADE_DETAIL_ID;
        }

        public String getGRADE_CODE() {
            return GRADE_CODE;
        }

        public void setGRADE_CODE(String GRADE_CODE) {
            this.GRADE_CODE = GRADE_CODE;
        }

        public String getOBTAINED_MARKS() {
            return OBTAINED_MARKS;
        }

        public void setOBTAINED_MARKS(String OBTAINED_MARKS) {
            this.OBTAINED_MARKS = OBTAINED_MARKS;
        }

        public String getEVALUATION_TYPE() {
            return EVALUATION_TYPE;
        }

        public void setEVALUATION_TYPE(String EVALUATION_TYPE) {
            this.EVALUATION_TYPE = EVALUATION_TYPE;
        }

        public String getMARKS_OUT_OF() {
            return MARKS_OUT_OF;
        }

        public void setMARKS_OUT_OF(String MARKS_OUT_OF) {
            this.MARKS_OUT_OF = MARKS_OUT_OF;
        }
    }
}
