package com.github.example.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.github.example.R;
import com.github.example.callback.ItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hardik on 25/12/17.
 */

public class ContributorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.img_contributor)
    ImageView imgContributor;
    @BindView(R.id.txt_contributor_name)
    AppCompatTextView txtContributorName;

    private ItemClickListener itemClickListener;

    public ContributorViewHolder(View itemView, ItemClickListener itemClickListener) {
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
