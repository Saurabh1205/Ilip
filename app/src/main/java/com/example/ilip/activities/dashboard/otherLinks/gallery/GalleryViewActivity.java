package com.example.ilip.activities.dashboard.otherLinks.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Gallery;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.ilip.all_model.CommonModel;
import com.example.ilip.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class GalleryViewActivity extends AppCompatActivity {
    CommonModel commonModel;
    ImageView previewImage;
    Gallery gallery;
    ArrayList<String> urls = new ArrayList<>();
    CustomizedGalleryAdapter customizedGalleryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_view);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String title = extras.getString("Title");
        String data = extras.getString("SelectedStringArray");
        try {
            JSONArray arrayList1 = new JSONArray(data);
            Log.e("Json Array data", "is==="+arrayList1);
            urls = new ArrayList();
            for (int j = 0; j < arrayList1.length(); j++) {
                JSONObject jsonObj = (JSONObject) arrayList1.get(j);
                urls.add(jsonObj.getString("ATTACHMENT_LIVE_PATH"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Toolbar toolbar = findViewById(R.id.toolbar4GalleryView);
        toolbar.setTitle(title);

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        previewImage = findViewById(R.id.previewImage);
        try{
            Picasso.get()
                    .load(urls.get(0))
                    .into(previewImage);
        }catch (Exception e){
            Log.e("Error image","is=="+e);
        }
        gallery = findViewById(R.id.PreviewGallery);

        customizedGalleryAdapter = new CustomizedGalleryAdapter(getApplicationContext(),urls);
        gallery.setAdapter(customizedGalleryAdapter);

        gallery.setOnItemClickListener((parent, view, position, id) -> {
            try{
                Picasso.get()
                        .load(urls.get(position))
                        .into(previewImage);
            }catch (Exception e){
                Log.e("Error image","is=="+e);
            }
        });
    }
}