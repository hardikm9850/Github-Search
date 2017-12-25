package com.github.example.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.example.R;
import com.github.example.adapter.RepoAdapter;
import com.github.example.contract.ContributorContract;
import com.github.example.model.Item;
import com.github.example.model.contributor.Contributor;
import com.github.example.presenter.ContributorPresenterImpl;
import com.github.example.presenter.RepoPresenterImpl;
import com.github.example.utils.CircleTransform;
import com.github.example.utils.DisplayUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hardik on 25/12/17.
 */

public class ContributorActivity extends AppCompatActivity implements ContributorContract.ContributorView {
    @BindView(R.id.imgAvatar)
    ImageView imgAvatar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;

    public static final String TAG_DATA = "tag_contributor";
    @BindView(R.id.recycler_repo)
    RecyclerView recyclerRepo;
    private Context context;
    private ContributorContract.ContributorPresenter contributorPresenter;
    private ProgressDialog progressDialog;
    private ArrayList<Item> repos;
    private RepoAdapter repoAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributor_details);
        ButterKnife.bind(this);
        Contributor contributor = getIntent().getParcelableExtra(TAG_DATA);
        if (contributor == null) {
            Toast.makeText(this, "Something went wrong, plese try again", Toast.LENGTH_SHORT).show();
            return;
        }
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        bindViews(contributor);
    }

    private void bindViews(Contributor contributor) {
        context = this;
        String name = contributor.getLogin();
        String avatarUrl = contributor.getAvatarUrl();
        toolbarLayout.setTitle(name);
        Glide.with(context).
                load(avatarUrl).
                transform(new CircleTransform(context)).
                into(imgAvatar);
        progressDialog = DisplayUtils.getProgressDialog(this,getString(R.string.fetch_contributors));

        repos = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        repoAdapter = new RepoAdapter(context);
        repoAdapter.setData(repos);
        recyclerRepo.setAdapter(repoAdapter);
        recyclerRepo.setLayoutManager(linearLayoutManager);
        contributorPresenter = new ContributorPresenterImpl(this);
        contributorPresenter.fetchRepoByContributorName(name);
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
    public void onResponseReceived(ArrayList<Item> items) {
        repoAdapter.setData(items);
    }

    @Override
    public void onFailedToReceiveResponse(int errorResId) {
        Toast.makeText(context,errorResId,Toast.LENGTH_SHORT).show();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
