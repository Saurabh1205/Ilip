package com.example.ilip.common;


public  class ConstantAPIsClass {

   public static String WebControllerClientCode = "api/PMT/get_clientwise_licensed_details";
   public static String RegistrationAPI="api/Employee_Main_Login/Main_Login/";
   public static String ResendOTP = "api/Employee_Main_Login_Resend_Otp/Resend_Otp/";
   public String ProfileDetailAPI = "api/Student/GET_PROFILE";
   public String ProfileImageAPI = "api/Get_Photo_URL";
   public String DashboardMenuAPI= "api/Student/MenuList";
   public static String DashboardRefreshFirstAPICall = "api/Student/P_CALCULATE_STUDENT_ATT";
   public String StudentDetail = "api/Student/GetDetails";
   public String studentAttendanceStatus = "api/Student/P_GET_ATTENDANCE_STATUS";
   public String studentSyllabusStatus = "api/Student/P_GET_SUBJ_ATT_SYLBS_STATUS";
   public String day_WiseAPI = "api/Student/P_GET_DAYWISE_ATTENDANCE";
   public String calenderSpan = "api/Student/P_GET_ATT_START_END_DATE";
   public String sem_WiseAPI = "api/Student/P_STUDENT_RESULT_SEMWISE_ALL";

   public String SubjectEmployeeDetails = "api/Student/P_GET_EMPLOYEE_DTLS";
   public  String SubjectDetailsList = "api/Student/P_GET_SUBJ_ALL_ATT_DTLS";

   public String getMyCourseApiCall = "api/Student/P_GET_MY_COURSES";
   public String UniversitySyllabusApiCall = "api/Student/P_GET_UNIVERSITY_SYLLABUS";
   public String SubjectActionPlan = "api/Student/P_GET_SUBJECT_ACTION_PLAN";
   public String getScheduleDetails = "api/Student/P_GET_DAY_SCHEDULE";

   public String getNotifications = "api/GetNoticeHome/Notification_Count";
   public String getNotificationSummary = "api/GetNoticeHome/Notification_Summary";
   public static String getDataForOtherLinks = "api/GetNoticeHome/GetData";

   public static String getStudentNotes = "api/Student/GetStudNotesDtls";
   public static String getStudentAssignments = "api/Student/GetStudAssignment";
   public static String getLikeDisLike = "api/Student/Like_Dislike_IU";

   public static String getOnlineTransactionDtl = "api/Student/Get_Onl_Transaction_Dtls";
   public  String StudentFeeDtl = "api/Student/GetStudFeeDetails";
   public String getFeesReceipt = "api/Student/Get_Receipt_List";
   public String getStudentFeesReceipt = "api/Student/Get_Stu_Fee_Receipt";

}
