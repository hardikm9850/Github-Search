package com.github.example.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.github.example.ContributorActivity;
import com.github.example.R;
import com.github.example.callback.ItemClickListener;
import com.github.example.model.contributor.Contributor;
import com.github.example.utils.CircleTransform;

import java.util.ArrayList;


/**
 * Created by hardik on 25/12/17.
 */

public class ContributorAdapter extends RecyclerView.Adapter<ContributorViewHolder> implements ItemClickListener {

    private ArrayList<Contributor> contributorArrayList;
    private Context context;

    public ContributorAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<Contributor> contributors) {
        contributorArrayList = contributors;
        notifyDataSetChanged();
    }

    @Override
    public ContributorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_contributor, parent, false);
        return new ContributorViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(ContributorViewHolder holder, int position) {
        Contributor contributor = contributorArrayList.get(position);
        String owner = contributor.getLogin();
        if (TextUtils.isEmpty(owner)) {
            owner = "";
        }
        holder.txtContributorName.setText(owner);
        Glide.with(context).
                load(contributor.getAvatarUrl()).
                transform(new CircleTransform(context)).
                placeholder(R.drawable.ic_profile_image).
                error(R.drawable.ic_profile_image).
                into(holder.imgContributor);
    }

    @Override
    public int getItemCount() {
        return contributorArrayList.size();
    }

    @Override
    public void onItemClicked(int position) {
        Contributor contributor = contributorArrayList.get(position);
        Intent intent = new Intent(context, ContributorActivity.class);
        intent.putExtra(ContributorActivity.TAG_DATA,contributor);
        context.startActivity(intent);
    }
}
