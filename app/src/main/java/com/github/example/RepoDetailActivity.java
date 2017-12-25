package com.github.example;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.util.Linkify;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.example.adapter.ContributorAdapter;
import com.github.example.contract.RepoDetailContract;
import com.github.example.model.Item;
import com.github.example.model.contributor.Contributor;
import com.github.example.presenter.RepoDetailPresenterImpl;
import com.github.example.utils.CircleTransform;
import com.github.example.utils.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by hardik on 24/12/17.
 */

public class RepoDetailActivity extends AppCompatActivity implements RepoDetailContract.RepoDetailView {
    public static final String TAG_DATA = "data";

    @BindView(R.id.imgAvatar)
    ImageView imgAvatar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;

    @BindView(R.id.txt_project_link)
    AppCompatTextView txtProjectLink;
    @BindView(R.id.card_project_link)
    CardView cardProjectLink;
    @BindView(R.id.txt_project_description)
    AppCompatTextView txtProjectDescription;
    @BindView(R.id.card_project_description)
    CardView cardProjectDescription;
    @BindView(R.id.recycler_contributors)
    RecyclerView recyclerContributors;
    @BindView(R.id.txt_no_contributors_found)
    AppCompatTextView txtNoContributorsFound;
    @BindView(R.id.card_project_contributors)
    CardView cardProjectContributors;

    private Item repoObject;
    private RepoDetailContract.RepoDetailPresenter repoDetailPresenter;
    private ProgressDialog progressDialog;
    private ContributorAdapter contributorAdapter;
    private Context context;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        context = this;
        progressDialog = DisplayUtils.getProgressDialog(this,getString(R.string.fetch_project_details));
        repoObject = getIntent().getParcelableExtra(TAG_DATA);
        contributorAdapter = new ContributorAdapter(context);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerContributors.setAdapter(contributorAdapter);
        recyclerContributors.setLayoutManager(linearLayoutManager);
        bindViews(repoObject);
    }

    private void bindViews(Item repo) {
        String repoName = repo.getName();
        String description = repo.getDescription();
        String repoLink = repo.getHtmlUrl();
        String owner = repo.getOwner().getLogin();
        String ownerAvatar = repo.getOwner().getAvatarUrl();

        toolbar.setTitle(repoName);
        toolbarLayout.setTitle(repoName);
        txtProjectDescription.setText(description);
        txtProjectLink.setText(Html.fromHtml(repoLink));
        //Linkify.addLinks(txtProjectLink, Linkify.ALL);//Making link clickable
        Glide.with(context).
                load(ownerAvatar).
                transform(new CircleTransform(context)).
                into(imgAvatar);
        repoDetailPresenter = new RepoDetailPresenterImpl(this);
        repoDetailPresenter.fetchContributors(owner, repoName);
    }

    @Override
    public void showDialog() {
        if (progressDialog != null) {
            progressDialog.show();
        }
    }

    /**
     * We want to show this repo on github's page
     */
    @OnClick(R.id.txt_project_link)
    public void onGithubRepoLinkClicked(View view) {
        String url = txtProjectLink.getText().toString();
        Intent intent = new Intent(context, GithubWebClient.class);
        intent.putExtra(GithubWebClient.TAG_URL, url);
        context.startActivity(intent);
    }


    @Override
    public void hideDialog() {
        if (progressDialog != null) {
            progressDialog.hide();
        }
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

    @Override
    public void onResponseReceived(List<Contributor> contributors) {
        txtNoContributorsFound.setVisibility(View.GONE);
        contributorAdapter.setData((ArrayList<Contributor>) contributors);
        recyclerContributors.setVisibility(View.VISIBLE);
    }

    @Override
    public void errorOccurred(int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }
}
