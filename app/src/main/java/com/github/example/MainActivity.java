package com.github.example;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.github.example.adapter.RepoAdapter;
import com.github.example.contract.RepoContractor;
import com.github.example.model.Item;
import com.github.example.model.RepoResponse;
import com.github.example.presenter.RepoPresenterImpl;
import com.github.example.utils.DisplayUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class MainActivity extends AppCompatActivity implements RepoContractor.RepoView, View.OnClickListener {

    @BindView(R.id.recycler_repo)
    RecyclerView recyclerRepo;
    @BindView(R.id.fab_filter)
    FloatingActionButton fabFilter;

    @Nullable
    @BindView(R.id.closeDialogImg)
    ImageView closeDialogImg;
    @Nullable
    @BindView(R.id.radio_stars)
    AppCompatRadioButton radioStars;
    @Nullable
    @BindView(R.id.radio_updated)
    AppCompatRadioButton radioUpdated;
    @Nullable
    @BindView(R.id.radio_forks)
    AppCompatRadioButton radioForks;
    @Nullable
    @BindView(R.id.radio_ascending)
    AppCompatRadioButton radioAscending;
    @Nullable
    @BindView(R.id.radio_descending)
    AppCompatRadioButton radioDescending;
    @Nullable
    @BindView(R.id.btn_apply)
    AppCompatButton btnApply;
    @Nullable
    @BindView(R.id.btn_cancel)
    AppCompatButton btnCancel;
    @Nullable
    @BindView(R.id.radio_group_sort_by)
    RadioGroup radioGroupSortBy;
    @Nullable
    @BindView(R.id.radio_group_order_by)
    RadioGroup radioGroupOrderBy;

    private Context context;
    private RepoContractor.RepoPresenter repoPresenter;
    private ProgressDialog progressDialog;
    private RepoAdapter repoAdapter;
    private ArrayList<Item> repos;
    private View dialogView;
    private Dialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bindViews();
    }

    private void bindViews() {
        context = this;
        repos = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        repoAdapter = new RepoAdapter(context);
        repoAdapter.setData(repos);
        recyclerRepo.setAdapter(repoAdapter);
        recyclerRepo.setLayoutManager(linearLayoutManager);
        repoPresenter = new RepoPresenterImpl(this);
        progressDialog = DisplayUtils.getProgressDialog(this, getString(R.string.fetch_repo));
        fabFilter.setOnClickListener(this);
    }

    private String getCheckedOrderByOption() {
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

    private String getCheckedSortByOption() {
        int checkedRadioButtonId = radioGroupSortBy.getCheckedRadioButtonId();
        String sortBy = "watcher_count";
        switch (checkedRadioButtonId) {
            case R.id.radio_stars: {
                sortBy = getString(R.string.stars);
            }
            break;
            case R.id.radio_updated: {
                sortBy = getString(R.string.updated);
            }
            break;
            case R.id.radio_forks: {
                sortBy = getString(R.string.forks);
            }
            break;
        }
        return sortBy;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchViewAndroidActionBar = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchViewAndroidActionBar.setOnQueryTextListener(onQueryTextListener);
        return super.onCreateOptionsMenu(menu);
    }

    private SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            repoPresenter.onQuerySubmitted(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    @Override
    public void onInvalidQueryEntered(int errorResId) {
        Toast.makeText(this, errorResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponseReceived(RepoResponse repoResponse) {
        repoAdapter.setData((ArrayList<Item>) repoResponse.getItems());
    }

    @Override
    public void onEmptyResponseReceived() {
        Toast.makeText(context, R.string.no_data_found, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isInternetAvailable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return false;
        }
        return networkInfo.isConnected();
    }

    @Override
    public void showDialog() {
        if (progressDialog != null) {
            progressDialog.show();
        }
    }

    @Override
    public void hideDialog() {
        if (progressDialog != null) {
            progressDialog.hide();
        }
    }

    private void showFilterDialog() {
        dialogView = View.inflate(this, R.layout.layout_filter, null);
        dialog = new Dialog(this, R.style.FloatingDialogStyle);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(dialogView);
        ImageView imageView = dialog.findViewById(R.id.closeDialogImg);
        dialog.findViewById(R.id.btn_apply).setOnClickListener(this);
        dialog.findViewById(R.id.btn_cancel).setOnClickListener(this);
        imageView.setOnClickListener(this);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_BACK) {
                    revealShow(dialogView, false, dialog);
                    return true;
                }
                return false;
            }
        });
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                revealShow(dialogView, true, null);
            }
        });

        radioGroupSortBy = dialog.findViewById(R.id.radio_group_sort_by);
        radioGroupOrderBy = dialog.findViewById(R.id.radio_group_order_by);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void revealShow(View dialogView, boolean shouldExpand, final Dialog dialog) {

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_apply: {
                String sortBy = getCheckedSortByOption();
                String orderBy = getCheckedOrderByOption();
                repoPresenter.onFilterApplied(sortBy.toLowerCase(), orderBy.toLowerCase());
                revealShow(dialogView, false, dialog);
            }
            break;
            case R.id.closeDialogImg:
            case R.id.btn_cancel: {
                revealShow(dialogView, false, dialog);
            }
            break;
            case R.id.fab_filter: {
                showFilterDialog();
            }
            break;
        }
    }
}
