package com.example.ilip.activities.fees.payable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ilip.R;

import java.util.ArrayList;

public class AbstractforpayableIndetails extends RecyclerView.Adapter<AbstractforpayableIndetails.ViewHolderForInsider>{
    Context context;
    ArrayList<ModelForPayable> arrayList;
    public AbstractforpayableIndetails(Context context, ArrayList<ModelForPayable> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolderForInsider onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_payables_insider,parent,false);
        return new ViewHolderForInsider(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderForInsider holder, int position) {
    if(arrayList.get(position).getFEE_INSTALLMENT_NO().equals("")){
        holder.layoutForInstalment.setVisibility(View.GONE);
    }else{
        holder.layoutForInstalment.setVisibility(View.VISIBLE);
        holder.instalmentNo.setText(arrayList.get(position).getFEE_INSTALLMENT_NO());

    }
//    holder.text1.setText(arrayList.get(position).getFEE_TYPE_DESC().get(position));
    holder.text2.setText(arrayList.get(position).getINS_RECEIVABLE_TOTAL());
    holder.text3.setText(arrayList.get(position).getINS_RECEIVED_TOTAL());
    holder.text4.setText(arrayList.get(position).getINS_CONSESSION_TOTAL());
    holder.text5.setText(arrayList.get(position).getINS_BALANCE_TOTAL());
        MyListAdapter adapter = new MyListAdapter(context,arrayList.get(position).getFEE_TYPE_DESC(),arrayList.get(position).getRECEIVABLE_AMOUNT(),arrayList.get(position).getFEE_RECEIPT_AMOUNT(),arrayList.get(position).getDISCOUNT_AMOUNT(),arrayList.get(position).getBALANCE_AMOUNT());
        holder.listView.setLayoutManager(new LinearLayoutManager(context));
        holder.listView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolderForInsider extends RecyclerView.ViewHolder{
    LinearLayout layoutForInstalment;
    RecyclerView listView;
    TextView  instalmentNo,text1,text2,text3,text4,text5;
    public ViewHolderForInsider(@NonNull View itemView) {
        super(itemView);

        layoutForInstalment = itemView.findViewById(R.id.instalment);
        instalmentNo = itemView.findViewById(R.id.instalmentNo);
        // text1 = (TextView) itemView.findViewById(R.id.Particulars);
         text2 = itemView.findViewById(R.id.Receivable_Total);
         text3 = itemView.findViewById(R.id.Received_Total);
         text4 = itemView.findViewById(R.id.Concession_Total);
         text5 = itemView.findViewById(R.id.Outstanding_Total);
        listView = itemView.findViewById(R.id.listData);
    }
}

}
