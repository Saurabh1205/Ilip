package com.example.ilip.activities.attendance.semesterWise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ilip.all_model.DashboardCourseAttendanceModel;
import com.example.ilip.R;

import java.util.ArrayList;

public class SemWiseAdapter extends RecyclerView.Adapter<SemWiseAdapter.DataViewHolder>{
    ArrayList<DashboardCourseAttendanceModel> arrayList;
    Context context;

    public SemWiseAdapter(ArrayList<DashboardCourseAttendanceModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sem_wise_statistical_layout, parent, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        DashboardCourseAttendanceModel courseAttendanceModel= arrayList.get(position);
    holder.data1.setText(courseAttendanceModel.getSemWiseStatistic());
    holder.data2.setText(courseAttendanceModel.getSemWiseAttendance());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder{
        TextView data1,data2;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            data1 = itemView.findViewById(R.id.trimester);
            data2 = itemView.findViewById(R.id.attendanceInPercent);
        }
    }
}
