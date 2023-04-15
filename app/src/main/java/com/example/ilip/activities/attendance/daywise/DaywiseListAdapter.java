package com.example.ilip.activities.attendance.daywise;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ilip.all_model.DashboardCourseAttendanceModel;
import com.example.ilip.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class DaywiseListAdapter extends RecyclerView.Adapter<DaywiseListAdapter.DataViewHolder> {
    private final ArrayList<DashboardCourseAttendanceModel> mDataset;
    private final Context context;

    public DaywiseListAdapter(ArrayList<DashboardCourseAttendanceModel> mDataset, Context context) {
        this.mDataset = mDataset;
        this.context = context;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.day_wise_view, parent, false);
        return new DataViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DashboardCourseAttendanceModel courseAttendanceModel = mDataset.get(position);
        holder.imageAttendanceStatus.setImageResource(courseAttendanceModel.getDaywiseImageAttendStus());
        holder.subjectName.setText(courseAttendanceModel.getDayWiseSubDtl());
        holder.subjectType.setText(courseAttendanceModel.getDayWiseSubType());
        holder.subjectDate.setText(courseAttendanceModel.getDayWiseASON_date());
        holder.subjectPeriodTypeCode.setText(courseAttendanceModel.getDayWisePeriodTypeCode());
        holder.AttendanceStatus.setText(courseAttendanceModel.getDayWiseAttendanceStatus());
        holder.subjectTeacher.setText(courseAttendanceModel.getDayWiseEmpName());
        holder.subjectTimeDuration.setText(courseAttendanceModel.getDayWiseTimeDuration());
        holder.AttendanceStatus.setTag(position);
        switch (mDataset.get(position).getDayWiseAttendStatusSCode()) {
            case "PR":
            case "S-LV":
                holder.AttendanceStatus.setTextColor(Color.parseColor("#2ECC71"));
                holder.cardStockColor.setStrokeColor(Color.parseColor("#2ECC71"));
                break;
            case "CA":
            case "CW":
            case "BK":
            case "H":
            case "CE":
            case "F-LV":
                holder.AttendanceStatus.setTextColor(Color.parseColor("#F2A42E"));
                holder.cardStockColor.setStrokeColor(Color.parseColor("#F2A42E"));
                break;
            case "AB":
                holder.AttendanceStatus.setTextColor(Color.parseColor("#F05454"));
                holder.cardStockColor.setStrokeColor(Color.parseColor("#F05454"));
                break;
            default:
                holder.AttendanceStatus.setTextColor(Color.GRAY);
                holder.cardStockColor.setStrokeColor(Color.GRAY);
                break;
        }
        /*holder.AttendanceStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.AttendanceStatus.setText(courseAttendanceModel.getDayWiseAttendanceStatus());
                notifyDataSetChanged();
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder {
        TextView subjectName,subjectType,subjectTeacher,subjectTimeDuration,subjectDate,subjectPeriodTypeCode;
        TextView AttendanceStatus;
        ImageView imageAttendanceStatus;
        MaterialCardView cardStockColor;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectDate = itemView.findViewById(R.id.daywiseSubDate);
            subjectPeriodTypeCode = itemView.findViewById(R.id.daywisePeriodShortcode);
            subjectTimeDuration = itemView.findViewById(R.id.daywiseTimeDuration);
            AttendanceStatus = itemView.findViewById(R.id.daywiseAttendanceStatus);
            subjectTeacher = itemView.findViewById(R.id.daywiseEmpName);
            subjectName = itemView.findViewById(R.id.daywiseSubjectName);
            subjectType = itemView.findViewById(R.id.daywiseSubjectType);
            imageAttendanceStatus = itemView.findViewById(R.id.imgAttendance);
            cardStockColor = itemView.findViewById(R.id.cardBg);
        }
    }


}
