package com.github.example.contract;

import com.github.example.model.contributor.Contributor;

import java.util.List;

/**
 * Created by hardik on 25/12/17.
 */

public interface RepoDetailContract {
    interface RepoDetailView {
        void showDialog();

        void hideDialog();

        void onResponseReceived(List<Contributor> contributors);

        void errorOccurred(int resId);

    }

    interface RepoDetailPresenter {
        void fetchContributors(String owner,String repoName);
    }
}
