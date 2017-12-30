package com.github.example.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.SpinnerAdapter;

import com.github.example.R;
import com.github.example.callback.Callback;
import com.github.example.model.Filter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hardik on 26/12/17.
 */


class FilterDialog extends Dialog {

    @BindView(R.id.closeDialogImg)
    ImageView closeDialogImg;
    @BindView(R.id.radio_stars)
    AppCompatRadioButton radioStars;
    @BindView(R.id.radio_updated)
    AppCompatRadioButton radioUpdated;
    @BindView(R.id.radio_forks)
    AppCompatRadioButton radioForks;
    @BindView(R.id.radio_ascending)
    AppCompatRadioButton radioAscending;
    @BindView(R.id.radio_descending)
    AppCompatRadioButton radioDescending;
    @BindView(R.id.btn_apply)
    AppCompatButton btnApply;
    @BindView(R.id.btn_cancel)
    AppCompatButton btnCancel;
    @BindView(R.id.radio_group_sort_by)
    RadioGroup radioGroupSortBy;
    @BindView(R.id.radio_group_order_by)
    RadioGroup radioGroupOrderBy;
    @BindView(R.id.spinner_language)
    AppCompatSpinner spinnerLanguage;
    @BindView(R.id.spinner_number_of_forks)
    AppCompatSpinner spinnerNumberOfForks;
    @BindView(R.id.spinner_license)
    AppCompatSpinner spinnerLicense;
    @BindView(R.id.spinner_search_in)
    AppCompatSpinner spinnerSearchIn;
    @BindView(R.id.radio_watcher_count)
    AppCompatRadioButton radioWatcherCount;
    @BindView(R.id.edt_topic)
    AppCompatEditText edtTopic;
    @BindView(R.id.txt_from_date)
    AppCompatTextView txtFromDate;
    @BindView(R.id.linear_from_date)
    LinearLayout linearFromDate;
    @BindView(R.id.txt_to_date)
    AppCompatTextView txtToDate;
    @BindView(R.id.linear_to_date)
    LinearLayout linearToDate;

    private View.OnClickListener onClickListener;
    private final FloatingActionButton fabFilter;
    private View dialogView;
    private Dialog dialog;
    private FragmentActivity activity;
    private Context context;
    private final String DATE_PLACEHOLDER = "YYYY/MM/DD";

    private String[] languageArray = new String[]{"No option selected", "Java", "C", "C++", "PHP"};
    private String[] licenseArray = new String[]{"No option selected", "MIT", "Apache license 2.0", "Artistic license 2.0", "Open Software License 3.0", "PostgreSQL License", "Mozilla Public License 2.0", "BSD 2-clause \"Simplified\" license"};
    private String[] noOfForks = new String[]{"No option selected", "Less than 100", "100 - 500", "500 - 1000", "1000 - 2000", "Greater than 2000"};

    private String[] searchInArray = new String[]{"No option selected", "Title", "Description", "Read me"};


    FilterDialog(@NonNull FragmentActivity activity, FloatingActionButton fabFilter, View.OnClickListener clickListener) {
        super(activity);
        this.activity = activity;
        this.context = activity;
        this.fabFilter = fabFilter;
        onClickListener = clickListener;
    }

    void createDialog() {
        dialogView = View.inflate(context, R.layout.layout_filter, null);
        ButterKnife.bind(this, dialogView);
        dialog = new Dialog(context, R.style.FloatingDialogStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(dialogView);
        ImageView imageView = dialog.findViewById(R.id.closeDialogImg);
        dialog.findViewById(R.id.btn_apply).setOnClickListener(onClickListener);
        dialog.findViewById(R.id.btn_cancel).setOnClickListener(onClickListener);
        linearFromDate.setOnClickListener(datePickerListener);
        linearToDate.setOnClickListener(datePickerListener);

        imageView.setOnClickListener(onClickListener);
        dialog.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_BACK) {
                    revealShow(false);
                    return true;
                }
                return false;
            }
        });
        dialog.setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                revealShow(true);
            }
        });

        radioGroupSortBy = dialog.findViewById(R.id.radio_group_sort_by);
        radioGroupOrderBy = dialog.findViewById(R.id.radio_group_order_by);
        radioForks = dialog.findViewById(R.id.radio_forks);
        radioStars = dialog.findViewById(R.id.radio_stars);
        radioUpdated = dialog.findViewById(R.id.radio_updated);
        radioAscending = dialog.findViewById(R.id.radio_ascending);
        radioDescending = dialog.findViewById(R.id.radio_descending);

        setAdapterToFilterOptions();
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        dialog.show();
    }

    private View.OnClickListener datePickerListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.linear_from_date: {
                    DialogFragment datePickerFragment = new DatePickerFragment(new Callback<DatePickerFragment.DateFormat>() {
                        @Override
                        public void returnResult(DatePickerFragment.DateFormat dateFormat) {
                            txtFromDate.setText(dateFormat.toString());
                            if (txtToDate.getText().toString().equalsIgnoreCase(DATE_PLACEHOLDER)) {
                                txtToDate.setText(dateFormat.toString());
                            }
                        }
                    });
                    datePickerFragment.show(activity.getSupportFragmentManager(), "Select From date");
                }
                break;
                case R.id.linear_to_date: {
                    DialogFragment datePickerFragment = new DatePickerFragment(new Callback<DatePickerFragment.DateFormat>() {
                        @Override
                        public void returnResult(DatePickerFragment.DateFormat dateFormat) {
                            txtToDate.setText(dateFormat.toString());
                        }
                    });
                    datePickerFragment.show(activity.getSupportFragmentManager(), "Select To date");
                }
                break;
            }
        }
    };


    private void setAdapterToFilterOptions() {
        SpinnerAdapter languageAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, languageArray);
        SpinnerAdapter licenseAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, licenseArray);
        SpinnerAdapter forkAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, noOfForks);
        SpinnerAdapter searchAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, searchInArray);

        spinnerLanguage.setAdapter(languageAdapter);
        spinnerLicense.setAdapter(licenseAdapter);
        spinnerNumberOfForks.setAdapter(forkAdapter);
        spinnerSearchIn.setAdapter(searchAdapter);
    }


    /**
     * Returns user's selected filter option back to callee
     *
     * @return filter object containing filter option
     */
    Filter getFilters() {
        int languageIndex = spinnerLanguage.getSelectedItemPosition();
        int licenseIndex = spinnerLicense.getSelectedItemPosition();
        int forkIndex = spinnerNumberOfForks.getSelectedItemPosition();
        int searchIndex = spinnerSearchIn.getSelectedItemPosition();
        String fromDate = txtFromDate.getText().toString();
        String toDate = txtToDate.getText().toString();

        int checkedSortByButtonId = radioGroupSortBy.getCheckedRadioButtonId();
        String sortBy = "watcher_count";
        switch (checkedSortByButtonId) {
            case R.id.radio_stars: {
                sortBy = context.getString(R.string.stars);
            }
            break;
            case R.id.radio_updated: {
                sortBy = context.getString(R.string.updated);
            }
            break;
            case R.id.radio_forks: {
                sortBy = context.getString(R.string.forks);
            }
            break;
        }
        int checkedOrderByRadioButtonId = radioGroupOrderBy.getCheckedRadioButtonId();
        String orderBy = "desc";
        switch (checkedOrderByRadioButtonId) {
            case R.id.radio_descending: {
                orderBy = "desc";
            }
            break;
            case R.id.radio_ascending: {
                orderBy = "asc";
            }
            break;
        }
        Filter.Builder builder = new Filter.Builder();
        builder.orderBy(orderBy.toLowerCase()). //order by
                sortBy(sortBy.toLowerCase()). //sort by
                languageIndex(languageIndex).licenseIndex(licenseIndex). //filtering options
                numberOfForksIndex(forkIndex).searchInIndex(searchIndex).
                fromDate(fromDate).toDate(toDate); //date
        return builder.build();
    }

    /**
     * Sets view according to saved user's filter option
     *
     * @param filter filter object containing user's filter options
     */
    void onStoredFilterReceived(Filter filter) {
        String sortBy = filter.getSortBy().toLowerCase();
        String orderBy = filter.getOrderBy().toLowerCase();
        int languageIndex = filter.getLanguageIndex();
        int licenseIndex = filter.getLicenseIndex();
        int searchInIndex = filter.getSearchInIndex();
        int numberOfForks = filter.getNumberOfForksIndex();
        String fromDate = filter.getFromDate();
        String toDate = filter.getToDate();

        switch (sortBy) {
            case "watcher_count": {
                radioWatcherCount.setChecked(true);
            }
            break;
            case "stars": {
                radioStars.setChecked(true);
            }
            break;
            case "forks": {
                radioForks.setChecked(true);
            }
            break;
            case "updated": {
                radioUpdated.setChecked(true);
            }
            break;
        }
        switch (orderBy) {
            case "asc": {
                radioAscending.setChecked(true);
            }
            break;
            case "desc": {
                radioDescending.setChecked(true);
            }
            break;
        }
        spinnerLanguage.setSelection(languageIndex);
        spinnerLicense.setSelection(licenseIndex);
        spinnerSearchIn.setSelection(searchInIndex);
        spinnerNumberOfForks.setSelection(numberOfForks);
        txtFromDate.setText(fromDate);
        txtToDate.setText(toDate);
    }

    void revealShow(boolean shouldExpand) {
        final View view = dialogView.findViewById(R.id.dialog);
        int w = view.getWidth();
        int h = view.getHeight();
        int endRadius = (int) Math.hypot(w, h);
        int cx = (int) (fabFilter.getX() + (fabFilter.getWidth() / 2));
        int cy = (int) (fabFilter.getY()) + fabFilter.getHeight() + 56;
        if (shouldExpand) {
            Animator revealAnimator = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, endRadius);
            view.setVisibility(View.VISIBLE);
            revealAnimator.setDuration(700);
            revealAnimator.start();
        } else {
            Animator anim =
                    ViewAnimationUtils.createCircularReveal(view, cx, cy, endRadius, 0);
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    dialog.dismiss();
                    view.setVisibility(View.INVISIBLE);
                }
            });
            anim.setDuration(700);
            anim.start();
        }
    }

}
