package com.example.android.chieftechnologyofficer.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.widget.LinearLayout;

import com.example.android.chieftechnologyofficer.fragments.AnimationFragment;
import com.example.android.chieftechnologyofficer.fragments.AudioFragment;
import com.example.android.chieftechnologyofficer.fragments.GraphFragment;
import com.example.android.chieftechnologyofficer.fragments.ImagesFragment;
import com.example.android.chieftechnologyofficer.fragments.VideoFragment;
import com.example.android.chieftechnologyofficer.interfaces.OnListener;
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
            public <T> void saveSession(Context context, String field, T data) {
            }

            @Override
            public void onCall(int frameLayout, String option,Bundle arguments) {
                try {
                    HomeActivity.this.navigator(frameLayout ,option, arguments);
                } catch (Exception e) {
//                    Logger.write("HomeActivity.startListener // new OnListener().onCall - ", Constants.Verbose.ERROR);
                }
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
            //setTitle(title);
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
