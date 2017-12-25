package com.github.example.contract;

import android.support.annotation.IntegerRes;

import com.github.example.model.Item;

import java.util.ArrayList;

/**
 * Created by hardik on 25/12/17.
 */

public interface ContributorContract {
    interface ContributorView {
        void showDialog();

        void hideDialog();

        void onResponseReceived(ArrayList<Item> items);

        void onFailedToReceiveResponse(int errorResId);
    }

    interface ContributorPresenter {
        void fetchRepoByContributorName(String contributorName);
    }
}
