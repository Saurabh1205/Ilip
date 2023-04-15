package com.example.ilip.activities.fees.receipts;

public class ModelforReceiptSublist {

    String SrNo,Fees_SubType,Fees_Amt,payMode,payDtl;

    public String getSrNo() {
        return SrNo;
    }

    public void setSrNo(String srNo) {
        SrNo = srNo;
    }

    public String getFees_SubType() {
        return Fees_SubType;
    }

    public void setFees_SubType(String fees_SubType) {
        Fees_SubType = fees_SubType;
    }

    public String getFees_Amt() {
        return Fees_Amt;
    }

    public void setFees_Amt(String fees_Amt) {
        Fees_Amt = fees_Amt;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public String getPayDtl() {
        return payDtl;
    }

    public void setPayDtl(String payDtl) {
        this.payDtl = payDtl;
    }
}
