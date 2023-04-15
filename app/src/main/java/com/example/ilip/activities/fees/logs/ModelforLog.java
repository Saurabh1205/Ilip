package com.example.ilip.activities.fees.logs;

public class ModelforLog {
    String Sr_no, Transaction_no, Order_no,Bank_Reference_No,Transaction_Date,Transaction_Time, status, Amount, Receipt_no;
    int ImagePath;

    public int getImagePath() {
        return ImagePath;
    }

    public void setImagePath(int imagePath) {
        ImagePath = imagePath;
    }

    public String getSr_no() {
        return Sr_no;
    }

    public void setSr_no(String sr_no) {
        Sr_no = sr_no;
    }

    public String getTransaction_no() {
        return Transaction_no;
    }

    public void setTransaction_no(String transaction_no) {
        Transaction_no = transaction_no;
    }

    public String getOrder_no() {
        return Order_no;
    }

    public void setOrder_no(String order_no) {
        Order_no = order_no;
    }

    public String getBank_Reference_No() {
        return Bank_Reference_No;
    }

    public void setBank_Reference_No(String bank_Reference_No) {
        Bank_Reference_No = bank_Reference_No;
    }

    public String getTransaction_Date() {
        return Transaction_Date;
    }

    public void setTransaction_Date(String transaction_Date) {
        Transaction_Date = transaction_Date;
    }

    public String getTransaction_Time() {
        return Transaction_Time;
    }

    public void setTransaction_Time(String transaction_Time) {
        Transaction_Time = transaction_Time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAmount() {
        return Amount;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public String getReceipt_no() {
        return Receipt_no;
    }

    public void setReceipt_no(String receipt_no) {
        Receipt_no = receipt_no;
    }

    @Override
    public String toString() {
        return "ModelforLog{" +
                "Sr_no='" + Sr_no + '\'' +
                ", Transaction_no='" + Transaction_no + '\'' +
                ", Order_no='" + Order_no + '\'' +
                ", Bank_Reference_No='" + Bank_Reference_No + '\'' +
                ", Transaction_Date='" + Transaction_Date + '\'' +
                ", Transaction_Time='" + Transaction_Time + '\'' +
                ", status='" + status + '\'' +
                ", Amount='" + Amount + '\'' +
                ", Receipt_no='" + Receipt_no + '\'' +
                ", ImagePath=" + ImagePath +
                '}';
    }
}
