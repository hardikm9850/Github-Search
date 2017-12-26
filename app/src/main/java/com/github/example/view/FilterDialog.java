package com.github.example.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.github.example.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hardik on 26/12/17.
 */


public class FilterDialog extends Dialog {

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

    private View.OnClickListener onClickListener;
    private final FloatingActionButton fabFilter;
    private View dialogView;
    private Dialog dialog;
    private Context context;


    public FilterDialog(@NonNull Context context, FloatingActionButton fabFilter, View.OnClickListener clickListener) {
        super(context);
        this.context = context;
        this.fabFilter = fabFilter;
        onClickListener = clickListener;
    }

    public void createDialog() {
        dialogView = View.inflate(context, R.layout.layout_filter, null);
        ButterKnife.bind(this, dialogView);
        dialog = new Dialog(context, R.style.FloatingDialogStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(dialogView);
        ImageView imageView = dialog.findViewById(R.id.closeDialogImg);
        dialog.findViewById(R.id.btn_apply).setOnClickListener(onClickListener);
        dialog.findViewById(R.id.btn_cancel).setOnClickListener(onClickListener);
        imageView.setOnClickListener(onClickListener);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_BACK) {
                    revealShow(false);
                    return true;
                }
                return false;
            }
        });
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
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
        Window window = dialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        dialog.show();
    }


    public void revealShow(boolean shouldExpand) {
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

    public String getCheckedOrderByOption() {
        int checkedRadioButtonId = radioGroupOrderBy.getCheckedRadioButtonId();
        String orderBy = "desc";
        switch (checkedRadioButtonId) {
            case R.id.radio_descending: {
                orderBy = "desc";
            }
            break;
            case R.id.radio_ascending: {
                orderBy = "asc";
            }
            break;
        }
        return orderBy;
    }

    public String getCheckedSortByOption() {
        assert radioGroupSortBy != null;
        int checkedRadioButtonId = radioGroupSortBy.getCheckedRadioButtonId();
        String sortBy = "watcher_count";
        switch (checkedRadioButtonId) {
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
        return sortBy;
    }

    public void onStoredFilterReceived(String orderBy, String sortBy) {
        switch (sortBy) {
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
    }
}
