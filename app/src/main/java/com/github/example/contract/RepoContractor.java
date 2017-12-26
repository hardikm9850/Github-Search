package com.github.example.contract;

import android.content.Context;

import com.github.example.model.RepoResponse;

/**
 * Created by hardik on 23/12/17.
 */

public interface RepoContractor {

    interface RepoPresenter{
        void onQuerySubmitted(String repoName);

        void onFilterApplied(String sortBy,String orderBy);

        void getSelectedFilterOption();
    }

    interface RepoView{
        void onInvalidQueryEntered(int errorResId);

        void onResponseReceived(RepoResponse repoResponse);

        void onEmptyResponseReceived();

        boolean isInternetAvailable();

        void showDialog();

        void hideDialog();

        Context getContext();

        void onStoredFilterReceived(String orderBy,String sortBy);
    }

}
