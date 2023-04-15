package com.example.ilip.activities.syllabus;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ilip.activities.syllabus.actionPlan.ActionPlanActivity;
import com.example.ilip.activities.syllabus.university.UniversityActivity;
import com.example.ilip.all_model.CommonModel;
import com.example.ilip.all_model.DashboardCourseAttendanceModel;
import com.example.ilip.common.ConstantAPIsClass;
import com.example.ilip.R;
import com.google.android.material.chip.Chip;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.ArrayList;

public class MySyllabusListAdaptor extends RecyclerView.Adapter<MySyllabusListAdaptor.SyllabuslistViewHolder> {
    ArrayList<DashboardCourseAttendanceModel> mArrayList;
    Context context;
    ConstantAPIsClass constantAPIsClass;
    CommonModel commonModel;

    public MySyllabusListAdaptor(ArrayList<DashboardCourseAttendanceModel> mArrayList, Context context, ConstantAPIsClass constantAPIsClass, CommonModel commonModel) {
        this.mArrayList = mArrayList;
        this.context = context;
        this.constantAPIsClass = constantAPIsClass;
        this.commonModel = commonModel;
    }

    @NonNull
    @Override
    public SyllabuslistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_syllabus_list,parent,false);
        return new SyllabuslistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SyllabuslistViewHolder holder, @SuppressLint("RecyclerView") int position) {
    DashboardCourseAttendanceModel courseAttendanceModel = mArrayList.get(position);
    holder.subjectName.setText(courseAttendanceModel.getCourseTitle());
    holder.subjectType.setText(courseAttendanceModel.getCourseUniversityCode()+" ("+courseAttendanceModel.getCourseType()+") ");
    holder.subjectFaculty.setText(courseAttendanceModel.getCourseFacultyName());
    holder.attendDays.setText(courseAttendanceModel.getCourseRatio1());
    holder.outOffDays.setText("/"+courseAttendanceModel.getCourseRatio2());
    holder.attendPercent.setText(courseAttendanceModel.getCoursePercentage()+"%");
    double dblVal=Double.parseDouble(courseAttendanceModel.getCoursePercentage());
    int data = (int) Math.abs(dblVal);
    holder.progressbar.setProgress(data);
    holder.layout1.setTag(courseAttendanceModel);
    holder.layout1.setOnClickListener(v -> {
        commonModel.setSubjectApplicableNo(mArrayList.get(position).getSubjectApplicableNo());
        commonModel.setSubjectGroupId(mArrayList.get(position).getSubjectGroupId());
        Intent i = new Intent(context, UniversityActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        bundle.putSerializable("CommonModel", commonModel);
        i.putExtras(bundle);
        context.startActivity(i);
    });
    holder.layout2.setTag(courseAttendanceModel);
    holder.layout2.setOnClickListener(v -> {
        commonModel.setSubjectDetailId(mArrayList.get(position).getSubjectDetailsId());
        commonModel.setSubjectGroupId(mArrayList.get(position).getSubjectGroupId());
        commonModel.setSubjectApplicableNo(mArrayList.get(position).getSubjectApplicableNo());
        commonModel.setSubjectBatchDtlId(mArrayList.get(position).getSubjectBatchDetailId());
        commonModel.setSubjectEmployeeIds(mArrayList.get(position).getSubjectEmployeeId());
        commonModel.setSelectedSubjectName(mArrayList.get(position).getCourseTitle());
        commonModel.setSubjectEmployeeName(mArrayList.get(position).getCourseFacultyName());
        commonModel.setSubjectType(holder.subjectType.getText().toString());
        Intent i = new Intent(context, ActionPlanActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        bundle.putSerializable("CommonModel", commonModel);

        i.putExtras(bundle);
        context.startActivity(i);
    });
    if(mArrayList.get(position).getCompulsory_Optional_Flag().equals("COMPULSORY")){
        holder.subjectType.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_complsry,0,0,0);
    }else if(mArrayList.get(position).getCompulsory_Optional_Flag().equals("ELECTIVE")){
        holder.subjectType.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_elective_pe,0,0,0);
    }else{
        holder.subjectType.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_open_elective,0,0,0);
    }

    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public static class SyllabuslistViewHolder extends RecyclerView.ViewHolder{
        TextView subjectName,subjectType,subjectFaculty,attendDays,outOffDays,attendPercent;
        CircularProgressIndicator progressbar;
        Chip layout1,layout2;

       public SyllabuslistViewHolder(@NonNull View itemView) {
           super(itemView);
           subjectName = itemView.findViewById(R.id.subjectName);
           subjectType = itemView.findViewById(R.id.subjectType);
           subjectFaculty = itemView.findViewById(R.id.subjectTeacher);
           attendDays = itemView.findViewById(R.id.subjectRatio1);
           outOffDays =itemView.findViewById(R.id.subjectRatio2);
           attendPercent = itemView.findViewById(R.id.subjectAttendance_Percent);
           layout1 = itemView.findViewById(R.id.tab1);
           layout2 = itemView.findViewById(R.id.tab2);
           progressbar = itemView.findViewById(R.id.subjectProgress_bar);
       }
   }




}
