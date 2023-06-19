package com.gbsw.hs.kr.dormitory_management_mobile.fragment;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gbsw.hs.kr.dormitory_management_mobile.MainActivity;
import com.gbsw.hs.kr.dormitory_management_mobile.R;
import com.gbsw.hs.kr.dormitory_management_mobile.service.PreferenceManager;

public class my_info_fragment extends Fragment {
    Button logout_btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_my_info, container, false);

        logout_btn = v.findViewById(R.id.logout_btn);

        logout_btn.setOnClickListener(view -> {
            PreferenceManager.removeKey(v.getContext(), "token");

            Log.i("logout attach", "logout");
            getActivity().finishAffinity();
            Intent intent = new Intent(v.getContext(), MainActivity.class);
            startActivity(intent);
        });


        return v;
    }
}