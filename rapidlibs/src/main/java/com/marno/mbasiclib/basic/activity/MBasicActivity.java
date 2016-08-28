package com.marno.mbasiclib.basic.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.marno.mbasiclib.utils.StackUtil;
import com.marno.mbasiclib.utils.ToastUtil;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

/**
 * Created by marno on 2016/7/18/10:24.
 * All Activity base class
 */
public abstract class MBasicActivity extends AppCompatActivity {

    protected String TAG = getClass().getSimpleName();

    protected Activity mContext;
    protected boolean mIsFirstShow = true;
    private Unbinder mUnbinder;

    protected final PublishSubject<ActivityEvent> lifecycleSubject = PublishSubject.create();

    /**
     * get activity layout
     */
    @LayoutRes
    protected abstract int getLayout();

    /**
     * tint status bar ,if return not 0
     */
    @ColorInt
    protected abstract int getStatusBarColor();

    /*
     * load data in onResume
     */
    protected abstract void initData();

    /**
     * init activity view
     *
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * before invoke setContentView will call this method
     */
    protected void beforeSetView() {
    }

    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lifecycleSubject.onNext(ActivityEvent.CREATE);
        mContext = this;
        StackUtil.getIns().push(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        beforeSetView();
        setContentView(getLayout());
        mUnbinder = ButterKnife.bind(this);
        initView(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();
        lifecycleSubject.onNext(ActivityEvent.START);
    }

    @Override
    protected void onResume() {
        if (mIsFirstShow) {
            mIsFirstShow = false;
            initData();
        }
        super.onResume();
        lifecycleSubject.onNext(ActivityEvent.RESUME);
    }

    @Override
    protected void onPause() {
        lifecycleSubject.onNext(ActivityEvent.PAUSE);
        super.onPause();
    }

    @Override
    protected void onStop() {
        lifecycleSubject.onNext(ActivityEvent.STOP);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        lifecycleSubject.onNext(ActivityEvent.DESTROY);
        super.onDestroy();
        StackUtil.getIns().pop(this);
        mUnbinder.unbind();
    }

    @NonNull
    public <T> Observable.Transformer<T, T> bindUntilEvent(@NonNull final ActivityEvent event) {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> sourceObservable) {
                Observable<ActivityEvent> compareLifecycleObservable =
                        lifecycleSubject.takeFirst(new Func1<ActivityEvent, Boolean>() {
                            @Override
                            public Boolean call(ActivityEvent activityLifeCycleEvent) {
                                return activityLifeCycleEvent.equals(event);
                            }
                        });
                return sourceObservable.takeUntil(compareLifecycleObservable);
            }
        };
    }


    protected int isFirstBack;

    protected void quit() {
        if (isFirstBack == 0) {
            ToastUtil.warn("再按一次退出程序");
            isFirstBack = 1;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isFirstBack = 0;
                }
            }, 1000);
        } else if (isFirstBack == 1) {
            StackUtil.getIns().popAll();
            finish();
            System.exit(0);
        }
    }

    /**
     * 通过泛型来简化findViewById
     */
    protected final <E extends View> E findView(int id) {
        try {
            return (E) findViewById(id);
        } catch (ClassCastException ex) {
            Log.e(TAG, "Could not cast View to concrete class.", ex);
            throw ex;
        }
    }

    public enum ActivityEvent {
        CREATE,
        START,
        RESUME,
        PAUSE,
        STOP,
        DESTROY
    }
}
