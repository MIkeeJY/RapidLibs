package com.utouu.test.data.repository;

import com.utouu.test.data.entity.GoodsListEntity;
import com.utouu.test.data.retrofit.RetrofitHelp;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by Marno on 2016/8/25/14:50.
 */
public class GoodsRepository extends BaseRepository {

    private static volatile GoodsRepository instance;

    private GoodsRepository() {
    }

    public static GoodsRepository getIns() {
        if (instance == null) {
            synchronized (GoodsRepository.class) {
                if (instance == null) {
                    instance = new GoodsRepository();
                }
            }
        }
        return instance;
    }

    /**
     * 获取商品列表
     *
     * @param params
     * @return
     */
    public Observable<GoodsListEntity> getGoodsList(HashMap<String, String> params) {
        return transform(RetrofitHelp.getIns().GOODS().goodsList(params));
    }

}
