package com.github.example.view;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;


import com.github.example.callback.Callback;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;

/**
 * Created by hardik on 02/08/17.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private static final String TAG = DatePickerFragment.class.getSimpleName();

    public boolean shouldAllowFutureDates = false;
    public boolean shouldAllowPastDates = true;
    private Callback<DateFormat> callback;

    public DatePickerFragment() {

    }

    @SuppressLint("ValidFragment")
    public DatePickerFragment(Callback<DateFormat> _callback) {
        callback = _callback;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        if (callback != null) {
            DateFormat dateFormat = new DateFormat(year, month + 1, day);
            callback.returnResult(dateFormat);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
        if (!shouldAllowFutureDates) {
            dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
        }
        if (!shouldAllowPastDates) {
            dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        }
        return dialog;
    }

    public static class DateFormat {
        public DateFormat() {

        }

        DateFormat(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        private int year;
        private int month;
        private int day;

        @NonNull
        @Override
        public String toString() {
            NumberFormat numberFormat = new DecimalFormat("00");
            //YYYY-MM-DD
            return numberFormat.format(year) +
                    "-" + numberFormat.format(month) +
                    "-" + numberFormat.format(day);
        }
    }

}
