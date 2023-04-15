package com.example.ilip.activities.dashboard.otherLinks.news;

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

public class AdapterforSlideingImages extends RecyclerView.Adapter<AdapterforSlideingImages.ClassViewHolder> {
    ArrayList arrayList;
    Context context;

    public AdapterforSlideingImages(ArrayList arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_news_images,parent,false);
        return new ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        try{
            Log.e("arrya list","=="+arrayList.get(position).toString());
            Picasso.get().load(arrayList.get(position).toString()).into(holder.newsImage);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ClassViewHolder extends RecyclerView.ViewHolder{
        ImageView newsImage;
        public ClassViewHolder(@NonNull View itemView) {
            super(itemView);
            newsImage = itemView.findViewById(R.id.imageNews);

        }

    }
}
