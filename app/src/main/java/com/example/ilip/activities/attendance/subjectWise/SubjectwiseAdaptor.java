package com.example.ilip.activities.attendance.subjectWise;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ilip.activities.attendance.subjectWise.subjectAndProfessorActivity.SubjectScreenActivity;
import com.example.ilip.all_model.CommonModel;
import com.example.ilip.all_model.DashboardCourseAttendanceModel;
import com.example.ilip.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.ArrayList;

public class SubjectwiseAdaptor extends RecyclerView.Adapter<SubjectwiseAdaptor.DataViewHolderSubjectWsie> {
    ArrayList<DashboardCourseAttendanceModel> arrayList;
    Context context;
    CommonModel commonModel;

//    public SubjectwiseAdaptor(ArrayList<DashboardCourseAttendanceModel> arrayList, Context context) {
//        this.arrayList = arrayList;
//        this.context = context;
//    }


    public SubjectwiseAdaptor(ArrayList<DashboardCourseAttendanceModel> arrayList, Context context, CommonModel commonModel) {
        this.arrayList = arrayList;
        this.context = context;
        this.commonModel = commonModel;
    }

    @NonNull
    @Override
    public DataViewHolderSubjectWsie onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_wise_list_layout,parent,false);
        return new DataViewHolderSubjectWsie(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolderSubjectWsie holder, int position) {
        DashboardCourseAttendanceModel courseAttendanceModel = arrayList.get(position);
        holder.subjectName.setText(courseAttendanceModel.getCourseTitle());
        holder.subjectType.setText(courseAttendanceModel.getCourseType());
        holder.ratio1.setText(courseAttendanceModel.getCourseRatio1()+"/");
        holder.ratio2.setText(courseAttendanceModel.getCourseRatio2());
        double dblVal=Double.parseDouble(courseAttendanceModel.getCoursePercentage());
        int data = (int) Math.abs(dblVal);
        holder.subjectWiseProgressBar.setProgress(data);
        holder.percentage.setText(data+"%");
        if(data<75){
            int colorRed= 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                colorRed = context.getColor(R.color.dashColorRed);
            }
            holder.subjectWiseProgressBar.setIndicatorColor(colorRed);
            holder.ratio1.setTextColor(colorRed);
        }else if(data>85){
            int colorGreen= 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                colorGreen = context.getColor(R.color.dashColorGreen);
            }
            holder.subjectWiseProgressBar.setIndicatorColor(colorGreen);
            holder.ratio1.setTextColor(colorGreen);
        }else{
            int colorOrange= 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                colorOrange = context.getColor(R.color.dashColorOrange);
            }
            holder.subjectWiseProgressBar.setIndicatorColor(colorOrange);
            holder.ratio1.setTextColor(colorOrange);
        }
        holder.cardView.setTag(arrayList.get(position));
        holder.cardView.setOnClickListener(v -> {
            commonModel.setSelectedSubjectName(courseAttendanceModel.getCourseTitle());
            commonModel.setSubjectDetailId(courseAttendanceModel.getSubjectDetailsId());
            commonModel.setSubjectApplicableNo(courseAttendanceModel.getSubjectApplicableNo());
            commonModel.setSubjectBatchDtlId(courseAttendanceModel.getSubjectBatchDetailId());
            commonModel.setSubjectEmployeeIds(courseAttendanceModel.getSubjectEmployeeId());
            commonModel.setDaysAttend(courseAttendanceModel.getCourseRatio1());
            commonModel.setDaysOutOff(courseAttendanceModel.getCourseRatio2());
            commonModel.setAttendancePercent(courseAttendanceModel.getCoursePercentage());
            Intent intent = new Intent(context, SubjectScreenActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("CommonModel", commonModel);
            intent.putExtras(bundle);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class DataViewHolderSubjectWsie extends RecyclerView.ViewHolder{
        TextView subjectName,subjectType,ratio1,ratio2,percentage;
        CircularProgressIndicator subjectWiseProgressBar;
        MaterialCardView cardView;
        public DataViewHolderSubjectWsie(@NonNull View itemView) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.subjectWiseName);
            subjectType = itemView.findViewById(R.id.subjectWiseType);
            ratio1 = itemView.findViewById(R.id.subjectWiseLimit1);
            ratio2 = itemView.findViewById(R.id.subjectWiseLimit2);
            percentage = itemView.findViewById(R.id.subjectWiseAttendance_Percent);
            subjectWiseProgressBar = itemView.findViewById(R.id.subjectWiseProgress_bar);
            cardView = itemView.findViewById(R.id.cardSubjectWise);
        }
    }

}
