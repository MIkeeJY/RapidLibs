package com.utouu.test.data.retrofit;

import com.utouu.test.base.BaseEntity;
import com.utouu.test.data.entity.GoodsListEntity;

import java.util.HashMap;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by marno on 2016/7/4/14:01.
 */
public interface GoodsService {

    @FormUrlEncoded
    @POST("api/goods/goodsList")
    Observable<BaseEntity<GoodsListEntity>> goodsList(
            @FieldMap HashMap<String, String> params);


}


