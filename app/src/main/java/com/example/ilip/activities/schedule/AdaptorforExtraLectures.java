package com.example.ilip.activities.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ilip.R;

import java.util.ArrayList;

public class AdaptorforExtraLectures extends RecyclerView.Adapter<AdaptorforExtraLectures.ExtraLectureViewHolder> {
    ArrayList<ScheduleClassModel> arrayList;
    Context context;

    public AdaptorforExtraLectures(ArrayList<ScheduleClassModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public ExtraLectureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_extra_lectures,parent,false);
        return new ExtraLectureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExtraLectureViewHolder holder, int position) {
//        if(arrayList.get(position).getTimeSlot().isEmpty()){
//            //holder.layout1.setVisibility(View.GONE);
//            // holder.recessTime.setVisibility(View.VISIBLE);
//            //holder.recessTime.setText(arrayList.get(position).getRecessDescription() + " | " + arrayList.get(position).getRecessDuration());
//        }else
        {
            // holder.layout1.setVisibility(View.VISIBLE);
            //holder.recessTime.setVisibility(View.GONE);
            holder.Date.setText(arrayList.get(position).getDate());
            holder.timeDuration.setText(arrayList.get(position).getTimeSlot());
            holder.subjectName.setText(arrayList.get(position).getSubDescription());
            holder.subjectType.setText(arrayList.get(position).getUniversityCode() + " (" + arrayList.get(position).getSubjectType() + ")");
            holder.subjectBranch.setText(arrayList.get(position).getSubjectBatchName());
            holder.employeeNameCode.setText(arrayList.get(position).getEmployeeNameShort());
            holder.roomName.setText(arrayList.get(position).getRoomCode());
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ExtraLectureViewHolder extends RecyclerView.ViewHolder{
        TextView Date,timeDuration,subjectName,subjectType,subjectBranch,employeeNameCode,roomName,All,recessTime;

        public ExtraLectureViewHolder(@NonNull View itemView) {
            super(itemView);
            Date = itemView.findViewById(R.id.date);
            timeDuration = itemView.findViewById(R.id.timeSlot);
            subjectName = itemView.findViewById(R.id.subjectTitle);
            subjectType = itemView.findViewById(R.id.subjectType);
            subjectBranch = itemView.findViewById(R.id.subjectBranch);
            employeeNameCode = itemView.findViewById(R.id.EmployeeShortName);
            roomName = itemView.findViewById(R.id.roomNo);
            All = itemView.findViewById(R.id.all);
           // recessTime = itemView.findViewById(R.id.recessTime);
        }
    }
}
