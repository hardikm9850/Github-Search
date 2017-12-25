package com.github.example.interactor;

import com.github.example.callback.ResultCallback;
import com.github.example.model.contributor.Contributor;
import com.github.example.network.RestClient;
import com.github.example.utils.Utils;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hardik on 25/12/17.
 */

public class RepoDetailInteractor {

    public void getRepoContributors(final String owner, final String projectName, final ResultCallback<List<Contributor>> resultCallback) {
        RestClient.
                getAPIInvokerInstance().
                getContributors(owner, projectName).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<List<Contributor>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        resultCallback.onFailed(e);
                    }

                    @Override
                    public void onNext(List<Contributor> contributors) {
                        resultCallback.onSuccess(contributors);
                    }
                });
    }
}
