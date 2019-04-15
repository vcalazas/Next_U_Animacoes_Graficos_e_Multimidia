package com.example.android.chieftechnologyofficer.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

public class MyPermissions {

    private static final int REQUEST_CODE = 1;

    public static void check(Activity activity, String[] permissionsToCheck){
        for(int i = 0; i < permissionsToCheck.length; i++){
            int permission = ActivityCompat.checkSelfPermission(activity, permissionsToCheck[i]);
            if(permission == PackageManager.PERMISSION_DENIED){
                ActivityCompat.requestPermissions(activity, permissionsToCheck, REQUEST_CODE);
            }
        }
    }

}
