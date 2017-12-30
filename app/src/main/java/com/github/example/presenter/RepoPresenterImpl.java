package com.github.example.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.github.example.R;
import com.github.example.callback.ResultCallback;
import com.github.example.contract.RepoContractor;
import com.github.example.interactor.RepoInteractor;
import com.github.example.model.Filter;
import com.github.example.model.Item;
import com.github.example.model.RepoResponse;
import com.github.example.utils.Utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.github.example.utils.Utils.DEFAULT_ORDER_BY;
import static com.github.example.utils.Utils.DEFAULT_SORT_BY;
import static com.github.example.utils.Utils.KEY_LANGUAGE_INDEX;
import static com.github.example.utils.Utils.KEY_LICENSE_INDEX;
import static com.github.example.utils.Utils.KEY_NUMBER_OF_FORKS_INDEX;
import static com.github.example.utils.Utils.KEY_ORDER_BY;
import static com.github.example.utils.Utils.KEY_SEARCH_IN_INDEX;
import static com.github.example.utils.Utils.KEY_SORT_BY;
import static com.github.example.utils.Utils.SHARED_PREFS_NAME;

/**
 * Created by hardik on 23/12/17.
 */

public class RepoPresenterImpl implements RepoContractor.RepoPresenter, ResultCallback<RepoResponse> {
    private RepoContractor.RepoView repoView;
    private RepoInteractor repoInteractor;
    private String orderBy = "watcher_count";
    private String sortBy = "desc";
    private SharedPreferences sharedPreferences;

    public RepoPresenterImpl(RepoContractor.RepoView repoView) {
        this.repoView = repoView;
        repoInteractor = new RepoInteractor();
        Context context = repoView.getContext();
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
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
        String query = applyFilter(repoName);
        int limit = 10;
        int pageIndex = 1;
        repoInteractor.fetchRepo(query, sortBy, orderBy, limit, pageIndex, this);
    }

    /**
     * Forming final query appending
     *
     * @param repoName search query
     * @return final query formed after appending filter options
     */
    private String applyFilter(String repoName) {
        int DEFAULT_INDEX = 0;
        int languageIndex = sharedPreferences.getInt(KEY_LANGUAGE_INDEX, DEFAULT_INDEX);
        int licenseIndex = sharedPreferences.getInt(KEY_LICENSE_INDEX, DEFAULT_INDEX);
        int forkIndex = sharedPreferences.getInt(KEY_NUMBER_OF_FORKS_INDEX, DEFAULT_INDEX);
        int searchIndex = sharedPreferences.getInt(KEY_SEARCH_IN_INDEX, DEFAULT_INDEX);

        sortBy = sharedPreferences.getString(KEY_SORT_BY, DEFAULT_SORT_BY);
        orderBy = sharedPreferences.getString(KEY_ORDER_BY, DEFAULT_ORDER_BY);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(repoName);
        char add = ' ';
        if (languageIndex != 0) {
            stringBuilder.append(add);
            stringBuilder.append("language:");
            stringBuilder.append(languageArray[languageIndex]);
        }
        if (licenseIndex != 0) {
            stringBuilder.append(add);
            stringBuilder.append("license:");
            stringBuilder.append(licenseNameArray[licenseIndex]);
        }
        if (forkIndex != 0) {
            stringBuilder.append(add);
            stringBuilder.append("forks:");
            stringBuilder.append(noOfForksArray[forkIndex]);
        }
        if (searchIndex != 0) {
            stringBuilder.append(add);
            stringBuilder.append("in:");
            stringBuilder.append(searchInArray[searchIndex]);
        }
        return stringBuilder.toString();
    }


    @Override
    public void onFilterApplied(Filter filter, String query) {
        storeFilterInSharedPreference(filter);
        if (TextUtils.isEmpty(query))
            return;
        onQuerySubmitted(query);
    }

    /**
     * stores user selected filter option in shared preferences
     *
     * @param filter filter object
     */
    private void storeFilterInSharedPreference(Filter filter) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_SORT_BY, filter.getSortBy());
        editor.putString(KEY_ORDER_BY, filter.getOrderBy());
        editor.putInt(KEY_LANGUAGE_INDEX, filter.getLanguageIndex());
        editor.putInt(KEY_LICENSE_INDEX, filter.getLicenseIndex());
        editor.putInt(KEY_NUMBER_OF_FORKS_INDEX, filter.getNumberOfForksIndex());
        editor.putInt(KEY_SEARCH_IN_INDEX, filter.getSearchInIndex());
        editor.apply();
    }

    @Override
    public void getSelectedFilterOption() {
        String sortBy = sharedPreferences.getString(KEY_SORT_BY, DEFAULT_SORT_BY);
        String orderBy = sharedPreferences.getString(KEY_ORDER_BY, DEFAULT_ORDER_BY);
        int languageIndex = sharedPreferences.getInt(KEY_LANGUAGE_INDEX, 0);
        int licenseIndex = sharedPreferences.getInt(KEY_LICENSE_INDEX, 0);
        int forkIndex = sharedPreferences.getInt(KEY_NUMBER_OF_FORKS_INDEX, 0);
        int searchIndex = sharedPreferences.getInt(KEY_SEARCH_IN_INDEX, 0);

        Filter.Builder filterBuilder = new Filter.Builder();
        filterBuilder.
                sortBy(sortBy).orderBy(orderBy).
                searchInIndex(searchIndex).numberOfForksIndex(forkIndex).
                languageIndex(languageIndex).licenseIndex(licenseIndex);
        repoView.onStoredFilterReceived(filterBuilder.build());
    }

    /**
     * Clears stored filter options
     */
    @Override
    public void clearFilters() {
        sharedPreferences.edit().clear().apply();
    }

    @Override
    public void onSuccess(RepoResponse repoResponse) {
        repoView.hideDialog();
        if (repoResponse.getItems().size() == 0) {
            repoView.onEmptyResponseReceived();
            return;
        }
        if (shouldSortData()) {
            String orderBy = sharedPreferences.getString(KEY_ORDER_BY, DEFAULT_ORDER_BY);
            sortDataInDescOrder(repoResponse.getItems(), orderBy.equalsIgnoreCase(DEFAULT_ORDER_BY));
        }
        repoView.onResponseReceived(repoResponse);
    }

    /**
     * API does not order that data which is sorted by watcher_count, hence we use comparator to manually sort it
     *
     * @param items data to be sorted
     */
    private void sortDataInDescOrder(List<Item> items, final boolean order) {
        Collections.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item item, Item t1) {
                int watcherCount1 = item.getWatchersCount();
                int watchedCount2 = t1.getWatchersCount();
                if (order) {
                    //Sort in descending order
                    return watcherCount1 > watchedCount2 ? -1 : watcherCount1 < watchedCount2 ? 1 : 0;
                } else {
                    //Sort in ascending order
                    return watcherCount1 > watchedCount2 ? 1 : watcherCount1 < watchedCount2 ? -1 : 0;
                }
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
     *
     * @return
     */
    private boolean shouldSortData() {
        orderBy = sharedPreferences.getString(KEY_SORT_BY, "watcher_count");
        return orderBy.equals("watcher_count");
    }
}
