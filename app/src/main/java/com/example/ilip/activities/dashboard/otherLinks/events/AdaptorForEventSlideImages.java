package com.example.ilip.activities.dashboard.otherLinks.events;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ilip.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptorForEventSlideImages extends RecyclerView.Adapter<AdaptorForEventSlideImages.EventimageViewHolder> {
    ArrayList arrayList;
    Context context;

    public AdaptorForEventSlideImages(ArrayList arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public EventimageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_event_images,parent,false);
        return new EventimageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventimageViewHolder holder, int position) {
        try{
            Log.e("arrya list","=="+arrayList.get(position).toString());
            Picasso.get().load(arrayList.get(position).toString()).into(holder.image);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class EventimageViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        public EventimageViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageEvent);
        }
    }
}
