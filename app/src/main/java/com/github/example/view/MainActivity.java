package com.github.example.view;

import android.app.ProgressDialog;
import android.content.Context;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.github.example.R;
import com.github.example.adapter.RepoAdapter;
import com.github.example.contract.RepoContractor;
import com.github.example.model.Filter;
import com.github.example.model.Item;
import com.github.example.model.RepoResponse;
import com.github.example.presenter.RepoPresenterImpl;
import com.github.example.utils.DisplayUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RepoContractor.RepoView, View.OnClickListener {

    @BindView(R.id.recycler_repo)
    RecyclerView recyclerRepo;
    @BindView(R.id.fab_filter)
    FloatingActionButton fabFilter;

    //---Filter dialog views
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
    private FilterDialog filterDialog;
    private SearchView searchViewAndroidActionBar;
    private Filter storedFilter;


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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchViewAndroidActionBar = (SearchView) MenuItemCompat.getActionView(searchItem);
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
        repoAdapter.setData((new ArrayList<Item>()));
        Toast.makeText(context, R.string.no_data_found, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isInternetAvailable() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
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

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void onStoredFilterReceived(Filter filter) {
        storedFilter = filter;
        filterDialog.onStoredFilterReceived(storedFilter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_apply: {
                Filter filter = filterDialog.getFilters();
                filterDialog.revealShow(false);
                if (storedFilter != null && storedFilter.equals(filter)) { //no need to make api call when filter was not changed
                    return;
                }
                String query = searchViewAndroidActionBar.getQuery().toString();
                repoPresenter.onFilterApplied(filter, query);
            }
            break;
            case R.id.closeDialogImg: {
                filterDialog.revealShow(false);
            }
            break;
            case R.id.btn_cancel: {
                filterDialog.revealShow(false);
                String query = searchViewAndroidActionBar.getQuery().toString();
                repoPresenter.clearFilters(query);
            }
            break;
            case R.id.fab_filter: {
                filterDialog = new FilterDialog(this, fabFilter, this);
                filterDialog.createDialog();
                repoPresenter.getSelectedFilterOption();
            }
            break;
        }
    }
}
