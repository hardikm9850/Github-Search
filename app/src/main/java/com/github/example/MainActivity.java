package com.github.example;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class MainActivity extends AppCompatActivity implements RepoContractor.RepoView {

    @BindView(R.id.recycler_repo)
    RecyclerView recyclerRepo;
    @BindView(R.id.fab_filter)
    FloatingActionButton fabFilter;

    private Context context;
    private RepoContractor.RepoPresenter repoPresenter;
    private ProgressDialog progressDialog;
    private RepoAdapter repoAdapter;
    private ArrayList<Item> repos;
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
        progressDialog = DisplayUtils.getProgressDialog(this);
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
        Toast.makeText(context,R.string.no_data_found,Toast.LENGTH_SHORT).show();
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
        if(progressDialog != null){
            progressDialog.show();
        }
    }

    @Override
    public void hideDialog() {
        if(progressDialog != null){
            progressDialog.hide();
        }
    }
}
