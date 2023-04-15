package com.example.ilip.activities.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ilip.all_model.DashboardCourseAttendanceModel;
import com.example.ilip.R;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class DashboardCourseAttendanceAdaptor extends RecyclerView.Adapter<DashboardCourseAttendanceAdaptor.DataViewHolder> {

    private final ArrayList<DashboardCourseAttendanceModel> mDataset;
    private final ArrayList ImageList;
    private final Context context;

    public DashboardCourseAttendanceAdaptor(ArrayList<DashboardCourseAttendanceModel> mDataset, ArrayList imageList, Context context) {
        this.mDataset = mDataset;
        ImageList = imageList;
        this.context = context;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_cardview, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        DashboardCourseAttendanceModel courseAttendanceModel = mDataset.get(position);
//    holder.cardBgImage.setBackgroundResource(courseAttendanceModel.getBackGroundImage());
    holder.cardBgImage.setBackgroundResource((Integer) ImageList.get(position));
    holder.subjectName.setText(courseAttendanceModel.getCourseTitle());
    holder.subjectType.setText(courseAttendanceModel.getCourseType());
    holder.subjectTeacher.setText(courseAttendanceModel.getCourseFacultyName());
    holder.subjectRatio1.setText(courseAttendanceModel.getCourseRatio1()+"/");
    holder.subjectRatio2.setText(courseAttendanceModel.getCourseRatio2()+" : ");
    holder.subjectPercentage.setText(courseAttendanceModel.getCoursePercentage()+"%");
        double dblVal=Double.parseDouble(courseAttendanceModel.getCoursePercentage());
        int data = (int) Math.abs(dblVal);
        if(data<75){
            int colorRed= 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                colorRed = context.getColor(R.color.dashColorRed);
            }
            holder.subjectPercentage.setTextColor(colorRed);
            holder.subjectRatio1.setTextColor(colorRed);
        }else if(data>85){
            int colorGreen= 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                colorGreen = context.getColor(R.color.dashColorGreen);
            }
            holder.subjectPercentage.setTextColor(colorGreen);
            holder.subjectRatio1.setTextColor(colorGreen);
        }else{
            int colorOrange= 0;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                colorOrange = context.getColor(R.color.dashColorOrange);
            }
            holder.subjectPercentage.setTextColor(colorOrange);
            holder.subjectRatio1.setTextColor(colorOrange);
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public static class DataViewHolder extends RecyclerView.ViewHolder{
        TextView subjectName,subjectType,subjectTeacher,subjectRatio1,subjectRatio2,subjectPercentage;
        MaterialCardView cardView;
        LinearLayout cardBgImage;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            subjectName = itemView.findViewById(R.id.subjectName);
            subjectType = itemView.findViewById(R.id.subjectType);
            subjectTeacher = itemView.findViewById(R.id.subjectTeacher);
            subjectRatio1 = itemView.findViewById(R.id.subjectRatio1);
            subjectRatio2 = itemView.findViewById(R.id.subjectRatio2);
            subjectPercentage = itemView.findViewById(R.id.subjectPercentage);
            cardView = itemView.findViewById(R.id.cardSubject);
            cardBgImage=itemView.findViewById(R.id.cardBg);
        }
    }
}
