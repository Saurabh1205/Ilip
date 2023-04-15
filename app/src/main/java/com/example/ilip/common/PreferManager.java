package com.example.ilip.common;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferManager {
 SharedPreferences pref;
 SharedPreferences.Editor editor;
 Context context;
 // shared pref mode
 int PRIVATE_MODE = 0;
 // Shared preferences file name
 private static final String PREF_NAME = "USER-welcome";
 private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
 private static final String IS_FIRST_TIME_LOGIN = "IsFirstTimeLogin";
 private static final String USERNAME = "username";
 private static final String IS_GPIN_WHILE_FIRST_TIME_LOGIN = "IsGPinWhileFirstTimeLogin";
 private static final String TOKEN_ID = "tokenId";
 private static final String MAC_ID = "mac_id";
 private static final String PIN = "pin";
 private static final String USERID = "userId";

 private static final String LEDGERID = "ledgerId";
 private static final String MASTERID = "masterID";
 private static final String EMPLOYEEID = "employeeID";
 private static final String MobileNo = "mobileNo";
 private static final String AltMobileNo = "altmobileNo";

 private static final String EmailId = "emailId";
 private static final String UserType = "usertype";

 private static final String UserLogin_Type="userlogintype";

 private static final String JsonDataForAttendanceAPI="jsonDataForAttendanceAPI";
 private static final String JsonDataForSyllabusAPI="jsonDataForSyllabusAPI";

 public PreferManager(Context context) {
  this.context = context;
  pref = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
  editor = pref.edit();
 }
 public PreferManager(Context context,String str) {
  this.context = context;
  pref = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
  editor = pref.edit();
  editor.clear();
  editor.commit();
 }
 public String getJsonDataForAttendanceAPI() {
  return pref.getString(JsonDataForAttendanceAPI,"");
 }

 public void setJsonDataForAttendanceAPI(String jsonDataForAttendanceAPI) {
  editor.putString(JsonDataForAttendanceAPI, jsonDataForAttendanceAPI);
  editor.commit();
 }

 public String getJsonDataForSyllabusAPI() {
  return pref.getString(JsonDataForSyllabusAPI,"");
 }

 public void setJsonDataForSyllabusAPI(String jsonDataForSyllabusAPI) {
  editor.putString(JsonDataForSyllabusAPI , jsonDataForSyllabusAPI);
  editor.commit();
 }

 public void setUserType(String usertype) {
  editor.putString(UserType, usertype);
  editor.commit();
 }

 public String getUserType() {
  return pref.getString(UserType, "");
 }

 public void setUserLogin_Type(String userlogintype) {
  editor.putString(UserLogin_Type, userlogintype);
  editor.commit();
 }

 public String getUserLogin_Type() {
  return pref.getString(UserLogin_Type, "");
 }

 public void setEmailId(String emailId
 ) {
  editor.putString(EmailId, emailId);
  editor.commit();
 }

 public String getEmailId() {
  return pref.getString(EmailId, "");
 }

 public void setMobileNo(String mobileNo
 ) {
  editor.putString(MobileNo, mobileNo);
  editor.commit();
 }

 public String getMobileNo() {
  return pref.getString(MobileNo, "");
 }

 public void setAltMobileNo(String altMobileNo
 ) {
  editor.putString(AltMobileNo, altMobileNo);
  editor.commit();
 }

 public String getAltMobileNo() {
  return pref.getString(AltMobileNo, "");
 }


 public void setuserId(String userId
 ) {
  editor.putString(USERID, userId);
  editor.commit();
 }

 public String getuserId() {
  return pref.getString(USERID, "0");
 }


 public void setledgerId(String ledgerId
 ) {
  editor.putString(LEDGERID, ledgerId);
  editor.commit();
 }

 public String getledgerId() {
  return pref.getString(LEDGERID, "");
 }

 public void setmasterID(String masterID) {
  editor.putString(MASTERID, masterID);
  editor.commit();
 }

 public String getmasterID() {
  return pref.getString(MASTERID, "");
 }

 public void setEMPLOYEEID(String employeeID) {
  editor.putString(EMPLOYEEID, employeeID);
  editor.commit();
 }

 public String getEMPLOYEEID() {
  return pref.getString(EMPLOYEEID, "0");
 }

 public void setMacId(String macId
 ) {
  editor.putString(MAC_ID, macId);
  editor.commit();
 }

 public String getMAcId() {
  return pref.getString(MAC_ID, "");
 }

 public void setPin(String pin
 ) {
  editor.putString(PIN, pin);
  editor.commit();
 }

 public String getPin() {
  return pref.getString(PIN, "");
 }

 public void setTokenId(String tokenId
 ) {
  editor.putString(TOKEN_ID, tokenId);
  editor.commit();
 }

 public String getTokenId() {
  return pref.getString(TOKEN_ID, "");
 }

 public void setFirstTimeLaunch(boolean isFirstTime) {
  editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
  editor.commit();
 }

 public boolean isFirstTimeLaunch() {
  return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
 }

 public void setFirstTimeLogin(boolean isFirstTime) {
  editor.putBoolean(IS_FIRST_TIME_LOGIN, isFirstTime);
  editor.commit();
 }

 public boolean isFirstTimeLogin() {
  return pref.getBoolean(IS_FIRST_TIME_LOGIN, true);
 }

 public void setGPinWhileFirstTimeLogin(boolean isFirstTime) {
  editor.putBoolean(IS_GPIN_WHILE_FIRST_TIME_LOGIN, isFirstTime);
  editor.commit();
 }

 public boolean isGPinWhileFirstTimeLogin() {
  return pref.getBoolean(IS_GPIN_WHILE_FIRST_TIME_LOGIN, true);
 }

 public void setUsername(String username) {
  editor.putString(USERNAME, username);
  editor.commit();
 }

 public String getUsername() {
  return pref.getString(USERNAME, "");
 }

/////////////////User data////////////////////////
// --------------------------------------------------------------------------------------
private static final String ISLOGIN = "ISLOGIN";
 private static final String ISGENERATEPIN = "ISGENERATEPIN";
 private static final String ISREENTERPIN = "ISREENTERPIN";
 private static final String ISENTERPINSCREEN = "ISENTERPINSCREEN";
 private static final String ISFINGERPRINT = "ISFINGERPRINT";
 private static final String ISNOCONNECTION = "ISNOCONNECTION";
 private static final String ISMENU = "ISMENU";
 private static final String ISWELCOME = "ISWELCOME";
 private static final String ISCompleteLogin = "ISCompleteLogin";
 private static final String ISSECONDTIMEINSTALL = "isSecondTimeInstall";
 private static final String ISFORGOTPIN = "isForgotPin";

 public boolean isISSECONDTIMEINSTALL() {
  return pref.getBoolean(ISSECONDTIMEINSTALL, true);
 }

 public void setISSECONDTIMEINSTALL(boolean isSecondTimeInstall) {
  editor.putBoolean(ISSECONDTIMEINSTALL, isSecondTimeInstall);
  editor.commit();
 }

 public boolean isISFORGOTPIN() {
  return pref.getBoolean(ISFORGOTPIN, true);
 }

 public void setISFORGOTPIN(boolean ifForgotPin) {
  editor.putBoolean(ISFORGOTPIN, ifForgotPin);
  editor.commit();
 }

 public boolean isISCompleteLogin() {
  return pref.getBoolean(ISCompleteLogin, true);
 }

 public void setISCompleteLogin(boolean isFirstTime) {
  editor.putBoolean(ISCompleteLogin, isFirstTime);
  editor.commit();
 }

 public boolean isLogin() {
  return pref.getBoolean(ISLOGIN, true);
 }

 public void setLogin(boolean isFirstTime) {
  editor.putBoolean(ISLOGIN, isFirstTime);
  editor.commit();
 }

 public boolean isISGENERATEPIN() {
  return pref.getBoolean(ISGENERATEPIN, true);
 }

 public void setISGENERATEPIN(boolean isFirstTime) {
  editor.putBoolean(ISGENERATEPIN, isFirstTime);
  editor.commit();
 }

 public boolean isISENTERPINSCREEN() {
  return pref.getBoolean(ISENTERPINSCREEN, true);
 }

 public void setISENTERPINSCREEN(boolean isFirstTime) {
  editor.putBoolean(ISENTERPINSCREEN, isFirstTime);
  editor.commit();
 }

 public boolean isISFINGERPRINT() {
  return pref.getBoolean(ISFINGERPRINT, true);
 }

 public void setISFINGERPRINT(boolean isFirstTime) {
  editor.putBoolean(ISFINGERPRINT, isFirstTime);
  editor.commit();
 }

 public boolean isISNOCONNECTION() {
  return pref.getBoolean(ISNOCONNECTION, true);
 }

 public void setISNOCONNECTION(boolean isFirstTime) {
  editor.putBoolean(ISNOCONNECTION, isFirstTime);
  editor.commit();
 }

 public boolean isISMENU() {
  return pref.getBoolean(ISMENU, true);
 }

 public void setISMENU(boolean isFirstTime) {
  editor.putBoolean(ISMENU, isFirstTime);
  editor.commit();
 }

 public boolean isISREENTERPIN() {
  return pref.getBoolean(ISREENTERPIN, true);
 }

 public void setISREENTERPIN(boolean isFirstTime) {
  editor.putBoolean(ISREENTERPIN, isFirstTime);
  editor.commit();
 }

 public boolean isISWELCOME() {
  return pref.getBoolean(ISWELCOME, true);
 }

 public void setISWELCOME(boolean isWelcome) {
  editor.putBoolean(ISWELCOME, isWelcome);
  editor.commit();
 }

}
