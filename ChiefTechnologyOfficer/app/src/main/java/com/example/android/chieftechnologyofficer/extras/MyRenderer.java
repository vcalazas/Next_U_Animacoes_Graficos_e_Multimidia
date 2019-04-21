package com.example.android.chieftechnologyofficer.extras;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.widget.SeekBar;

import java.util.List;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyRenderer implements GLSurfaceView.Renderer {

    private Cube cubo;
    private Context context;

    private static float anguloCubo1 = 0;
    private static float anguloCubo2 = 0;
    private static float anguloCubo3 = 0;
    private static float anguloCubo4 = 0;
    private static float speedCubo1 = 1.0f;
    private static float speedCubo2 = 1.0f;
    private static float speedCubo3 = 1.0f;
    private static float speedCubo4 = 1.0f;

    public MyRenderer(Context context, List<SeekBar> seekBarChangeListeners){
        this.context = context;
        cubo = new Cube();

        seekBarChangeListeners.get(0).setProgress(10);
        seekBarChangeListeners.get(1).setProgress(10);
        seekBarChangeListeners.get(2).setProgress(10);
        seekBarChangeListeners.get(3).setProgress(10);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        cubo.carregarTextura(gl,context);
        gl.glEnable(GL10.GL_TEXTURE_2D);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, -0.5f);
        gl.glScalef(0.8f, 0.8f, 0.8f);
        gl.glRotatef(anguloCubo1,anguloCubo2,anguloCubo3,anguloCubo4);

        cubo.desenhar(gl);
        anguloCubo1 += speedCubo1;
        anguloCubo2 = speedCubo2;
        anguloCubo3 = speedCubo3;
        anguloCubo4 = speedCubo4;
    }

    public void changeAnimationSpeed(float speed){ speedCubo1 = speed; }
    public void changeAnimationSpeed2(float speed){ speedCubo2 = speed; }
    public void changeAnimationSpeed3(float speed){ speedCubo3 = speed; }
    public void changeAnimationSpeed4(float speed){ speedCubo4 = speed; }
}
