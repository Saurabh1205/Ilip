package com.example.ilip.activities.attendance.subjectWise.subjectAndProfessorActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ilip.all_model.DashboardCourseAttendanceModel;
import com.example.ilip.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class SubjectListDetailAdaptor extends RecyclerView.Adapter<SubjectListDetailAdaptor.SubjectDataViewHolder> {
 ArrayList<DashboardCourseAttendanceModel> arrayList;
 Context context;

    public SubjectListDetailAdaptor(ArrayList<DashboardCourseAttendanceModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public SubjectDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subject_list_view_layout, parent, false);
        return new SubjectDataViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull SubjectDataViewHolder holder, int position) {
        DashboardCourseAttendanceModel courseAttendanceModel = arrayList.get(position);
        holder.imageAttendanceStatus.setImageResource(courseAttendanceModel.getDaywiseImageAttendStus());
        holder.TopicName.setText(courseAttendanceModel.getTopicName());
        holder.TopicDescription.setText(courseAttendanceModel.getTopicDescription());
        holder.subjectDate.setText(courseAttendanceModel.getDayWiseASON_date());
        holder.subjectPeriodTypeCode.setText(courseAttendanceModel.getDayWisePeriodTypeCode());
        holder.subjectTimeDuration.setText(courseAttendanceModel.getDayWiseTimeDuration());
        holder.AttendanceStatus.setText(courseAttendanceModel.getDayWiseAttendanceStatus().trim());
        holder.subjectTeacher.setText(courseAttendanceModel.getDayWiseEmpName());
        holder.unit.setText(courseAttendanceModel.getSyllabusDescription());
        holder.layoutForHide.setTag(arrayList.get(position));

        if(arrayList.get(position).getSyllabusDescription().equals("")){
            holder.layoutForHide.setVisibility(View.GONE);
        }else{
            holder.layoutForHide.setVisibility(View.VISIBLE);
        }
        switch (arrayList.get(position).getDayWiseAttendStatusSCode()) {
            case "PR":
            case "S-LV":
                holder.AttendanceStatus.setTextColor(Color.parseColor("#2ECC71"));
                holder.cardView.setStrokeColor(Color.parseColor("#2ECC71"));
                break;
            case "CA":
            case "CW":
            case "BK":
            case "H":
            case "CE":
            case "F-LV":
                holder.AttendanceStatus.setTextColor(Color.parseColor("#F2A42E"));
                holder.cardView.setStrokeColor(Color.parseColor("#F2A42E"));
                break;
            case "AB":
                holder.AttendanceStatus.setTextColor(Color.parseColor("#F05454"));
                holder.cardView.setStrokeColor(Color.parseColor("#F05454"));
                break;
            default:
                holder.AttendanceStatus.setTextColor(Color.GRAY);
                holder.cardView.setStrokeColor(Color.GRAY);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class SubjectDataViewHolder extends RecyclerView.ViewHolder{
        TextView TopicName,TopicDescription,subjectTeacher,subjectTimeDuration,subjectDate,subjectPeriodTypeCode;
        TextView AttendanceStatus;
        ImageView imageAttendanceStatus;
        LinearLayout layoutForHide;
        MaterialCardView cardView;
        Chip unit;
       public SubjectDataViewHolder(@NonNull View itemView) {
           super(itemView);
           subjectDate = itemView.findViewById(R.id.daywiseSubDate);
           subjectPeriodTypeCode = itemView.findViewById(R.id.daywisePeriodShortcode);
           subjectTimeDuration = itemView.findViewById(R.id.daywiseTimeDuration);
           AttendanceStatus = itemView.findViewById(R.id.daywiseAttendanceStatus);
           subjectTeacher = itemView.findViewById(R.id.daywiseEmpName);
           TopicName = itemView.findViewById(R.id.topicName);
           TopicDescription = itemView.findViewById(R.id.topicDescription);
           imageAttendanceStatus = itemView.findViewById(R.id.imgAttendance);
           layoutForHide = itemView.findViewById(R.id.hideLayout);
           unit = itemView.findViewById(R.id.unitName);
           cardView = itemView.findViewById(R.id.cardBg);
       }
   }
}
