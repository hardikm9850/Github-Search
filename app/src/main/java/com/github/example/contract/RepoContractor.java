package com.github.example.contract;

import android.content.Context;

import com.github.example.model.Filter;
import com.github.example.model.RepoResponse;

/**
 * Created by hardik on 23/12/17.
 */

public interface RepoContractor {

    interface RepoPresenter {
        String[] languageArray = new String[]{"No option selected", "Java", "C", "C++", "PHP"};
        String[] licenseNameArray = new String[]{"No option selected", "mit", "apache-2.0", "artistic-2.0", "osl-3.0"};
        String[] noOfForksArray = new String[]{"No option selected", "forks:<100", "forks:<500", "forks:<1000", "forks:>1000", "forks:>2000"};
        String[] searchInArray = new String[]{"No option selected", "name", "description", "readme"};

        void onQuerySubmitted(String repoName);

        void onFilterApplied(Filter filter, String query);

        void getSelectedFilterOption();

        void clearFilters();
    }

    interface RepoView {
        void onInvalidQueryEntered(int errorResId);

        void onResponseReceived(RepoResponse repoResponse);

        void onEmptyResponseReceived();

        boolean isInternetAvailable();

        void showDialog();

        void hideDialog();

        Context getContext();

        void onStoredFilterReceived(Filter filter);
    }

}
