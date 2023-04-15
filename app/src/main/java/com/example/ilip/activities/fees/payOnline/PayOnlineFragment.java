package com.example.ilip.activities.fees.payOnline;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ilip.all_model.CommonModel;
import com.example.ilip.R;


public class PayOnlineFragment extends Fragment {
CommonModel commonModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras;
        extras = this.getArguments();
        if (extras != null) {
            commonModel = (CommonModel) extras.get("CommonModel");
        }
        Log.e("FramePayable", "Data" + commonModel);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pay_online, container, false);
        return view;
    }
}