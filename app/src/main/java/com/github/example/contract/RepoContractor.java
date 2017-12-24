package com.github.example.contract;

import com.github.example.model.RepoResponse;

/**
 * Created by hardik on 23/12/17.
 */

public interface RepoContractor {

    interface RepoPresenter{
        void onQuerySubmitted(String repoName);


    }

    interface RepoView{
        void onInvalidQueryEntered(int errorResId);

        void onResponseReceived(RepoResponse repoResponse);

        void onEmptyResponseReceived();

        boolean isInternetAvailable();

        void showDialog();

        void hideDialog();
    }

}
