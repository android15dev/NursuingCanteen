package com.android15dev.nursuingcanteen.app;

import android.app.Application;

import com.android15dev.nursuingcanteen.controller.Utils;

/**
 * Created by krishnauser on 06-Dec-16.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        new Utils(getApplicationContext());
    }
}
