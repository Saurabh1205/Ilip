package com.example.ilip.activities.attendance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.ilip.activities.attendance.daywise.DayWiseFragment;
import com.example.ilip.activities.attendance.semesterWise.SemesterWiseFragment;
import com.example.ilip.activities.attendance.subjectWise.SubjectWiseFragment;
import com.example.ilip.all_model.CommonModel;
import com.example.ilip.common.ConstantAPIsClass;
import com.example.ilip.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class AttendanceActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    DayWiseFragment dayWiseFragment;
    SubjectWiseFragment subjectWiseFragment;
    SemesterWiseFragment semesterWiseFragment;
    CommonModel commonModel;
    Bundle bundle;

    ConstantAPIsClass constantAPIsClass;
    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        commonModel = (CommonModel) extras.get("CommonModel");
        Log.e("CommonModel", "model data" + commonModel);
        if(commonModel.getLoginType().equals("Student")){
            setTheme(R.style.StudentTheme);
        }else{
            setTheme(R.style.ParentTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        requestQueue = Volley.newRequestQueue(this);
        constantAPIsClass = new ConstantAPIsClass();
//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        commonModel = (CommonModel) extras.get("CommonModel");
//        Log.e("CommonModel", "model data" + commonModel);

        toolbar = findViewById(R.id.toolbarForAttendance);
        toolbar.setTitle("Day wise Attendance");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layoutAttendance);
        if(commonModel.getLoginType().equals("Student")){
            tabLayout.setBackground(getDrawable(R.drawable.tab_selector));
        }else{
            tabLayout.setBackground(getDrawable(R.drawable.tab_selector_parent));
        }

        dayWiseFragment = new DayWiseFragment();
        subjectWiseFragment = new SubjectWiseFragment();
        semesterWiseFragment = new SemesterWiseFragment();
//        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addTab(tabLayout.newTab().setText("Day wise"));
        tabLayout.addTab(tabLayout.newTab().setText("Subject wise"));
        tabLayout.addTab(tabLayout.newTab().setText("Sem wise"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
             @Override
             public void onTabSelected(TabLayout.Tab tab) {
                 viewPager.setCurrentItem(tab.getPosition());
                 toolbar.setTitle(tab.getText()+" Attendance");
             }

             @Override
             public void onTabUnselected(TabLayout.Tab tab) {

             }

             @Override
             public void onTabReselected(TabLayout.Tab tab) {

             }

         });

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPagerAdapter.addFragment(dayWiseFragment,"");
        viewPagerAdapter.addFragment(subjectWiseFragment,"");
        viewPagerAdapter.addFragment(semesterWiseFragment,"");
        viewPager.setAdapter(viewPagerAdapter);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        //set the icons
//        tabLayout.getTabAt(0).setIcon(R.drawable.android);
//        tabLayout.getTabAt(1).setIcon(R.drawable.google_play);
//        tabLayout.getTabAt(2).setIcon(R.drawable.heart);
        //set the badge
//        BadgeDrawable badgeDrawable = tabLayout.getTabAt(0).getOrCreateBadge();
//        badgeDrawable.setVisible(true);
//        badgeDrawable.setNumber(5);

        bundle = new Bundle();
        bundle.putSerializable("CommonModel",commonModel);
        dayWiseFragment.setArguments(bundle);
        semesterWiseFragment.setArguments(bundle);
        subjectWiseFragment.setArguments(bundle);
    }
    public static class ViewPagerAdapter extends FragmentStateAdapter {
        private final List<Fragment> fragments = new ArrayList<>();
        private final List<String> fragmentTitles = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        //add fragment to the viewpager
        public void addFragment(Fragment fragment, String title){
            fragments.add(fragment);
            fragmentTitles.add(title);
        }
//        @NonNull
//        @Override
//        public Fragment getItem(int position) {
//            return fragments.get(position);
//        }
//        @Override
//        public int getCount() {
//            return fragments.size();
//        }
//        //to setup title of the tab layout
//        @Nullable
//        @Override
//        public CharSequence getPageTitle(int position) {
//            return fragmentTitles.get(position);
//        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }

        @Override
        public int getItemCount() {
            return fragments.size();
        }
    }

}