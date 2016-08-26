package com.utouu.test.data.repository;

import com.utouu.test.data.entity.UserEntity;
import com.utouu.test.data.retrofit.RetrofitHelp;

import rx.Observable;

/**
 * Created by Marno on 2016/8/26/14:42.
 */
public class UserRepository extends BaseRepository{
     private static volatile UserRepository instance;

         private UserRepository() {
         }

         public static UserRepository getIns() {
             if (instance == null) {
                 synchronized (UserRepository.class) {
                     if (instance == null) {
                         instance = new UserRepository();
                     }
                 }
             }
             return instance;
         }

    /**
     * 获取用户信息
     * @param phone
     * @param password
     * @return
     */
    public Observable<UserEntity> getUserInfo(String phone,String password) {
        return transform(RetrofitHelp.getIns().USER().getUserInfo(phone,password));
    }
}
