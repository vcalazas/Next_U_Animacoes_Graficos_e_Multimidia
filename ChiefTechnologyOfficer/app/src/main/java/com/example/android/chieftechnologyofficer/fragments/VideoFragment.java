package com.example.android.chieftechnologyofficer.fragments;


import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

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
public class VideoFragment extends Fragment {
    public static final String FRAGMENT_NAME = "VideoFragment";
    private OnListener mListener;
    private static final int REQUEST_CODE_FOR_CAMERA= 1;
    private static final int REQUEST_CODE_FOR_GALERY = 2;
    private String videoFileName, currentVideoPath;

    private Button button, button2;
    private VideoView videoView;
    private TextView textView;

    public VideoFragment() {
        // Required empty public constructor
    }

    public void setmListener(OnListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_video, container, false);

        button      =  view.findViewById(R.id.button);
        button2     =  view.findViewById(R.id.button2);
        videoView   = view.findViewById(R.id.videoView);
        textView    = view.findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Escolha um vídeo"), REQUEST_CODE_FOR_GALERY);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    dispatchTakeVideoIntent();
                } catch (Exception e){
                    Toast.makeText(getContext(), "Falha ao abrir a camera", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_FOR_GALERY) {
            try {
                String patch = data.getDataString();
                MediaController mediaController = new MediaController(getActivity());
                videoView.setMediaController(mediaController);
                videoView.setVideoURI(Uri.parse(patch));
                videoView.start();
                textView.setText(patch);
            } catch (Exception e) {
                Toast.makeText(getActivity(), "erro ao executar o áudio", Toast.LENGTH_SHORT).show();
            }
        } else if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_FOR_CAMERA) {
            Toast.makeText(getContext(), "O vídeo foi salvo em:\n"+ Environment.getExternalStorageDirectory() + "/VideosNextU/video.mp4", Toast.LENGTH_LONG).show();
            Uri videoEscolhido = data.getData();
            MediaController mediaController = new MediaController(getContext());
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(videoEscolhido);
            videoView.start();
            textView.setText(currentVideoPath);

            mediaController.setAnchorView(videoView);
        }
    }


    private void dispatchTakeVideoIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File videoFile = null;
            try {
                videoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(getContext(), "Falha ao abrir a camera", Toast.LENGTH_LONG).show();
            }
            // Continue only if the File was successfully created
            if (videoFile != null) {
                Uri videoURI = FileProvider.getUriForFile(getContext(),
                        "com.example.android.fileprovider",
                        videoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, videoURI);

                startActivityForResult(takePictureIntent, REQUEST_CODE_FOR_CAMERA);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        videoFileName = "VIDEO_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                videoFileName,  /* prefix */
                ".mp4",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentVideoPath = image.getAbsolutePath();
        return image;
    }

}
