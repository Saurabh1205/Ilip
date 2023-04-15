package com.example.ilip.activities.fees.receipts;

import org.json.JSONArray;


public class ModelclassforReceipts {

    String receiptNo,Receipt_Date,SrNo,Amount;



    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getReceipt_Date() {
        return Receipt_Date;
    }

    public void setReceipt_Date(String receipt_Date) {
        Receipt_Date = receipt_Date;
    }

    public String getSrNo() {
        return SrNo;
    }

    public void setSrNo(String srNo) {
        SrNo = srNo;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    int status;
    String STATUS;
   // List<ReceiptDtlBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

//    public List<ReceiptDtlBean> getData() {
//        return data;
//    }
//
//    public void setData(List<ReceiptDtlBean> data) {
//        this.data = data;
//    }


   // public  class  ReceiptDtlBean {
    String RECEIPT_NO,RCPT_NO,RECEIPT_DATE,FEE_CH_DD_NO,FEE_CH_DD_DATE,BANK_NAME,
            BRANCH_NAME,
            NARRATION,
            PERSON_TYPES,
            PERSON_ID,
            PERSON_CODE,
            SESSION_ID,
            FEE_TYPE_ID,
            FEE_TYPE_DESC,
            FEE_SUB_TYPE_ID,
            FEE_SUB_TYPE_DESC,
            AMT,
            RECEIPT_BY_CASH,
            RECEIPT_BY_BANK,
            RECEIPT_BY_ADJ,
            PAYABLE_AMOUNT,
            TOTAL_AMOUNT,
            TOTAL_WORDS,
            PERSON_NAME,
            PERSON_NAME1,
            BRANCH_STD_SHRT_DESC,
            BRANCH_STD_DESC,
            STANDARD_DESCRIPTION,
            STANDARD_CODE,
            BALANCE_AMOUNT,
            TRANSACTION_TYPE,
            ACCOUNT_NAME,
            EMP,
            ADMCAT,
            STUCAT,
            ROLL_NO,
            ENROLL,
            SEAT_TYPE,
            INSERT_USER_ID,
            BS_MST_ID,
            CENTRE_CODE,
            CENTRE_NAME,
            REPORT_HEADING,
            CENTRE_ADDRESS,
            CELL_PHONE,
            PHONE_NUMBER,
            YEARLY_ROLL_NUMBER,
            BRANCH_DEPT,
            ACADEMIC_YEAR,
            SECTION_DESCRIPTION,
            BRANCH_DESCRIPTION,
            BATCH,
            CHQ_D,
            CASHIER_NAME,
            REMARK,
            CANCELLED,
            FEE_RECEIVABLE_ID,
            PAYEE_BANK_NAME,
            PAYEE_BANK_ACCT,
            PROMOTIONAL_CONCESSION,
            ONE_TIME_CONCESSION,
            RCVBLAMT_STUD,
            CURRENCY_NAME,
            CURRENCY_SYMBOL,
            CNT_PAYEE,
            ONL_ORDER_ID,
            ONL_PG_REF_NO,
            ONL_DATE_AND_TIME,
            FEE_RECEIPT_CONC_REMARK,
            HOSTEL_NAME,
            SAC_CODE;
        JSONArray Sub_Type_List;

    public String getRECEIPT_NO() {
        return RECEIPT_NO;
    }

    public void setRECEIPT_NO(String RECEIPT_NO) {
        this.RECEIPT_NO = RECEIPT_NO;
    }

    public String getRCPT_NO() {
        return RCPT_NO;
    }

    public void setRCPT_NO(String RCPT_NO) {
        this.RCPT_NO = RCPT_NO;
    }

    public String getRECEIPT_DATE() {
        return RECEIPT_DATE;
    }

    public void setRECEIPT_DATE(String RECEIPT_DATE) {
        this.RECEIPT_DATE = RECEIPT_DATE;
    }

    public String getFEE_CH_DD_NO() {
        return FEE_CH_DD_NO;
    }

    public void setFEE_CH_DD_NO(String FEE_CH_DD_NO) {
        this.FEE_CH_DD_NO = FEE_CH_DD_NO;
    }

    public String getFEE_CH_DD_DATE() {
        return FEE_CH_DD_DATE;
    }

    public void setFEE_CH_DD_DATE(String FEE_CH_DD_DATE) {
        this.FEE_CH_DD_DATE = FEE_CH_DD_DATE;
    }

    public String getBANK_NAME() {
        return BANK_NAME;
    }

    public void setBANK_NAME(String BANK_NAME) {
        this.BANK_NAME = BANK_NAME;
    }

    public String getBRANCH_NAME() {
        return BRANCH_NAME;
    }

    public void setBRANCH_NAME(String BRANCH_NAME) {
        this.BRANCH_NAME = BRANCH_NAME;
    }

    public String getNARRATION() {
        return NARRATION;
    }

    public void setNARRATION(String NARRATION) {
        this.NARRATION = NARRATION;
    }

    public String getPERSON_TYPES() {
        return PERSON_TYPES;
    }

    public void setPERSON_TYPES(String PERSON_TYPES) {
        this.PERSON_TYPES = PERSON_TYPES;
    }

    public String getPERSON_ID() {
        return PERSON_ID;
    }

    public void setPERSON_ID(String PERSON_ID) {
        this.PERSON_ID = PERSON_ID;
    }

    public String getPERSON_CODE() {
        return PERSON_CODE;
    }

    public void setPERSON_CODE(String PERSON_CODE) {
        this.PERSON_CODE = PERSON_CODE;
    }

    public String getSESSION_ID() {
        return SESSION_ID;
    }

    public void setSESSION_ID(String SESSION_ID) {
        this.SESSION_ID = SESSION_ID;
    }

    public String getFEE_TYPE_ID() {
        return FEE_TYPE_ID;
    }

    public void setFEE_TYPE_ID(String FEE_TYPE_ID) {
        this.FEE_TYPE_ID = FEE_TYPE_ID;
    }

    public String getFEE_TYPE_DESC() {
        return FEE_TYPE_DESC;
    }

    public void setFEE_TYPE_DESC(String FEE_TYPE_DESC) {
        this.FEE_TYPE_DESC = FEE_TYPE_DESC;
    }

    public String getFEE_SUB_TYPE_ID() {
        return FEE_SUB_TYPE_ID;
    }

    public void setFEE_SUB_TYPE_ID(String FEE_SUB_TYPE_ID) {
        this.FEE_SUB_TYPE_ID = FEE_SUB_TYPE_ID;
    }

    public String getFEE_SUB_TYPE_DESC() {
        return FEE_SUB_TYPE_DESC;
    }

    public void setFEE_SUB_TYPE_DESC(String FEE_SUB_TYPE_DESC) {
        this.FEE_SUB_TYPE_DESC = FEE_SUB_TYPE_DESC;
    }

        public String getAMT() {
            return AMT;
        }

        public void setAMT(String AMT) {
            this.AMT = AMT;
        }

        public String getRECEIPT_BY_CASH() {
            return RECEIPT_BY_CASH;
        }

        public void setRECEIPT_BY_CASH(String RECEIPT_BY_CASH) {
            this.RECEIPT_BY_CASH = RECEIPT_BY_CASH;
        }

        public String getRECEIPT_BY_BANK() {
            return RECEIPT_BY_BANK;
        }

        public void setRECEIPT_BY_BANK(String RECEIPT_BY_BANK) {
            this.RECEIPT_BY_BANK = RECEIPT_BY_BANK;
        }

        public String getRECEIPT_BY_ADJ() {
            return RECEIPT_BY_ADJ;
        }

        public void setRECEIPT_BY_ADJ(String RECEIPT_BY_ADJ) {
            this.RECEIPT_BY_ADJ = RECEIPT_BY_ADJ;
        }

        public String getPAYABLE_AMOUNT() {
            return PAYABLE_AMOUNT;
        }

        public void setPAYABLE_AMOUNT(String PAYABLE_AMOUNT) {
            this.PAYABLE_AMOUNT = PAYABLE_AMOUNT;
        }

        public String getTOTAL_AMOUNT() {
            return TOTAL_AMOUNT;
        }

        public void setTOTAL_AMOUNT(String TOTAL_AMOUNT) {
            this.TOTAL_AMOUNT = TOTAL_AMOUNT;
        }

        public String getTOTAL_WORDS() {
        return TOTAL_WORDS;
    }

    public void setTOTAL_WORDS(String TOTAL_WORDS) {
        this.TOTAL_WORDS = TOTAL_WORDS;
    }

    public String getPERSON_NAME() {
        return PERSON_NAME;
    }

    public void setPERSON_NAME(String PERSON_NAME) {
        this.PERSON_NAME = PERSON_NAME;
    }

    public String getPERSON_NAME1() {
        return PERSON_NAME1;
    }

    public void setPERSON_NAME1(String PERSON_NAME1) {
        this.PERSON_NAME1 = PERSON_NAME1;
    }

    public String getBRANCH_STD_SHRT_DESC() {
        return BRANCH_STD_SHRT_DESC;
    }

    public void setBRANCH_STD_SHRT_DESC(String BRANCH_STD_SHRT_DESC) {
        this.BRANCH_STD_SHRT_DESC = BRANCH_STD_SHRT_DESC;
    }

    public String getBRANCH_STD_DESC() {
        return BRANCH_STD_DESC;
    }

    public void setBRANCH_STD_DESC(String BRANCH_STD_DESC) {
        this.BRANCH_STD_DESC = BRANCH_STD_DESC;
    }

    public String getSTANDARD_DESCRIPTION() {
        return STANDARD_DESCRIPTION;
    }

    public void setSTANDARD_DESCRIPTION(String STANDARD_DESCRIPTION) {
        this.STANDARD_DESCRIPTION = STANDARD_DESCRIPTION;
    }

    public String getSTANDARD_CODE() {
        return STANDARD_CODE;
    }

    public void setSTANDARD_CODE(String STANDARD_CODE) {
        this.STANDARD_CODE = STANDARD_CODE;
    }

    public String getBALANCE_AMOUNT() {
        return BALANCE_AMOUNT;
    }

    public void setBALANCE_AMOUNT(String BALANCE_AMOUNT) {
        this.BALANCE_AMOUNT = BALANCE_AMOUNT;
    }

    public String getTRANSACTION_TYPE() {
        return TRANSACTION_TYPE;
    }

    public void setTRANSACTION_TYPE(String TRANSACTION_TYPE) {
        this.TRANSACTION_TYPE = TRANSACTION_TYPE;
    }

    public String getACCOUNT_NAME() {
        return ACCOUNT_NAME;
    }

    public void setACCOUNT_NAME(String ACCOUNT_NAME) {
        this.ACCOUNT_NAME = ACCOUNT_NAME;
    }

    public String getEMP() {
        return EMP;
    }

    public void setEMP(String EMP) {
        this.EMP = EMP;
    }

    public String getADMCAT() {
        return ADMCAT;
    }

    public void setADMCAT(String ADMCAT) {
        this.ADMCAT = ADMCAT;
    }

    public String getSTUCAT() {
        return STUCAT;
    }

    public void setSTUCAT(String STUCAT) {
        this.STUCAT = STUCAT;
    }

    public String getROLL_NO() {
        return ROLL_NO;
    }

    public void setROLL_NO(String ROLL_NO) {
        this.ROLL_NO = ROLL_NO;
    }

    public String getENROLL() {
        return ENROLL;
    }

    public void setENROLL(String ENROLL) {
        this.ENROLL = ENROLL;
    }

    public String getSEAT_TYPE() {
        return SEAT_TYPE;
    }

    public void setSEAT_TYPE(String SEAT_TYPE) {
        this.SEAT_TYPE = SEAT_TYPE;
    }

    public String getINSERT_USER_ID() {
        return INSERT_USER_ID;
    }

    public void setINSERT_USER_ID(String INSERT_USER_ID) {
        this.INSERT_USER_ID = INSERT_USER_ID;
    }

    public String getBS_MST_ID() {
        return BS_MST_ID;
    }

    public void setBS_MST_ID(String BS_MST_ID) {
        this.BS_MST_ID = BS_MST_ID;
    }

    public String getCENTRE_CODE() {
        return CENTRE_CODE;
    }

    public void setCENTRE_CODE(String CENTRE_CODE) {
        this.CENTRE_CODE = CENTRE_CODE;
    }

    public String getCENTRE_NAME() {
        return CENTRE_NAME;
    }

    public void setCENTRE_NAME(String CENTRE_NAME) {
        this.CENTRE_NAME = CENTRE_NAME;
    }

    public String getREPORT_HEADING() {
        return REPORT_HEADING;
    }

    public void setREPORT_HEADING(String REPORT_HEADING) {
        this.REPORT_HEADING = REPORT_HEADING;
    }

    public String getCENTRE_ADDRESS() {
        return CENTRE_ADDRESS;
    }

    public void setCENTRE_ADDRESS(String CENTRE_ADDRESS) {
        this.CENTRE_ADDRESS = CENTRE_ADDRESS;
    }

    public String getCELL_PHONE() {
        return CELL_PHONE;
    }

    public void setCELL_PHONE(String CELL_PHONE) {
        this.CELL_PHONE = CELL_PHONE;
    }

    public String getPHONE_NUMBER() {
        return PHONE_NUMBER;
    }

    public void setPHONE_NUMBER(String PHONE_NUMBER) {
        this.PHONE_NUMBER = PHONE_NUMBER;
    }

    public String getYEARLY_ROLL_NUMBER() {
        return YEARLY_ROLL_NUMBER;
    }

    public void setYEARLY_ROLL_NUMBER(String YEARLY_ROLL_NUMBER) {
        this.YEARLY_ROLL_NUMBER = YEARLY_ROLL_NUMBER;
    }

    public String getBRANCH_DEPT() {
        return BRANCH_DEPT;
    }

    public void setBRANCH_DEPT(String BRANCH_DEPT) {
        this.BRANCH_DEPT = BRANCH_DEPT;
    }

    public String getACADEMIC_YEAR() {
        return ACADEMIC_YEAR;
    }

    public void setACADEMIC_YEAR(String ACADEMIC_YEAR) {
        this.ACADEMIC_YEAR = ACADEMIC_YEAR;
    }

    public String getSECTION_DESCRIPTION() {
        return SECTION_DESCRIPTION;
    }

    public void setSECTION_DESCRIPTION(String SECTION_DESCRIPTION) {
        this.SECTION_DESCRIPTION = SECTION_DESCRIPTION;
    }

    public String getBRANCH_DESCRIPTION() {
        return BRANCH_DESCRIPTION;
    }

    public void setBRANCH_DESCRIPTION(String BRANCH_DESCRIPTION) {
        this.BRANCH_DESCRIPTION = BRANCH_DESCRIPTION;
    }

    public String getBATCH() {
        return BATCH;
    }

    public void setBATCH(String BATCH) {
        this.BATCH = BATCH;
    }

    public String getCHQ_D() {
        return CHQ_D;
    }

    public void setCHQ_D(String CHQ_D) {
        this.CHQ_D = CHQ_D;
    }

    public String getCASHIER_NAME() {
        return CASHIER_NAME;
    }

    public void setCASHIER_NAME(String CASHIER_NAME) {
        this.CASHIER_NAME = CASHIER_NAME;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public String getCANCELLED() {
        return CANCELLED;
    }

    public void setCANCELLED(String CANCELLED) {
        this.CANCELLED = CANCELLED;
    }

    public String getFEE_RECEIVABLE_ID() {
        return FEE_RECEIVABLE_ID;
    }

    public void setFEE_RECEIVABLE_ID(String FEE_RECEIVABLE_ID) {
        this.FEE_RECEIVABLE_ID = FEE_RECEIVABLE_ID;
    }

    public String getPAYEE_BANK_NAME() {
        return PAYEE_BANK_NAME;
    }

    public void setPAYEE_BANK_NAME(String PAYEE_BANK_NAME) {
        this.PAYEE_BANK_NAME = PAYEE_BANK_NAME;
    }

    public String getPAYEE_BANK_ACCT() {
        return PAYEE_BANK_ACCT;
    }

    public void setPAYEE_BANK_ACCT(String PAYEE_BANK_ACCT) {
        this.PAYEE_BANK_ACCT = PAYEE_BANK_ACCT;
    }

    public String getPROMOTIONAL_CONCESSION() {
        return PROMOTIONAL_CONCESSION;
    }

    public void setPROMOTIONAL_CONCESSION(String PROMOTIONAL_CONCESSION) {
        this.PROMOTIONAL_CONCESSION = PROMOTIONAL_CONCESSION;
    }

    public String getONE_TIME_CONCESSION() {
        return ONE_TIME_CONCESSION;
    }

    public void setONE_TIME_CONCESSION(String ONE_TIME_CONCESSION) {
        this.ONE_TIME_CONCESSION = ONE_TIME_CONCESSION;
    }

    public String getRCVBLAMT_STUD() {
        return RCVBLAMT_STUD;
    }

    public void setRCVBLAMT_STUD(String RCVBLAMT_STUD) {
        this.RCVBLAMT_STUD = RCVBLAMT_STUD;
    }

    public String getCURRENCY_NAME() {
        return CURRENCY_NAME;
    }

    public void setCURRENCY_NAME(String CURRENCY_NAME) {
        this.CURRENCY_NAME = CURRENCY_NAME;
    }

    public String getCURRENCY_SYMBOL() {
        return CURRENCY_SYMBOL;
    }

    public void setCURRENCY_SYMBOL(String CURRENCY_SYMBOL) {
        this.CURRENCY_SYMBOL = CURRENCY_SYMBOL;
    }

    public String getCNT_PAYEE() {
        return CNT_PAYEE;
    }

    public void setCNT_PAYEE(String CNT_PAYEE) {
        this.CNT_PAYEE = CNT_PAYEE;
    }

    public String getONL_ORDER_ID() {
        return ONL_ORDER_ID;
    }

    public void setONL_ORDER_ID(String ONL_ORDER_ID) {
        this.ONL_ORDER_ID = ONL_ORDER_ID;
    }

    public String getONL_PG_REF_NO() {
        return ONL_PG_REF_NO;
    }

    public void setONL_PG_REF_NO(String ONL_PG_REF_NO) {
        this.ONL_PG_REF_NO = ONL_PG_REF_NO;
    }

    public String getONL_DATE_AND_TIME() {
        return ONL_DATE_AND_TIME;
    }

    public void setONL_DATE_AND_TIME(String ONL_DATE_AND_TIME) {
        this.ONL_DATE_AND_TIME = ONL_DATE_AND_TIME;
    }

    public String getFEE_RECEIPT_CONC_REMARK() {
        return FEE_RECEIPT_CONC_REMARK;
    }

    public void setFEE_RECEIPT_CONC_REMARK(String FEE_RECEIPT_CONC_REMARK) {
        this.FEE_RECEIPT_CONC_REMARK = FEE_RECEIPT_CONC_REMARK;
    }

    public String getHOSTEL_NAME() {
        return HOSTEL_NAME;
    }

    public void setHOSTEL_NAME(String HOSTEL_NAME) {
        this.HOSTEL_NAME = HOSTEL_NAME;
    }

    public String getSAC_CODE() {
        return SAC_CODE;
    }

    public void setSAC_CODE(String SAC_CODE) {
        this.SAC_CODE = SAC_CODE;
    }

        public JSONArray getSub_Type_List() {
            return Sub_Type_List;
        }

        public void setSub_Type_List(JSONArray sub_Type_List) {
            Sub_Type_List = sub_Type_List;
        }
    //}

}
