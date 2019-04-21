package com.example.android.chieftechnologyofficer.fragments;


import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.example.android.chieftechnologyofficer.R;
import com.example.android.chieftechnologyofficer.extras.MyRenderer;
import com.example.android.chieftechnologyofficer.interfaces.OnListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GraphFragment extends Fragment {
    public static final String FRAGMENT_NAME = "GraphFragment";
    private OnListener mListener;
    private GLSurfaceView Tela;
    private MyRenderer myRenderer;

    private List<SeekBar.OnSeekBarChangeListener> onSeekBarChangeListeners = new ArrayList<SeekBar.OnSeekBarChangeListener>();

    public GraphFragment() {
        // Required empty public constructor
    }

    public void setmListener(OnListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_graph, container, false);
        LinearLayout linearLayout = view.findViewById(R.id.linearLayout);
        SeekBar seekBar = view.findViewById(R.id.seekBar);
        SeekBar seekBar2 = view.findViewById(R.id.seekBar2);
        SeekBar seekBar3 = view.findViewById(R.id.seekBar3);
        SeekBar seekBar4 = view.findViewById(R.id.seekBar4);

        List<SeekBar> seekBars = new ArrayList<SeekBar>();

        seekBars.add(seekBar);
        seekBars.add(seekBar2);
        seekBars.add(seekBar3);
        seekBars.add(seekBar4);

        myRenderer = new MyRenderer(getContext(), seekBars);
        configureSeeks();


        seekBar.setOnSeekBarChangeListener(onSeekBarChangeListeners.get(0));
        seekBar2.setOnSeekBarChangeListener(onSeekBarChangeListeners.get(1));
        seekBar3.setOnSeekBarChangeListener(onSeekBarChangeListeners.get(2));
        seekBar4.setOnSeekBarChangeListener(onSeekBarChangeListeners.get(3));

        Tela = new GLSurfaceView(getContext());
        Tela.setRenderer(myRenderer);

        linearLayout.addView(Tela);
        return view;
    }

    private void configureSeeks (){
        onSeekBarChangeListeners.add(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                myRenderer.changeAnimationSpeed(
                        Float.parseFloat( (progress / 10)+"" )
                );
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        onSeekBarChangeListeners.add(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                myRenderer.changeAnimationSpeed2(
                        Float.parseFloat( (progress / 10)+"" )
                );
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        onSeekBarChangeListeners.add(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                myRenderer.changeAnimationSpeed3(
                        Float.parseFloat( (progress / 10)+"" )
                );
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        onSeekBarChangeListeners.add(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                myRenderer.changeAnimationSpeed4(
                        Float.parseFloat( (progress / 10)+"" )
                );
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

}
