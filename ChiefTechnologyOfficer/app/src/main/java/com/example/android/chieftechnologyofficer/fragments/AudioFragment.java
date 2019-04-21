package com.example.android.chieftechnologyofficer.fragments;


import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.chieftechnologyofficer.R;
import com.example.android.chieftechnologyofficer.interfaces.OnListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class AudioFragment extends Fragment {
    public static final String FRAGMENT_NAME = "AudioFragment";
    private OnListener mListener;

    private Button button;
    private TextView textView;

    private boolean theMusicIsPlaying;
    private static final int Pick_song = 1;
    private MediaPlayer audio;

    private static String nomeAudio = null;
    private MediaRecorder mediaRecorder = null;
    boolean IsTheMicrophoneRecording = false;

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
        View view =  inflater.inflate(R.layout.fragment_audio, container, false);

        textView = view.findViewById(R.id.textView);
        button   = view.findViewById(R.id.button);
        Button button1  = view.findViewById(R.id.button1);
        Button button2  = view.findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(audio != null){
                    if(theMusicIsPlaying){
                        audio.pause();
                        ( (Button) view).setText(getActivity().getResources().getString(R.string.reproduzir_udio));
                    } else {
                        audio.start();
                        ( (Button) view).setText(getActivity().getResources().getString(R.string.parar_udio));
                    }
                    theMusicIsPlaying = !theMusicIsPlaying;
                }
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("audio/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Escolha um audio"),Pick_song);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsTheMicrophoneRecording){
                    pararGravacao();
                    ( (Button) view).setText("Iniciar Gravação");
                }else{
                    comecarGravacao();
                    ( (Button) view).setText("Pare a gravação");
                }
                IsTheMicrophoneRecording = !IsTheMicrophoneRecording;
            }
        });

        return view;
    }

    private void setListener(){
        audio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                button.setText(getActivity().getResources().getString(R.string.reproduzir_udio));
                IsTheMicrophoneRecording = !IsTheMicrophoneRecording;
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case Pick_song:
                if (resultCode ==RESULT_OK) {
                    String patch = data.getDataString();
                    try{
                        textView.setText(patch);
                        audio = new MediaPlayer();
                        audio.setDataSource(getActivity().getApplicationContext(), Uri.parse(patch));
                        audio.prepare();
                        setListener();
                    }catch (Exception e){
                        Toast.makeText(getActivity(), "erro ao executar o áudio", Toast.LENGTH_SHORT).show();
                    }
                }
        }
    }

    private void pararGravacao(){
        try {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;

            audio = new MediaPlayer();
            audio.setDataSource(getActivity().getApplicationContext(), Uri.parse(nomeAudio));
            audio.prepare();
            setListener();
            textView.setText(nomeAudio);
            Toast.makeText(getContext(), "O áudio foi salvo em:\n" + nomeAudio, Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(getContext(), "ocorreu um erro na gravação", Toast.LENGTH_SHORT).show();
        }
    }

    private void comecarGravacao(){
        try{
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_MUSIC);
            File image = File.createTempFile(
                    "AUDIO_"+timeStamp,  /* prefix */
                    ".3gp",         /* suffix */
                    storageDir      /* directory */
            );
            nomeAudio = image.getAbsolutePath();
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setOutputFile(nomeAudio);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            mediaRecorder.prepare();

            mediaRecorder.start();
        }catch(Exception e){
            Toast.makeText(getContext(), "Não será gravado corretamente", Toast.LENGTH_SHORT).show();
        }
    }



}
