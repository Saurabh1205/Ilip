package com.example.ilip.activities.fees.logs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ilip.R;

import java.util.ArrayList;

public class AbstractForLog extends RecyclerView.Adapter<AbstractForLog.ViewHolderClass> {
    ArrayList<ModelforLog> arrayList;
    Context context;

    public AbstractForLog(ArrayList<ModelforLog> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_log_history,parent,false);
        return new ViewHolderClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderClass holder, int position) {
    holder.Transaction_no.setText(arrayList.get(position).getTransaction_no());
    holder.Transaction_Date.setText(arrayList.get(position).getTransaction_Date());
    holder.Transaction_Time.setText(arrayList.get(position).getTransaction_Time());
    holder.Order_no.setText(arrayList.get(position).getOrder_no());
    holder.status.setImageResource(arrayList.get(position).getImagePath());
    holder.Bank_Ref_no.setText(arrayList.get(position).getBank_Reference_No());
    holder.Amount.setText(arrayList.get(position).getAmount());

        if(!arrayList.get(position).getReceipt_no().equals("")) {
            holder.Receipt_no.setVisibility(View.VISIBLE);
            holder.Receipt_no.setText(arrayList.get(position).getReceipt_no());
        }else{
            holder.Receipt_no.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolderClass extends RecyclerView.ViewHolder {
        TextView Bank_Ref_no, Transaction_no, Order_no, Transaction_Date,Transaction_Time, Amount, Receipt_no;
        ImageView status;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            Bank_Ref_no = itemView.findViewById(R.id.bank_Ref_No);
        Transaction_no = itemView.findViewById(R.id.Trans_No);
        Order_no =  itemView.findViewById(R.id.order_no);
        Transaction_Date =itemView.findViewById(R.id.Trans_Date);
        Transaction_Time = itemView.findViewById(R.id.Trans_Time);
        status = itemView.findViewById(R.id.status);
        Amount = itemView.findViewById(R.id.Amount);
        Receipt_no = itemView.findViewById(R.id.Receipt_no);
        }
    }
}
