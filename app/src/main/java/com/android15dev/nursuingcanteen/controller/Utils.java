package com.android15dev.nursuingcanteen.controller;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by krishnauser on 06-Dec-16.
 */

public class Utils {

    private static Context context;
    private static Utils mInstance;

    public Utils(Context context) {
        this.context = context;
        mInstance = this;
    }

    public static Utils getInstance() {
        return mInstance;
    }

    public void showToast(String str) {
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
    }

}
