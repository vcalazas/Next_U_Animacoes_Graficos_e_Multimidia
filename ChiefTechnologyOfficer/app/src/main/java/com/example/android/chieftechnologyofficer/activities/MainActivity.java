package com.example.android.chieftechnologyofficer.activities;

import android.Manifest;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.android.chieftechnologyofficer.R;
import com.example.android.chieftechnologyofficer.extras.MyRenderer;
import com.example.android.chieftechnologyofficer.utils.MyPermissions;

public class MainActivity extends AppCompatActivity {


    private View linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        linearLayout = findViewById(R.id.linearLayout);
        linearLayout.setAnimation(animation);
    }
}
