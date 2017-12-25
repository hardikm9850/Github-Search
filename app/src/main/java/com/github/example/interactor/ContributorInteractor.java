package com.github.example.interactor;

import com.github.example.callback.ResultCallback;
import com.github.example.model.Item;
import com.github.example.network.RestClient;
import com.github.example.utils.Utils;

import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hardik on 25/12/17.
 */

public class ContributorInteractor {
    public void fetchRepositories(final String contributorName, final ResultCallback<ArrayList<Item>> resultCallback) {
        RestClient.getAPIInvokerInstance().
                getOwnerRepositories(contributorName).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<ArrayList<Item>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        resultCallback.onFailed(e);
                    }

                    @Override
                    public void onNext(ArrayList<Item> items) {
                        resultCallback.onSuccess(items);
                    }
                });
    }
}
