package com.gbsw.hs.kr.dormitory_management_mobile.fragment;

import static com.gbsw.hs.kr.dormitory_management_mobile.R.*;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gbsw.hs.kr.dormitory_management_mobile.service.CardService;

public class student_card_fragment extends Fragment {


    private AnimatorSet front_anim;
    private AnimatorSet back_anim;
    Intent _cardService;

    boolean is_front = true;

    public student_card_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        // Inflate the layout for this fragment
        View v = inflater.inflate(layout.fragment_student_card, container, false);
        float scale = getResources().getDisplayMetrics().density;

        ImageView front = v.findViewById(id.card_front);
        ImageView back = v.findViewById(id.card_back);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ConstraintLayout stu_card = v.findViewById(id.student_card);

        front.setCameraDistance(8000 * scale);
        back.setCameraDistance(8000 * scale);

        AnimatorSet front_animation = (AnimatorSet) AnimatorInflater.loadAnimator(requireActivity().getApplicationContext(), animator.front_animator);
        AnimatorSet back_animation = (AnimatorSet) AnimatorInflater.loadAnimator(requireActivity().getApplicationContext(), animator.back_animator);

        // NFC start
        _cardService = new Intent(requireActivity(), CardService.class);
        requireActivity().startService(_cardService);


        stu_card.setOnClickListener(view -> {
            if(is_front) {
                front_animation.setTarget(front);
                back_animation.setTarget(back);
                front_animation.start();
                back_animation.start();

                is_front = false;
            } else {
                front_animation.setTarget(back);
                back_animation.setTarget(front);
                back_animation.start();
                front_animation.start();

                is_front = true;
            }

//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
        });

        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        requireActivity().stopService(_cardService);
    }
}