package com.example.android.chieftechnologyofficer.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.chieftechnologyofficer.R;
import com.example.android.chieftechnologyofficer.interfaces.OnListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class AudioFragment extends Fragment {
    public static final String FRAGMENT_NAME = "AudioFragment";
    private OnListener mListener;

    public AudioFragment() {
        // Required empty public constructor
    }

    public void setmListener(OnListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_audio, container, false);
    }

}
