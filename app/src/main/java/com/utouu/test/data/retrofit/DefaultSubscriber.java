package com.utouu.test.data.retrofit;

import android.accounts.NetworkErrorException;

import com.google.gson.JsonSyntaxException;
import com.marno.mbasiclib.utils.MLog;
import com.marno.mbasiclib.utils.ToastUtil;
import com.utouu.test.data.exception.AccountsException;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by marno on 2016/7/9/14:45.
 */
public abstract class DefaultSubscriber<T> extends rx.Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        String reason = "";
        if (e instanceof AccountsException) {//账户异常
            reason = "账户异常";
            ToastUtil.remind("登录失效，请重新登录");
        } else if (e instanceof JsonSyntaxException) {//数据格式化错误
            reason = "数据格式化错误";
        } else if (e instanceof HttpException) {// http异常
            reason = "http异常";
        } else if (e instanceof UnknownHostException || e instanceof ConnectException) {//未连接网络或DNS错误
            reason = "未连接网络或DNS错误";
        } else if (e instanceof NetworkErrorException) {
            reason = "网络错误";
            ToastUtil.warn("网络错误");
        } else if (e instanceof SocketException) {
            reason = "连接超时";
        } else {
            reason = "其他错误";
        }
        MLog.e(reason + ":" + e.toString());
    }

    @Override
    public void onNext(T entity) {
        _onNext(entity);
    }

    public abstract void _onNext(T entity);
}
