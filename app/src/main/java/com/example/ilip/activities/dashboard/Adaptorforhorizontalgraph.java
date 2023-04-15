package com.example.ilip.activities.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ilip.R;
import com.example.ilip.all_model.DashboardCourseAttendanceModel;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.ArrayList;

public class Adaptorforhorizontalgraph extends RecyclerView.Adapter<Adaptorforhorizontalgraph.GraphViewHolder> {
    private  ArrayList<DashboardCourseAttendanceModel> mDataset;
    private  Context context;

    public Adaptorforhorizontalgraph(ArrayList<DashboardCourseAttendanceModel> mDataset, Context context) {
        this.mDataset = mDataset;
        this.context = context;
    }

    @NonNull
    @Override
    public GraphViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_graph_layout,parent,false);
        return new GraphViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GraphViewHolder holder, int position) {
    holder.subjName.setText(mDataset.get(position).getCourseUniversityCode());
    holder.linearProgressIndicator.setProgress((int) Float.parseFloat(mDataset.get(position).getCoursePercentage()));
    holder.Percentage.setText(mDataset.get(position).getCoursePercentage()+"%");
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class GraphViewHolder extends RecyclerView.ViewHolder{
        LinearProgressIndicator linearProgressIndicator;
        TextView subjName,Percentage;
        public GraphViewHolder(@NonNull View itemView) {
            super(itemView);
        linearProgressIndicator = itemView.findViewById(R.id.progress_bar);
        subjName = itemView.findViewById(R.id.subName);
        Percentage = itemView.findViewById(R.id.progressText);
        }
    }
}
