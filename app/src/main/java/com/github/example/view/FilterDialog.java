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
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatSpinner;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SpinnerAdapter;

import com.github.example.R;
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

    private View.OnClickListener onClickListener;
    private final FloatingActionButton fabFilter;
    private View dialogView;
    private Dialog dialog;
    private Context context;
    private String[] languageArray = new String[]{"No option selected", "Java", "C", "C++", "PHP"};
    private String[] licenseArray = new String[]{"No option selected", "MIT", "Apache license 2.0", "Artistic license 2.0", "Open Software License 3.0"};
    private String[] noOfForks = new String[]{"No option selected", "Less than 100", "Less than 500", "Less than 1000", "Greater than 1000", "Greater than 2000"};
    private String[] searchInArray = new String[]{"No option selected", "Title", "Description", "ReadMe.MD"};


    FilterDialog(@NonNull Context context, FloatingActionButton fabFilter, View.OnClickListener clickListener) {
        super(context);
        this.context = context;
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
                numberOfForksIndex(forkIndex).searchInIndex(searchIndex);
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
