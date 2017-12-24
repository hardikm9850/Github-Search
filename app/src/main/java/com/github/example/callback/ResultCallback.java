package com.github.example.callback;

/**
 * Created by hardik on 24/12/17.
 */

public interface ResultCallback<T> {
    void onSuccess(T t);

    void onFailed(Throwable e);
}
