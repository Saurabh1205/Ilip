package com.example.ilip.activities.syllabus.university;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ilip.activities.syllabus.UniversitySyllabusModel;
import com.example.ilip.R;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class UniversityAdaptor extends RecyclerView.Adapter<UniversityAdaptor.UniversityViewHolder> {
    ArrayList<UniversitySyllabusModel> arrayList;
    List<String> arrayList1;
    Context context;

    public UniversityAdaptor(ArrayList<UniversitySyllabusModel> arrayList, List<String> arrayList1, Context context) {
        this.arrayList = arrayList;
        this.arrayList1 = arrayList1;
        this.context = context;
    }

    @NonNull
    @Override
    public UniversityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_univesity_syllabus_list,parent,false);
        return new UniversityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UniversityViewHolder holder, int position) {
        holder.layoutUniversity.setTag(arrayList.get(position));
        holder.setIsRecyclable(false);
        holder.Unit.setTag(arrayList.get(position));
        holder.topicName.setTag(arrayList.get(position));
        if(!arrayList.get(position).getSR_NO().equals("")){
            holder.Unit.setVisibility(View.INVISIBLE);
            holder.topicName.setTextSize(12);
            holder.topicName.setText(arrayList.get(position).getSR_NO()+" - "+arrayList.get(position).getTOPIC_DESCRIPTION());
        }else{
            holder.Unit.setVisibility(View.VISIBLE);
            holder.topicName.setTextSize(14);
            holder.Unit.setText(arrayList.get(position).getSYLLABUS_DESCRIPTION());
            holder.topicName.setText(arrayList.get(position).getTOPIC_NAME());
        }
        holder.weightage.setText(arrayList.get(position).getWEIGHTAGE());
//    holder.subTopics.add
//        ArrayAdapter<String> arr;
//        arr = new ArrayAdapter(context,support_simple_spinner_dropdown_item,arrayList1);
//        holder.subTopics.setAdapter(arr);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public static class UniversityViewHolder extends RecyclerView.ViewHolder{
        Chip Unit;
        TextView topicName,topicDescription,weightage;
        ListView subTopics;
        LinearLayout layoutUniversity;
        public UniversityViewHolder(@NonNull View itemView) {
            super(itemView);

            Unit = itemView.findViewById(R.id.unit);
            topicName = itemView.findViewById(R.id.topicName);
           // topicDescription = itemView.findViewById(R.id.topicDescription);
            weightage = itemView.findViewById(R.id.weightage);
           // subTopics = itemView.findViewById(R.id.subTopics);
            layoutUniversity = itemView.findViewById(R.id.layoutUniversity);
        }
    }
}
