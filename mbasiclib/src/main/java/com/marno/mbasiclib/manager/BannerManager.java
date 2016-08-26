package com.marno.mbasiclib.manager;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;

import java.util.List;


/**
 * Created by marno on 2016/3/23/11:10.
 */
public class BannerManager {
    /**
     * 显示banner
     *
     * @param mBanner      ConvenientBanner的对象
     * @param bannerImages 图片网址的集合
     */
    public static void showBanner(ConvenientBanner mBanner, List<? extends Object> bannerImages) {
        mBanner.setPages(new CBViewHolderCreator<BannerImageHolder>() {
            @Override
            public BannerImageHolder createHolder() {
                return new BannerImageHolder();
            }
        }, bannerImages);
//        mBanner.setPageIndicator(new int[]{
//                R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
//                .setPageIndicatorAlign(
//                        ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
    }

    private static class BannerImageHolder implements Holder<Object> {
        private ImageView imageView;
        Context mContext;

        @Override
        public View createView(Context context) {
            this.mContext = context;
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, int position, Object data) {
            GlideManager.loadImg(data, imageView);
        }
    }
}
