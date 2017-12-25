package com.github.example.presenter;

import com.github.example.callback.ResultCallback;
import com.github.example.contract.RepoDetailContract;
import com.github.example.interactor.RepoDetailInteractor;
import com.github.example.model.contributor.Contributor;

import java.util.List;

/**
 * Created by hardik on 25/12/17.
 */

public class RepoDetailPresenterImpl implements RepoDetailContract.RepoDetailPresenter,ResultCallback<List<Contributor>> {
    RepoDetailContract.RepoDetailView repoDetailView;
    RepoDetailInteractor repoDetailInteractor;

    public RepoDetailPresenterImpl(RepoDetailContract.RepoDetailView repoDetailView) {
        this.repoDetailView = repoDetailView;
        repoDetailInteractor = new RepoDetailInteractor();
    }

    @Override
    public void fetchContributors(String owner, String repoName) {
        repoDetailView.showDialog();
        repoDetailInteractor.getRepoContributors(owner,repoName,this);
    }

    @Override
    public void onSuccess(List<Contributor> contributors) {
         repoDetailView.hideDialog();
         repoDetailView.onResponseReceived(contributors);
    }

    @Override
    public void onFailed(Throwable e) {
        repoDetailView.hideDialog();
    }
}
