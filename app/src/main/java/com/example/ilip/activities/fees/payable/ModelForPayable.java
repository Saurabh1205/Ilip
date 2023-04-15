package com.example.ilip.activities.fees.payable;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class ModelForPayable {
    String Fee_head,Fee_Type,Fee_value,ScreenName;
    String RowData;
    JSONArray jsonArrayStructural,jsonArrayNonStructural,jsonArrayExamination;
    List<String> arrayList;
    String FEE_INSTALLMENT_NO,INS_RECEIVABLE_TOTAL,INS_RECEIVED_TOTAL,INS_BALANCE_TOTAL,INS_CONSESSION_TOTAL;

    ArrayList<String> FEE_TYPE_DESC,RECEIVABLE_AMOUNT,FEE_RECEIPT_AMOUNT,DISCOUNT_AMOUNT,BALANCE_AMOUNT;

    public String getRowData() {
        return RowData;
    }

    public void setRowData(String rowData) {
        RowData = rowData;
    }

    public String getFee_head() {
        return Fee_head;
    }

    public void setFee_head(String fee_head) {
        Fee_head = fee_head;
    }

    public String getFee_Type() {
        return Fee_Type;
    }

    public void setFee_Type(String fee_Type) {
        Fee_Type = fee_Type;
    }

    public String getFee_value() {
        return Fee_value;
    }

    public void setFee_value(String fee_value) {
        Fee_value = fee_value;
    }

    public String getScreenName() {
        return ScreenName;
    }

    public void setScreenName(String screenName) {
        ScreenName = screenName;
    }

    public JSONArray getJsonArrayStructural() {
        return jsonArrayStructural;
    }

    public void setJsonArrayStructural(JSONArray jsonArrayStructural) {
        this.jsonArrayStructural = jsonArrayStructural;
    }

    public JSONArray getJsonArrayNonStructural() {
        return jsonArrayNonStructural;
    }

    public void setJsonArrayNonStructural(JSONArray jsonArrayNonStructural) {
        this.jsonArrayNonStructural = jsonArrayNonStructural;
    }

    public JSONArray getJsonArrayExamination() {
        return jsonArrayExamination;
    }

    public void setJsonArrayExamination(JSONArray jsonArrayExamination) {
        this.jsonArrayExamination = jsonArrayExamination;
    }

    public String getFEE_INSTALLMENT_NO() {
        return FEE_INSTALLMENT_NO;
    }

    public void setFEE_INSTALLMENT_NO(String FEE_INSTALLMENT_NO) {
        this.FEE_INSTALLMENT_NO = FEE_INSTALLMENT_NO;
    }

    public String getINS_RECEIVABLE_TOTAL() {
        return INS_RECEIVABLE_TOTAL;
    }

    public void setINS_RECEIVABLE_TOTAL(String INS_RECEIVABLE_TOTAL) {
        this.INS_RECEIVABLE_TOTAL = INS_RECEIVABLE_TOTAL;
    }

    public String getINS_RECEIVED_TOTAL() {
        return INS_RECEIVED_TOTAL;
    }

    public void setINS_RECEIVED_TOTAL(String INS_RECEIVED_TOTAL) {
        this.INS_RECEIVED_TOTAL = INS_RECEIVED_TOTAL;
    }

    public String getINS_BALANCE_TOTAL() {
        return INS_BALANCE_TOTAL;
    }

    public void setINS_BALANCE_TOTAL(String INS_BALANCE_TOTAL) {
        this.INS_BALANCE_TOTAL = INS_BALANCE_TOTAL;
    }

    public String getINS_CONSESSION_TOTAL() {
        return INS_CONSESSION_TOTAL;
    }

    public void setINS_CONSESSION_TOTAL(String INS_CONSESSION_TOTAL) {
        this.INS_CONSESSION_TOTAL = INS_CONSESSION_TOTAL;
    }

    public List<String> getArrayList() {
        return arrayList;
    }

    public void setArrayList(List<String> arrayList) {
        this.arrayList = arrayList;
    }

    public ArrayList<String> getFEE_TYPE_DESC() {
        return FEE_TYPE_DESC;
    }

    public void setFEE_TYPE_DESC(ArrayList<String> FEE_TYPE_DESC) {
        this.FEE_TYPE_DESC = FEE_TYPE_DESC;
    }

    public ArrayList<String> getRECEIVABLE_AMOUNT() {
        return RECEIVABLE_AMOUNT;
    }

    public void setRECEIVABLE_AMOUNT(ArrayList<String> RECEIVABLE_AMOUNT) {
        this.RECEIVABLE_AMOUNT = RECEIVABLE_AMOUNT;
    }

    public ArrayList<String> getFEE_RECEIPT_AMOUNT() {
        return FEE_RECEIPT_AMOUNT;
    }

    public void setFEE_RECEIPT_AMOUNT(ArrayList<String> FEE_RECEIPT_AMOUNT) {
        this.FEE_RECEIPT_AMOUNT = FEE_RECEIPT_AMOUNT;
    }

    public ArrayList<String> getDISCOUNT_AMOUNT() {
        return DISCOUNT_AMOUNT;
    }

    public void setDISCOUNT_AMOUNT(ArrayList<String> DISCOUNT_AMOUNT) {
        this.DISCOUNT_AMOUNT = DISCOUNT_AMOUNT;
    }

    public ArrayList<String> getBALANCE_AMOUNT() {
        return BALANCE_AMOUNT;
    }

    public void setBALANCE_AMOUNT(ArrayList<String> BALANCE_AMOUNT) {
        this.BALANCE_AMOUNT = BALANCE_AMOUNT;
    }
}
