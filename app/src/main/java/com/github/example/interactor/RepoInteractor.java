package com.github.example.interactor;

import com.github.example.callback.ResultCallback;
import com.github.example.model.RepoResponse;
import com.github.example.network.RestClient;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by hardik on 23/12/17.
 */

public class RepoInteractor {

    public void fetchRepo(String repoName, String sortBy, String orderBy, int limit, int pageIndex, final ResultCallback<RepoResponse> resultCallback) {
        RestClient.getAPIInvokerInstance().
                getRepositories(repoName, sortBy, orderBy, limit, pageIndex).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Subscriber<RepoResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        resultCallback.onFailed(e);
                    }

                    @Override
                    public void onNext(RepoResponse repoResponse) {
                        resultCallback.onSuccess(repoResponse);
                    }
                });
    }
}
