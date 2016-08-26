package com.utouu.test.data.repository;

import android.accounts.NetworkErrorException;

import com.utouu.test.base.BaseEntity;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by 李刚 on 2016/8/23/16:39.
 */
public abstract class BaseRepository {

    protected <T> Observable<T> transform(Observable<BaseEntity<T>> observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<BaseEntity<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(BaseEntity<T> result) {
                        if (result == null) {
                            return Observable.error(new NetworkErrorException());
                        } else {
                            return Observable.just(result.data);
                        }
                    }
                });
    }


}
