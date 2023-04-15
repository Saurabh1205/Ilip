package com.example.ilip.activities.dashboard.notification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ilip.all_model.CommonModel;
import com.example.ilip.R;

import java.util.ArrayList;

public class AdaptorClass extends RecyclerView.Adapter<AdaptorClass.NotificationViewHolder> {
    ArrayList<CommonModel> arrayList;
    Context context;

    public AdaptorClass(ArrayList<CommonModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_notification_dilog, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//        selecteddate = new SimpleDateFormat(null,Locale.getDefault()).format(new Date());
//        holder.dateAndTime.setText();
        holder.SummeryDetail.setText(arrayList.get(position).getNotificationSummary());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder{
        TextView dateAndTime,SummeryDetail;
       public NotificationViewHolder(@NonNull View itemView) {
           super(itemView);
           dateAndTime = itemView.findViewById(R.id.txt_date);
           SummeryDetail = itemView.findViewById(R.id.textSummaryDetails);
       }
   }
}
