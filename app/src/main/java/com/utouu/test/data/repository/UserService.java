package com.utouu.test.data.repository;

import com.utouu.test.base.BaseEntity;
import com.utouu.test.data.entity.UserEntity;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Marno on 2016/8/26/14:37.
 */
public interface UserService {

    @FormUrlEncoded
    @POST("aa/ddd/cc")
    Observable<BaseEntity<UserEntity>> getUserInfo(
            @Field("phone") String phoneNum,
            @Field("password") String pwd);
}
