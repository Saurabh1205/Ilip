package com.example.ilip.activities.dashboard.otherLinks.news;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ilip.activities.dashboard.otherLinks.OtherLinkCommonModel;
import com.example.ilip.R;
import com.google.android.material.card.MaterialCardView;
import com.orhanobut.dialogplus.DialogPlus;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptorForNews extends RecyclerView.Adapter<AdaptorForNews.NewsViewHolder>{
    ArrayList<OtherLinkCommonModel> arrayList;
    OtherLinkCommonModel otherLinkCommonModel;
    Context context;

    public AdaptorForNews(ArrayList<OtherLinkCommonModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.otherLinkCommonModel = otherLinkCommonModel;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_news,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, @SuppressLint("RecyclerView") int position) {
    holder.newsDetail.setText(arrayList.get(position).getGalleryItemName());
    holder.newsDescription.setText(arrayList.get(position).getDetailDescription());
    holder.dateAndTime.setText(arrayList.get(position).getGalleryItemDate_time());
    try{
        Picasso.get().load(arrayList.get(position).getImages().get(0))
                .into(holder.newsImage);
    }catch (Exception e){
        e.printStackTrace();
    }
        holder.cardView.setOnClickListener(v -> getPreviewForNEws(v.getContext(),arrayList.get(position).getImages(),arrayList.get(position).getGalleryItemDate_time(),arrayList.get(position).getGalleryItemName(),arrayList.get(position).getDetailDescription()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder{
        MaterialCardView cardView;
        ImageView newsImage;
        TextView newsDetail,newsDescription,dateAndTime;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            newsDetail = itemView.findViewById(R.id.newsName);
            newsDescription = itemView.findViewById(R.id.newsDescription);
            dateAndTime = itemView.findViewById(R.id.newsDateAndTime);
            cardView = itemView.findViewById(R.id.newsCardView);
            newsImage = itemView.findViewById(R.id.newsImage);
        }

    }
    public void getPreviewForNEws(Context context,ArrayList ImageURL,String newsdate,String newsTitle,String description){
        Log.e("resource for Img","=="+ImageURL.get(0).toString());
        final DialogPlus inflate = DialogPlus.newDialog(context)
                .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.layout_for_news_preview))
                .setExpanded(true, WindowManager.LayoutParams.MATCH_PARENT)
                .setGravity(Gravity.BOTTOM)
                .setCancelable(true)
                .create();
        RecyclerView listImageNews = (RecyclerView) inflate.findViewById(R.id.ListImageNews);
        /*ImageView img = (ImageView) inflate.findViewById(R.id.imageNews);
        if(!ImageURL.equals("")) {
            Picasso.get()
                    .load(ImageURL)
                    .into(img);
        }*/
        AdapterforSlideingImages  adapterforSlideingImages = new AdapterforSlideingImages(ImageURL, context.getApplicationContext());
        listImageNews.setLayoutManager(new LinearLayoutManager(context.getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        listImageNews.setAdapter(adapterforSlideingImages);
        TextView textName = (TextView) inflate.findViewById(R.id.newsName);
        TextView textdate = (TextView) inflate.findViewById(R.id.newsDate);
        textdate.setText(newsdate);
        textName.setText(newsTitle);
        TextView textAbout = (TextView) inflate.findViewById(R.id.newsAbout);
        textAbout.setText(description);
        androidx.appcompat.widget.Toolbar toolbar = (Toolbar) inflate.findViewById(R.id.toolbar4NewsPreview);
        toolbar.setTitle(newsTitle);
        toolbar.setNavigationOnClickListener(view -> inflate.dismiss());
        inflate.show();
    }
}
