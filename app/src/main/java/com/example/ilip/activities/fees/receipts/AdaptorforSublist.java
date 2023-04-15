package com.example.ilip.activities.fees.receipts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ilip.R;

import java.util.ArrayList;

public class AdaptorforSublist extends RecyclerView.Adapter<AdaptorforSublist.ViewHolderReceiptsDtl>{
    ArrayList<ModelforReceiptSublist> arrayList;
    Context context;

    public AdaptorforSublist(ArrayList<ModelforReceiptSublist> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderReceiptsDtl onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_sublist_receipt,parent,false);
        return new ViewHolderReceiptsDtl(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderReceiptsDtl holder, int position) {
    holder.SrNo.setText(String.valueOf(position+1));
    holder.receiptDtl.setText(arrayList.get(position).getFees_SubType());
    holder.receipt_Amount.setText(arrayList.get(position).getFees_Amt());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolderReceiptsDtl extends RecyclerView.ViewHolder{
        TextView SrNo,receiptDtl,receipt_Amount;
        public ViewHolderReceiptsDtl(@NonNull View itemView) {
            super(itemView);
            SrNo = itemView.findViewById(R.id.SrNos);
            receiptDtl = itemView.findViewById(R.id.receiptDtl);
            receipt_Amount = itemView.findViewById(R.id.receiptAmount);
        }
    }
}
