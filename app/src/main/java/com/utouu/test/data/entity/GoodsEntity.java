package com.utouu.test.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by marno on 2016/6/17/09:28.
 */
public class GoodsEntity {
    @SerializedName(value = "id", alternate = {"collect_id"})
    public String id;//商品ID
    public String activity_id;//活动id
    public String goods_id;//商品id
    public int is_collected;//是否被收藏
    public String shop_logo;//店铺logo
    public String distance;//距离
    public int activityStatus;//活动状态
    public String status;//商品状态
    public String price_activity;
    public String add_time;
    public String shop_id;
    public String goods_name;
    public String sold;
    public String price_shop;
    public String goods_thumb;
    public String now_price;
    public String shop_name;
    public String scheme;

}
