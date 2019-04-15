package com.example.android.chieftechnologyofficer.interfaces;

import android.content.Context;
import android.os.Bundle;

public interface OnListener {

    <T> void saveSession(Context context, String field, T data);
    void onCall(int frameLayout, String option, Bundle arguments);

}
