package com.example.ilip.activities.dashboard.otherLinks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.ilip.activities.dashboard.otherLinks.events.EventsActivity;
import com.example.ilip.activities.dashboard.otherLinks.gallery.GalleryActivity;
import com.example.ilip.activities.dashboard.otherLinks.news.NewsActivity;
import com.example.ilip.all_model.CommonModel;
import com.example.ilip.R;
import com.google.android.material.card.MaterialCardView;

public class OtherLinksActivity extends AppCompatActivity implements View.OnClickListener {
    CommonModel commonModel;
    MaterialCardView news,events,gallery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_links);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        commonModel = (CommonModel) extras.get("CommonModel");
        Log.e("CommonModel", "model data" + commonModel);
        Toolbar toolbar = findViewById(R.id.toolbar4OtherImpLinks);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());
        news = findViewById(R.id.News);
        events = findViewById(R.id.Events);
        gallery = findViewById(R.id.Gallery);
         news.setOnClickListener(this);
         events.setOnClickListener(this);
         gallery.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.News){
            Intent intent = new Intent(getApplicationContext(), NewsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("CommonModel", commonModel);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if(id == R.id.Events){
            Intent intent = new Intent(getApplicationContext(), EventsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("CommonModel", commonModel);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if(id == R.id.Gallery){
            Intent intent = new Intent(getApplicationContext(), GalleryActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("CommonModel", commonModel);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}