package com.example.android.chieftechnologyofficer.extras;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Next U.
 */
public class Cubo {

    private FloatBuffer vertexBuffer;

    private int numCaras = 6;

    private float[][] colors = {
            {0.0f, 0.5f, 0.0f, 1.0f},
            {0.5f, 0.0f, 1.0f, 1.0f},
            {0.0f, 1.0f, 0.0f, 1.0f},
            {1.0f, 0.0f, 1.0f, 1.0f},
            {1.0f, 0.0f, 0.0f, 1.0f},
            {1.0f, 1.0f, 0.0f, 1.0f}
    };

    private float[] vertices = {
            -0.5f, -0.5f,  0.5f,
            0.5f, -0.5f,  0.5f,
            -0.5f,  0.5f,  0.5f,
            0.5f,  0.5f,  0.5f,

            0.5f, -0.5f, -0.5f,
            -0.5f, -0.5f, -0.5f,
            0.5f,  0.5f, -0.5f,
            -0.5f,  0.5f, -0.5f,

            -0.5f, -0.5f, -0.5f,
            -0.5f, -0.5f,  0.5f,
            -0.5f,  0.5f, -0.5f,
            -0.5f,  0.5f,  0.5f,

            0.5f, -0.5f,  0.5f,
            0.5f, -0.5f, -0.5f,
            0.5f,  0.5f,  0.5f,
            0.5f,  0.5f, -0.5f,

            -0.5f,  0.5f,  0.5f,
            0.5f,  0.5f,  0.5f,
            -0.5f,  0.5f, -0.5f,
            0.5f,  0.5f, -0.5f,

            -0.5f, -0.5f, -0.5f,
            0.5f, -0.5f, -0.5f,
            -0.5f, -0.5f,  0.5f,
            0.5f, -0.5f,  0.5f
    };

    public Cubo(){
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
        vbb.order(ByteOrder.nativeOrder());

        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);
    }

    public void desenhar(GL10 gl){
        gl.glFrontFace(GL10.GL_CCW);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);

        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

        for (int cara = 0 ; cara < numCaras; cara++){
            gl.glColor4f(colors[cara][0],colors[cara][1], colors[cara][2],colors[cara][3]);
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, cara*4, 4);
        }

        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisable(GL10.GL_CULL_FACE);
    }

}
