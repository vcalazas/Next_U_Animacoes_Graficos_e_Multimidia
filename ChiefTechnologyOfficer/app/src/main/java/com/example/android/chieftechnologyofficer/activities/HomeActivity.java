package com.example.android.chieftechnologyofficer.activities;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.android.chieftechnologyofficer.fragments.AnimationFragment;
import com.example.android.chieftechnologyofficer.fragments.AudioFragment;
import com.example.android.chieftechnologyofficer.fragments.GraphFragment;
import com.example.android.chieftechnologyofficer.fragments.ImagesFragment;
import com.example.android.chieftechnologyofficer.fragments.VideoFragment;
import com.example.android.chieftechnologyofficer.interfaces.OnListener;
import com.example.android.chieftechnologyofficer.utils.MyPermissions;
import com.transitionseverywhere.Rotate;
import com.transitionseverywhere.TransitionManager;

import com.example.android.chieftechnologyofficer.R;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private OnListener mListener;
    private LinearLayout mainActMainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        startListener();
        mainActMainLayout = findViewById(R.id.mainActMainLayout);
        navigator(R.id.fragment_content, AnimationFragment.FRAGMENT_NAME, null);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){

            case R.id.nav_animations:
                navigator(R.id.fragment_content, AnimationFragment.FRAGMENT_NAME, null);
                break;

            case R.id.nav_audio:
                navigator(R.id.fragment_content, AudioFragment.FRAGMENT_NAME, null);
                break;

            case R.id.nav_graphs:
                navigator(R.id.fragment_content, GraphFragment.FRAGMENT_NAME, null);
                break;

            case R.id.nav_images:
                navigator(R.id.fragment_content, ImagesFragment.FRAGMENT_NAME, null);
                break;

            case R.id.nav_video:
                navigator(R.id.fragment_content, VideoFragment.FRAGMENT_NAME, null);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void startListener() {
        HomeActivity.this.mListener = new OnListener() {

            @Override
            public void onCall(int frameLayout, String option,Bundle arguments) {
                try {
                    HomeActivity.this.navigator(frameLayout ,option, arguments);
                } catch (Exception ignore) { }
            }
        };
    }

    private void navigator(int frameLayout , String way, Bundle arguments){
        try {

            switch (way) {
                case AnimationFragment.FRAGMENT_NAME:
                    HomeActivity.this.goAnimations(frameLayout, arguments);
                    break;

                case AudioFragment.FRAGMENT_NAME:
                    HomeActivity.this.goAudio(frameLayout, arguments);
                    break;

                case GraphFragment.FRAGMENT_NAME:
                    HomeActivity.this.goGraph(frameLayout, arguments);
                    break;

                case ImagesFragment.FRAGMENT_NAME:
                    HomeActivity.this.goImages(frameLayout, arguments);
                    break;

                case VideoFragment.FRAGMENT_NAME:
                    HomeActivity.this.goVideo(frameLayout, arguments);
                    break;

            }
        } catch (Exception e){

        }
    }

    private void navigationFragment(int frameLayout, Fragment fragment, String title) {
        try {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(frameLayout, fragment);
            fragmentTransaction.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            HomeActivity.this.setTitle(title);
            fragmentTransaction.commit();
        } catch (Exception e) {

        }
    }


    private void goAnimations(int frameLayout, Bundle arguments){
        try {
            AnimationFragment animationFragment = new AnimationFragment();
            animationFragment.setmListener(HomeActivity.this.mListener);
            if( arguments != null ){
                animationFragment.setArguments(arguments);
            }
            HomeActivity.this.navigationFragment(frameLayout ,animationFragment, getString(R.string.anima_es));
            TransitionManager.beginDelayedTransition(mainActMainLayout, new Rotate());
        } catch (Exception e){

        }
    }

    private void goAudio(int frameLayout, Bundle arguments){
        try {
            final String[] PERMISSOES={
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.RECORD_AUDIO
            };

            MyPermissions.check(HomeActivity.this, PERMISSOES);

            AudioFragment audioFragment = new AudioFragment();
            audioFragment.setmListener(HomeActivity.this.mListener);
            if( arguments != null ){
                audioFragment.setArguments(arguments);
            }
            HomeActivity.this.navigationFragment(frameLayout ,audioFragment, getString(R.string.audio));
            TransitionManager.beginDelayedTransition(mainActMainLayout, new Rotate());
        } catch (Exception e){

        }
    }

    private void goGraph(int frameLayout, Bundle arguments){
        try {
            GraphFragment graphFragment = new GraphFragment();
            graphFragment.setmListener(HomeActivity.this.mListener);
            if( arguments != null ){
                graphFragment.setArguments(arguments);
            }
            HomeActivity.this.navigationFragment(frameLayout ,graphFragment, getString(R.string.gr_ficos));
            TransitionManager.beginDelayedTransition(mainActMainLayout, new Rotate());
        } catch (Exception e){

        }
    }

    private void goImages(int frameLayout, Bundle arguments){
        try {
            final String[] PERMISSOES={
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA
            };

            MyPermissions.check(HomeActivity.this, PERMISSOES);

            ImagesFragment imagesFragment = new ImagesFragment();
            imagesFragment.setmListener(HomeActivity.this.mListener);
            if( arguments != null ){
                imagesFragment.setArguments(arguments);
            }
            HomeActivity.this.navigationFragment(frameLayout ,imagesFragment, getString(R.string.imagens));
            TransitionManager.beginDelayedTransition(mainActMainLayout, new Rotate());
        } catch (Exception e){

        }
    }

    private void goVideo(int frameLayout, Bundle arguments){
        try {
            final String[] PERMISSOES={
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA
            };

            MyPermissions.check(HomeActivity.this, PERMISSOES);

            VideoFragment videoFragment = new VideoFragment();
            videoFragment.setmListener(HomeActivity.this.mListener);
            if( arguments != null ){
                videoFragment.setArguments(arguments);
            }
            HomeActivity.this.navigationFragment(frameLayout ,videoFragment, getString(R.string.v_deos));
            TransitionManager.beginDelayedTransition(mainActMainLayout, new Rotate());
        } catch (Exception e){

        }
    }


}
