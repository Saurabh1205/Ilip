package com.example.ilip.activities.dashboard.otherLinks.gallery;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.ilip.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomizedGalleryAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<String> images;

    public CustomizedGalleryAdapter(Context context, ArrayList<String> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v =convertView;
        if (v == null) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_for_gallery_image, null);
        }
        ImageView imageView = v.findViewById(R.id.album);

        // set image in ImageView
        try{
            Picasso.get()
                    .load(images.get(position))
                    .into(imageView);
        }catch (Exception e){
            Log.e("Error image","is=="+e);
        }
       // imageView.setImageResource(images[position]);

        // set ImageView param
        return v;
    }
}
