package com.example.ilip.activities.attendance.subjectWise.subjectAndProfessorActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ilip.R;
import com.google.android.material.card.MaterialCardView;
import com.orhanobut.dialogplus.DialogPlus;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SubjectProfessorListDetailAdaptor extends RecyclerView.Adapter<SubjectProfessorListDetailAdaptor.ProfessorDataViewHolder>{
    ArrayList<SubjectProfessorListModel> arrayList;
    Context context;

    public SubjectProfessorListDetailAdaptor(ArrayList<SubjectProfessorListModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProfessorDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_professor_list_layout,parent,false);
        return new ProfessorDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessorDataViewHolder holder, @SuppressLint("RecyclerView") int position) {
        SubjectProfessorListModel  subjectProfessorListModel = arrayList.get(position);
        if (!subjectProfessorListModel.getProfessorImageURL().equals("")) {
            Picasso.get()
                    .load(subjectProfessorListModel.getProfessorImageURL())
                    .into(holder.imageView);
        }

        holder.professorName.setText(subjectProfessorListModel.getProfessorName());
        holder.professorDesignation.setText(subjectProfessorListModel.getProfessorDesignation());
        holder.cardView.setTag(arrayList.get(position));
        holder.cardView.setOnClickListener(v -> getProfessorProfilePopUp(v.getContext(),position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ProfessorDataViewHolder extends RecyclerView.ViewHolder{
        MaterialCardView cardView;
        ImageView imageView;
        TextView professorName,professorDesignation;
       public ProfessorDataViewHolder(@NonNull View itemView) {
           super(itemView);
           imageView = itemView.findViewById(R.id.ProfessorImage);
           professorName = itemView.findViewById(R.id.professorName);
           professorDesignation  = itemView.findViewById(R.id.professorDesignation);
           cardView = itemView.findViewById(R.id.cardProfessor);
       }
   }
        public void getProfessorProfilePopUp(Context context,int position){
            final DialogPlus inflate = DialogPlus.newDialog(context)
                    .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.popup_professor_profile_layout))
                    .setExpanded(true, WindowManager.LayoutParams.MATCH_PARENT)
                    .setGravity(Gravity.BOTTOM)
                    .setCancelable(true)
                    .create();

            TextView textEmail = (TextView) inflate.findViewById(R.id.textEmail);
            textEmail.setText(arrayList.get(position).getProfessorEmailID());
            TextView textMobile = (TextView) inflate.findViewById(R.id.textMobile);
            textMobile.setText(arrayList.get(position).getProfessorMobile());
            TextView textDesignation = (TextView) inflate.findViewById(R.id.textDesignation);
            textDesignation.setText(arrayList.get(position).getProfessorDesignation());
            TextView textId = (TextView) inflate.findViewById(R.id.textId);
            textId.setText(arrayList.get(position).getProfessorId());
            ImageView profileimg = (ImageView) inflate.findViewById(R.id.imageProfile);
            if(!arrayList.get(position).getProfessorImageURL().equals("")) {
                Picasso.get()
                        .load(arrayList.get(position).getProfessorImageURL())
                        .into(profileimg);
            }
            TextView textName = (TextView) inflate.findViewById(R.id.textName);
            textName.setText(arrayList.get(position).getProfessorName());
            TextView textAboutme = (TextView) inflate.findViewById(R.id.textAboutMe);
            //textAboutme.setText(arrayList.get(position).getProfessorAboutMe());
            androidx.appcompat.widget.Toolbar toolbar = (Toolbar) inflate.findViewById(R.id.toolbar4ProfileFaculty);
            toolbar.setTitle("");
            toolbar.setNavigationOnClickListener(view -> inflate.dismiss());
            inflate.show();
        }
}
