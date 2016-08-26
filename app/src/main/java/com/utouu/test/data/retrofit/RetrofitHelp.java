package com.utouu.test.data.retrofit;

import com.utouu.test.BuildConfig;
import com.utouu.test.data.repository.UserService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by marno on 2016/6/26/23:15.
 * retrofit封装
 */
public class RetrofitHelp {

    private static String URL_BASE = BuildConfig.BASE_URL;
    private static volatile Retrofit mRetrofit;
    private static volatile RetrofitHelp mClient;

    private RetrofitHelp() {
        mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(URL_BASE)
                .build();
    }

    public static RetrofitHelp getIns() {
        if (mClient == null) {
            synchronized (RetrofitHelp.class) {
                if (mClient == null) {
                    mClient = new RetrofitHelp();
                }
            }
        }
        return mClient;
    }

    protected static <T> T create(Class<T> apiService) {
        return mClient.getIns().mRetrofit.create(apiService);
    }

    public GoodsService GOODS() {
        return create(GoodsService.class);
    }


    public UserService USER() {
        return create(UserService.class);
    }
}
