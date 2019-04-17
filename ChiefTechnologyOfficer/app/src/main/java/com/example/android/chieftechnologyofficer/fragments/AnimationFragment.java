package com.example.android.chieftechnologyofficer.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.android.chieftechnologyofficer.R;
import com.example.android.chieftechnologyofficer.interfaces.OnListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnimationFragment extends Fragment {
    public static final String FRAGMENT_NAME = "AnimationFragment";

    private ImageView imageView;
    private Animation animation;

    private OnListener mListener;

    public AnimationFragment() {
        // Required empty public constructor
    }

    public void setmListener(OnListener listener){
        AnimationFragment.this.mListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_animation, container, false);

        imageView = view.findViewById(R.id.imageView);
        FloatingActionButton floatingActionButton2 = view.findViewById(R.id.floatingActionButton2);
        FloatingActionButton floatingActionButton3 = view.findViewById(R.id.floatingActionButton3);

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animation = AnimationUtils.loadAnimation(getActivity(), R.anim.fadein);
                imageView.setAnimation(animation);
            }
        });

        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.clearAnimation();
            }
        });

        return view;
    }

}
