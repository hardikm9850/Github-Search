package com.github.example.utils;

import android.app.Activity;
import android.app.ProgressDialog;

import com.github.example.R;

/**
 * Created by hardik on 24/12/17.
 */

public class DisplayUtils {

    public static ProgressDialog getProgressDialog(Activity activity,String message) {
        ProgressDialog progressDialog = new ProgressDialog(activity, R.style.DialogTheme);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        return progressDialog;
    }
}
