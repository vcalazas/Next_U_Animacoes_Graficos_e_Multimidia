package com.example.android.chieftechnologyofficer.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.chieftechnologyofficer.R;
import com.example.android.chieftechnologyofficer.interfaces.OnListener;

import java.io.File;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImagesFragment extends Fragment {
    public static final String FRAGMENT_NAME = "ImagesFragment";
    private static final int REQUEST_CODE_FOR_GALERY =1;
    private static final int REQUEST_CODE_FOR_CAMERA =2;
    private OnListener mListener;
    private ImageView imageView3;
    private TextView textView2;

    public ImagesFragment() {
        // Required empty public constructor
    }

    public void setmListener(OnListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_images, container, false);

        Button button = view.findViewById(R.id.button);
        Button button2 = view.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,2);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    File imagemFolder = new File(Environment.getExternalStorageDirectory(),"CameraFolder");

                    imagemFolder.mkdirs();

                    File imagem = new File(imagemFolder, "foto.jpg");

                    Uri uriImagem = Uri.fromFile(imagem);

                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,uriImagem);

                    getActivity().startActivityForResult(cameraIntent, REQUEST_CODE_FOR_CAMERA);
                } catch (Exception e){
                    Toast.makeText(getContext(), "Falha ao abrie a camera", Toast.LENGTH_LONG).show();
                }
            }
        });

        imageView3 = view.findViewById(R.id.imageView3);
        textView2 = view.findViewById(R.id.textView2);

        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent Data){
        super.onActivityResult(requestCode,resultCode,Data);
        try {
            if (requestCode == 2 && resultCode == RESULT_OK && null !=Data){
                Bitmap bitmap = null;
                Uri imagemescolhido = Data.getData();
                String[] path = {MediaStore.Images.Media.DATA};
                Cursor cursor = getActivity().getContentResolver().query(imagemescolhido,path,null,null,null);
                cursor.moveToFirst();
                int coluna = cursor.getColumnIndex(path[0]);
                String pathimagem = cursor.getString(coluna);
                textView2.setText(pathimagem);
                cursor.close();
                bitmap = BitmapFactory.decodeFile(pathimagem);
                int height = bitmap.getHeight();
                int width = bitmap.getWidth();
                float scaleA = ((float)(width/2))/width;
                float scaleB = ((float)(height/2))/height;
                Matrix matrix = new Matrix();
                matrix.postScale(scaleA,scaleB);
                Bitmap novaimagem = Bitmap.createBitmap(bitmap,0,0,width,height,matrix,true);
                imageView3.setImageBitmap(novaimagem);
            } else if (requestCode == REQUEST_CODE_FOR_CAMERA && resultCode == RESULT_OK){
                Toast.makeText(getContext(), "a imagem foi salva:\n"+ Environment.getExternalStorageDirectory()+ "/CameraFolder/foto.jpg", Toast.LENGTH_LONG).show();

                Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/CameraFolder/foto.jpg");

                int height = bitmap.getHeight();
                int width = bitmap.getWidth();

                float scaleA = ((float)(width/2))/width;
                float scaleB = ((float)(height))/height;

                Matrix matrix = new Matrix();
                matrix.postScale(scaleA,scaleB);

                Bitmap novaImagem = Bitmap.createBitmap(bitmap,0,0,width,height,matrix,true);

                imageView3.setImageBitmap(novaImagem);

            }else {
                Toast.makeText(getContext(), "a imagem não foi salva corretamente no dispositivo", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e){
            textView2.setText(getActivity().getString(R.string.nenhum_arquivo_selecionado));
        }
    }

}
