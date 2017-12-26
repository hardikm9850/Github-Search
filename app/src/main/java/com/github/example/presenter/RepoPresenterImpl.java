package com.github.example.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.github.example.R;
import com.github.example.callback.ResultCallback;
import com.github.example.contract.RepoContractor;
import com.github.example.interactor.RepoInteractor;
import com.github.example.model.Item;
import com.github.example.model.RepoResponse;
import com.github.example.utils.Utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.github.example.utils.Utils.KEY_ORDER_BY;
import static com.github.example.utils.Utils.KEY_SORT_BY;
import static com.github.example.utils.Utils.SHARED_PREFS_NAME;

/**
 * Created by hardik on 23/12/17.
 */

public class RepoPresenterImpl implements RepoContractor.RepoPresenter, ResultCallback<RepoResponse> {
    RepoContractor.RepoView repoView;
    RepoInteractor repoInteractor;
    private String orderBy = "watcher_count";
    private String sortBy = "desc";
    private int limit = 10;
    private int pageIndex = 1;

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
        if (!repoView.isInternetAvailable()) {
            repoView.onInvalidQueryEntered(R.string.internet_required);
            return;
        }
        repoView.showDialog();
        getDataFromPreference();
        repoInteractor.fetchRepo(repoName, sortBy, orderBy, limit, pageIndex, this);
    }

    private void getDataFromPreference() {
        Context context = repoView.getContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        sortBy = sharedPreferences.getString(KEY_SORT_BY, "desc");
        orderBy = sharedPreferences.getString(KEY_ORDER_BY, "watcher_count");
    }

    @Override
    public void onFilterApplied(String sortBy, String orderBy) {
        this.sortBy = sortBy;
        this.orderBy = orderBy;
        storeFilterInPreference(sortBy, orderBy);
    }

    @Override
    public void getSelectedFilterOption() {
        Context context = repoView.getContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        String sortBy = sharedPreferences.getString(KEY_SORT_BY, "watcher_count");
        String orderBy = sharedPreferences.getString(KEY_ORDER_BY, "desc");
        repoView.onStoredFilterReceived(orderBy,sortBy);
    }

    private void storeFilterInPreference(String sortBy, String orderBy) {
        Context context = repoView.getContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_SORT_BY, sortBy);
        editor.putString(KEY_ORDER_BY, orderBy);
        editor.apply();
    }


    @Override
    public void onSuccess(RepoResponse repoResponse) {
        repoView.hideDialog();
        if (repoResponse.getItems().size() == 0) {
            repoView.onEmptyResponseReceived();
            return;
        }
        if (shouldSortData()) {
            sortDataInDescOrder(repoResponse.getItems());
        }
        repoView.onResponseReceived(repoResponse);
    }

    /**
     * API does not sort that data which is ordered by watcher_count, hence we use comparator to manually sort it
     *
     * @param items data to be sorted
     */
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

    /**
     * Sort data only if condition is watcher_count
     * @return
     */
    private boolean shouldSortData() {
        Context context = repoView.getContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        orderBy = sharedPreferences.getString(KEY_ORDER_BY, "watcher_count");
        return orderBy.equals("watcher_count");
    }
}
