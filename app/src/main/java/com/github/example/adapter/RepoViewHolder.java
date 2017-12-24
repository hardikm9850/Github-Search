package com.github.example.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.example.R;
import com.github.example.callback.ItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hardik on 24/12/17.
 */

public class RepoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.card_container)
    public CardView cardView;
    @BindView(R.id.img_avatar)
    public ImageView imgAvatar;
    @BindView(R.id.tv_repo_watcher_count)
    public TextView tvRepoWatcherCount;
    @BindView(R.id.tv_repo_fork_count)
    public TextView tvRepoForkCount;
    @BindView(R.id.tv_repo_star_count)
    public TextView tvRepoStarCount;
    @BindView(R.id.tv_repo_name)
    public TextView tvRepoName;
    @BindView(R.id.tv_repo_full_name)
    public TextView tvRepoFullName;

    private ItemClickListener itemClickListener;

    public RepoViewHolder(View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.itemClickListener = itemClickListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int position = getAdapterPosition();
        if (position == -1)
            return;
        itemClickListener.onItemClicked(position);
    }
}
