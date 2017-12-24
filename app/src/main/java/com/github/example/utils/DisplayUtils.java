package com.github.example.utils;

import android.app.Activity;
import android.app.ProgressDialog;

/**
 * Created by hardik on 24/12/17.
 */

public class DisplayUtils {

    public static ProgressDialog getProgressDialog(Activity activity) {
        ProgressDialog progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Fetching repo");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        return progressDialog;
    }
}
