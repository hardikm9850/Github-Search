package com.github.example;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.util.Linkify;
import android.widget.ImageView;

import com.github.example.model.Item;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hardik on 24/12/17.
 */

public class RepoDetailActivity extends AppCompatActivity {
    public static final String TAG_DATA = "data";

    @BindView(R.id.imgAvatar)
    ImageView imgAvatar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;

    Item repo;
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
    AppCompatTextView txtNoContibutorsFound;
    @BindView(R.id.card_project_contributors)
    CardView cardProjectContributors;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        repo = getIntent().getParcelableExtra(TAG_DATA);
        bindViews(repo);
    }

    private void bindViews(Item repo) {
        String repoName = repo.getName();
        String description = repo.getDescription();
        String repoLink = repo.getUrl();
        toolbar.setTitle(repoName);
        toolbarLayout.setTitle(repoName);
        txtProjectDescription.setText(description);
        txtProjectLink.setText(Html.fromHtml(repoLink));
        Linkify.addLinks(txtProjectLink, Linkify.ALL);//Making link clickable
        //TODO Fetching contributors
    }
}
