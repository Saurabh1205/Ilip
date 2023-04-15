package com.example.ilip.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    Pattern pattern;
    Matcher matcher;
    String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\."+
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";
    String noPattern = "\\d+";
//    String gst = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$";
//    String Adharcard = "^[2-9]{1}[0-9]{3}\\\\s[0-9]{4}\\\\s[0-9]{4}$";
    String drivinglicense = "^(([A-Z]{2}[0-9]{2})( )|([A-Z]{2}-[0-9]{2}))((19|20)[0-9][0-9])[0-9]{7}$";
    String passportnum = "^[A-PR-WYa-pr-wy][1-9]\\\\d\\\\s?\\\\d{4}[1-9]$";
//    String vaoteridval = "/^([a-zA-Z]){3}([0-9]){7}?$/g";
//    String panPattern = "[A-Z]{5}[0-9]{4}[A-Z]{1}";
    String chassis_engine="[^\\w\\d]*(([0-9]+.*[A-Za-z]+.*)|[A-Za-z]+.*([0-9]+.*))";
//    String PinCode="^[1-9]{1}[0-9]{2}\\s?[0-9]{3}$";
    String fullname = "^[\\p{L} .'-]+$";
    String passwordpattern ="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)"+"(?=.*[-+_!@#$%^&*., ?]).+$";
    //  boolean isWhitespace = s.matches("^\\s*$");
    String vehical_no="^[A-Z]{2}[ -][0-9]{1,2}(?: [A-Z])?(?: [A-Z]*)? [0-9]{4}$";

    String vehicalno2= "^[A-Z]{2}[0-9]{2}[A-Z]{1,2}[0-9]{4}$";
//    public boolean validatePinCode(final String hex){
//        pattern = Pattern.compile(PinCode);
//        matcher = pattern.matcher(hex);
//        boolean str = matcher.matches();
//        Log.e("",""+str);
//        return str;
//    }
    public boolean validateEmail(final String hex) {
        pattern = Pattern.compile(emailPattern);
        matcher = pattern.matcher(hex);
        return matcher.matches();

    }
    public boolean validateChassis_Engin(final String hex) {
        pattern = Pattern.compile(chassis_engine);
        matcher = pattern.matcher(hex);
        return matcher.matches();

    }
    public boolean Validationfor_VehicalNo(final String hex){
        pattern = Pattern.compile(vehical_no);
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }
    public boolean Validationfor_VehicalNo2(final String hex){
        pattern = Pattern.compile(vehicalno2);
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }
//    public boolean validateadhar(final String hex){
//        pattern = Pattern.compile(Adharcard);
//        matcher = pattern.matcher(hex);
//        return matcher.matches();
//    }
    public boolean validdrivinglicense(final String hex){
        pattern = Pattern.compile(drivinglicense);
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }

//    public boolean validateGstnum(final String hex){
//        pattern = Pattern.compile(gst);
//        matcher = pattern.matcher(hex);
//        return matcher.matches();
//    }
    public boolean validatepassportnum(final String hex){
        pattern = Pattern.compile(passportnum);
        matcher = pattern.matcher(hex);
        return matcher.matches();
    }

//    public boolean validvoterIDnum(final String hex){
//        pattern = Pattern.compile(vaoteridval);
//        matcher = pattern.matcher(hex);
//        return matcher.matches();
//    }
    public boolean ValidNumber(final String hex) {
        pattern = Pattern.compile(noPattern);
        matcher = pattern.matcher(hex);
        boolean str = matcher.matches();
        return str;

    }

    public boolean ValidFullName(final String hex) {
        pattern = Pattern.compile(fullname);
        matcher = pattern.matcher(hex);
        boolean str = matcher.matches();
        return str;

    }
    public boolean ValidMobileNo(final String phone) {

        //                Toast.makeText(this, "Invalid--", Toast.LENGTH_SHORT).show();
        return phone.startsWith("6") || phone.startsWith("7") || phone.startsWith("8") || phone.startsWith("9");
    }

    public boolean ValidMobileNo1(final String phone) {

        //                Toast.makeText(this, "Invalid--", Toast.LENGTH_SHORT).show();
        return phone.startsWith("6") || phone.startsWith("7") || phone.startsWith("8") || phone.startsWith("9");
    }

    public static boolean isValidMobileNo(String str)
    {
//(0/91): number starts with (0/91)
//[7-9]: starting of the number may contain a digit between 0 to 9
//[0-9]: then contains digits 0 to 9
        Pattern ptrn = Pattern.compile("(0/91)?[6-9][0-9]{9}");
//the matcher() method creates a matcher that will match the given input against this pattern
        Matcher match = ptrn.matcher(str);
//returns a boolean value
        return (match.find() && match.group().equals(str));
    }

//    public boolean validatePanNo(final String panNo) {
//        pattern = Pattern.compile(panPattern);
//        matcher = pattern.matcher(panNo);
//        boolean str = matcher.matches();
//        return str;
//    }
    public boolean Passwordpattern(final String password){
        pattern=Pattern.compile(passwordpattern);
        matcher = pattern.matcher(password);
        boolean str = matcher.matches();
        return str;
    }
}
