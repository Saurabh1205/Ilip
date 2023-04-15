package com.example.ilip.activities.syllabus.actionPlan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ilip.activities.syllabus.UniversitySyllabusModel;
import com.example.ilip.R;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class ActionPlanAdaptor extends RecyclerView.Adapter<ActionPlanAdaptor.ActionPlanViewHolder> {

    ArrayList<UniversitySyllabusModel> arrayList;
    Context context;

    public ActionPlanAdaptor(ArrayList<UniversitySyllabusModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ActionPlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_action_plan_list, parent, false);
        return new ActionPlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActionPlanViewHolder holder, int position) {
        holder.layoutActionPlan.setTag(arrayList.get(position));

        holder.layoutForDetails.setTag(arrayList.get(position));
        holder.unitName.setTag(arrayList.get(position));
        holder.setIsRecyclable(false);
        if (!arrayList.get(position).getSR_NO().equals("")) {
            holder.unitName.setVisibility(View.INVISIBLE);
            holder.subjectTitle.setTextSize(12);
            holder.subjectTitle.setText(arrayList.get(position).getSR_NO() + " - " + arrayList.get(position).getTOPIC_DESCRIPTION());
            holder.coveredDate.setText(arrayList.get(position).getTOPIC_COVERED_DATE());
            holder.proposedDate.setText(arrayList.get(position).getPLANED_START_DATE() + " - " + arrayList.get(position).getPLANED_END_DATE());
            holder.layoutForDetails.setVisibility(View.VISIBLE);
        } else {
            holder.unitName.setVisibility(View.VISIBLE);
            holder.subjectTitle.setTextSize(14);
            holder.unitName.setText(arrayList.get(position).getSYLLABUS_DESCRIPTION());
            holder.subjectTitle.setText(arrayList.get(position).getTOPIC_NAME());
            holder.layoutForDetails.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ActionPlanViewHolder extends RecyclerView.ViewHolder {
        Chip unitName;
        TextView subjectTitle, coveredDate, proposedDate;
        LinearLayout layoutForDetails, layoutActionPlan;

        public ActionPlanViewHolder(@NonNull View itemView) {
            super(itemView);
            unitName = itemView.findViewById(R.id.chipForUnit);
            subjectTitle = itemView.findViewById(R.id.SubTitle);
            coveredDate = itemView.findViewById(R.id.coveredDate);
            proposedDate = itemView.findViewById(R.id.proposedSpan);
            layoutForDetails = itemView.findViewById(R.id.layoutForDetails);
            layoutActionPlan = itemView.findViewById(R.id.layoutActionPlan);
        }
    }
}
