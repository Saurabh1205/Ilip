package com.example.ilip.activities.dashboard.otherLinks.gallery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ilip.activities.dashboard.otherLinks.OtherLinkCommonModel;
import com.example.ilip.R;
import com.google.android.material.card.MaterialCardView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdaptorForGallery extends RecyclerView.Adapter<AdaptorForGallery.GalleryViewHolder> {
    CustomizedGalleryAdapter customGalleryAdapter;
    ArrayList<OtherLinkCommonModel> arrayList;
    ArrayList urls;
    Context context;

    public AdaptorForGallery(ArrayList<OtherLinkCommonModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_gallery_data,parent,false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, @SuppressLint("RecyclerView") int position) {
       try {
           JSONArray arrayList1 = arrayList.get(position).getJsonArrayData();
           Log.e("Json Array data", "is==="+arrayList1);
           urls = new ArrayList();
           for (int j = 0; j < arrayList1.length(); j++) {
               JSONObject jsonObj = (JSONObject) arrayList1.get(j);
               urls.add(jsonObj.getString("ATTACHMENT_LIVE_PATH"));
           }
          // otherLinkCommonModel.setImages(urls);
       }catch (Exception e){
           e.printStackTrace();
       }

        holder.Name.setText(arrayList.get(position).getGalleryItemName());
        holder.DateAndTime.setText(arrayList.get(position).getGalleryItemDate_time());
        Log.e("Array data","is ==="+arrayList.get(position).getImages());
       customGalleryAdapter = new CustomizedGalleryAdapter(context,urls /*arrayList.get(position).getImages()*/);
       holder.simpleGallery.setAdapter(customGalleryAdapter);
       holder.cardView.setTag(position);
       holder.cardView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Log.e("selected Value","is"+position);
               Intent intent = new Intent(v.getContext(), GalleryViewActivity.class);
               // intent.putExtras();
               Bundle bundle = new Bundle();
               bundle.putString("Title",holder.Name.getText().toString());
               bundle.putString("SelectedStringArray",arrayList.get(position).getJsonArrayData().toString());
               intent.putExtras(bundle);
               v.getContext().startActivity(intent);
           }
       });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class GalleryViewHolder extends RecyclerView.ViewHolder{
        TextView Name,DateAndTime;
        Gallery simpleGallery;
        MaterialCardView cardView;
        public GalleryViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.GalleryName);
            DateAndTime = itemView.findViewById(R.id.GalleryDateTime);
            simpleGallery = itemView.findViewById(R.id.languagesGallery);
            cardView = itemView.findViewById(R.id.galleryCard);
            cardView.setOnClickListener(v -> {
                Log.e("selected Value","is"+Name.getText());
                Intent intent = new Intent(v.getContext(), GalleryViewActivity.class);
                // intent.putExtras();
                Bundle bundle = new Bundle();
                bundle.putString("Title",Name.getText().toString());
                bundle.putStringArrayList("AdaptorList",urls);
                intent.putExtras(bundle);
                v.getContext().startActivity(intent);
            });
        }
    }
}
