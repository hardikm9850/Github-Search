package com.github.example.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.github.example.R;
import com.github.example.view.RepoDetailActivity;
import com.github.example.callback.ItemClickListener;
import com.github.example.model.Item;

import java.util.ArrayList;

/**
 * Created by hardik on 24/12/17.
 */

public class RepoAdapter extends RecyclerView.Adapter<RepoViewHolder> implements ItemClickListener {
    private Context context;
    private ArrayList<Item> data;

    public void setData(ArrayList<Item> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public RepoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_repo_overview, parent, false);
        return new RepoViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        Item repoDetail = data.get(position);
        holder.tvRepoName.setText(repoDetail.getName());
        holder.tvRepoFullName.setText(repoDetail.getFullName());
        holder.tvRepoWatcherCount.setText(String.valueOf(repoDetail.getWatchersCount()));
        holder.tvRepoForkCount.setText(String.valueOf(repoDetail.getForksCount()));
        holder.tvRepoStarCount.setText(String.valueOf(repoDetail.getStargazersCount()));
        String ownerUrl = repoDetail.getOwner().getAvatarUrl();
        if (TextUtils.isEmpty(ownerUrl)) {
            Glide.with(context).load(R.drawable.img_placeholder).into(holder.imgAvatar);
        } else {
            Glide.with(context).load(ownerUrl).into(holder.imgAvatar);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onItemClicked(int position) {
        Item repoData = data.get(position);
        Intent intent = new Intent(context, RepoDetailActivity.class);
        intent.putExtra(RepoDetailActivity.TAG_DATA,repoData);
        context.startActivity(intent);
    }
}
