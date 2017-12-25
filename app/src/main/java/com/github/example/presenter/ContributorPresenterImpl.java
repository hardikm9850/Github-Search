package com.github.example.presenter;

import com.github.example.R;
import com.github.example.callback.ResultCallback;
import com.github.example.contract.ContributorContract;
import com.github.example.interactor.ContributorInteractor;
import com.github.example.model.Item;

import java.util.ArrayList;

/**
 * Created by hardik on 25/12/17.
 */

public class ContributorPresenterImpl implements ContributorContract.ContributorPresenter, ResultCallback<ArrayList<Item>> {

    private ContributorContract.ContributorView view;
    private ContributorInteractor contributorInteractor;
    public ContributorPresenterImpl(ContributorContract.ContributorView view) {
        this.view = view;
        contributorInteractor = new ContributorInteractor();
    }

    @Override
    public void fetchRepoByContributorName(String contributorName) {
        view.showDialog();
        contributorInteractor.fetchRepositories(contributorName,this);
    }

    @Override
    public void onSuccess(ArrayList<Item> items) {
        view.hideDialog();
        view.onResponseReceived(items);
    }

    @Override
    public void onFailed(Throwable e) {
        view.hideDialog();
        view.onFailedToReceiveResponse(R.string.no_result);
    }
}
