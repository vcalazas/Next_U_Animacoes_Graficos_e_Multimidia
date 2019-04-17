package com.example.android.chieftechnologyofficer.extras;

import android.content.Context;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Next University.
 */
public class MyRenderer implements GLSurfaceView.Renderer {

    private Cubo cubo;

    private static float anguloCubo = 0;
    private static float speedCubo = -1.5f;

    public MyRenderer(Context context){
        cubo = new Cubo();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

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
        gl.glRotatef(anguloCubo,1.0f,1.0f,0.0f);

        cubo.desenhar(gl);

        anguloCubo += speedCubo;
    }
}
