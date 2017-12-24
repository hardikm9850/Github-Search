package com.github.example.presenter;

import android.text.TextUtils;

import com.github.example.R;
import com.github.example.callback.ResultCallback;
import com.github.example.contract.RepoContractor;
import com.github.example.interactor.RepoInteractor;
import com.github.example.model.Item;
import com.github.example.model.RepoResponse;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hardik on 23/12/17.
 */

public class RepoPresenterImpl implements RepoContractor.RepoPresenter, ResultCallback<RepoResponse> {
    RepoContractor.RepoView repoView;
    RepoInteractor repoInteractor;

    public RepoPresenterImpl(RepoContractor.RepoView repoView) {
        this.repoView = repoView;
        repoInteractor = new RepoInteractor();
    }

    @Override
    public void onQuerySubmitted(String repoName) {
        if (TextUtils.isEmpty(repoName)) {
            repoView.onInvalidQueryEntered(R.string.empty_query);
            return;
        }
        if (repoName.length() < 3) {
            repoView.onInvalidQueryEntered(R.string.invalid_length_of_query);
            return;
        }
        repoView.showDialog();
        repoInteractor.fetchRepo(repoName, this);
    }

    @Override
    public void onSuccess(RepoResponse repoResponse) {
        repoView.hideDialog();
        if (repoResponse.getItems().size() == 0) {
            repoView.onEmptyResponseReceived();
            return;
        }
        sortDataInDescOrder(repoResponse.getItems());
        repoView.onResponseReceived(repoResponse);
    }

    private void sortDataInDescOrder(List<Item> items) {
        Collections.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item item, Item t1) {
                int watcherCount1 = item.getWatchersCount();
                int watchedCount2 = t1.getWatchersCount();
                return watcherCount1 > watchedCount2 ? -1 : watcherCount1 < watchedCount2 ? 1 : 0;
            }
        });
    }

    @Override
    public void onFailed(Throwable e) {
        repoView.hideDialog();
        repoView.onEmptyResponseReceived();
    }
}
