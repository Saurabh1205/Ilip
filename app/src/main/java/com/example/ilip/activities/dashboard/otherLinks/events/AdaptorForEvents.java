package com.example.ilip.activities.dashboard.otherLinks.events;

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

public class AdaptorForEvents extends RecyclerView.Adapter<AdaptorForEvents.EventsViewHolder> {
    ArrayList<OtherLinkCommonModel> arrayList;
    Context context;

    public AdaptorForEvents(ArrayList<OtherLinkCommonModel> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_events,parent,false);
        return new EventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsViewHolder holder, @SuppressLint("RecyclerView") int position) {
    holder.eventDetails.setText(arrayList.get(position).getGalleryItemName());
    holder.location.setText(arrayList.get(position).getLocation());
    holder.dateAndTime.setText(arrayList.get(position).getGalleryItemDate_time());
    try{
        Picasso.get().load(arrayList.get(position).getImages().get(0)).into(holder.EventImg);
    }catch (Exception e){
        e.printStackTrace();
    }
    holder.cardView.setTag(position);
        holder.cardView.setOnClickListener(v -> getPreviewForEvents(v.getContext(),arrayList.get(position).getImages(),arrayList.get(position).getGalleryItemDate_time()
                ,arrayList.get(position).getGalleryItemName(),arrayList.get(position).getLocation(),arrayList.get(position).getDetailDescription()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class EventsViewHolder extends RecyclerView.ViewHolder{
        MaterialCardView cardView;
        ImageView EventImg;
        TextView eventDetails,location,dateAndTime;
      public EventsViewHolder(@NonNull View itemView) {
          super(itemView);
          EventImg = itemView.findViewById(R.id.eventImg);
          eventDetails = itemView.findViewById(R.id.eventDetails);
          location = itemView.findViewById(R.id.eventLocation);
          dateAndTime = itemView.findViewById(R.id.eventDateTime);
          cardView = itemView.findViewById(R.id.cardEvent);
      }
  }
    public void getPreviewForEvents(Context context,ArrayList ImageURL,String newsdate,String newsTitle,String location,String About){
        Log.e("resource for Img","=="+ImageURL.get(0));
        final DialogPlus inflate = DialogPlus.newDialog(context)
                .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.layout_for_events_preview))
                .setExpanded(true, WindowManager.LayoutParams.MATCH_PARENT)
                .setGravity(Gravity.BOTTOM)
                .setCancelable(true)
                .create();
        RecyclerView imageListEvents = (RecyclerView) inflate.findViewById(R.id.imageListEvent);
//        ImageView img = (ImageView) inflate.findViewById(R.id.imageEvent);
//        if(!ImageURL.equals("")) {
//            Picasso.get()
//                    .load(ImageURL.get(0).toString())
//                    .into(img);
//        }
        AdaptorForEventSlideImages adapterforSlideingImages = new AdaptorForEventSlideImages(ImageURL, context.getApplicationContext());
        imageListEvents.setLayoutManager(new LinearLayoutManager(context.getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        imageListEvents.setAdapter(adapterforSlideingImages);
        TextView textlocation = (TextView) inflate.findViewById(R.id.location);
        TextView textName = (TextView) inflate.findViewById(R.id.titleText);
        TextView textdate = (TextView) inflate.findViewById(R.id.date);
        textlocation.setText(location);
        textdate.setText(newsdate);
        textName.setText(newsTitle);
        TextView textAbout = (TextView) inflate.findViewById(R.id.eventDescription);
        textAbout.setText(About);
        androidx.appcompat.widget.Toolbar toolbar = (Toolbar) inflate.findViewById(R.id.toolbar4EventsPreview);
        toolbar.setTitle(newsTitle);
        toolbar.setNavigationOnClickListener(view -> inflate.dismiss());
        inflate.show();
    }
}
