package com.marno.mbasiclib.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
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
 * Created by 李刚 on 2016/7/18/10:24.
 * 所有Activity的基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected String TAG = getClass().getSimpleName();

    protected Activity mContext;
    protected boolean mIsFirstShow = true;
    private Unbinder mUnbinder;

    protected final PublishSubject<ActivityEvent> lifecycleSubject = PublishSubject.create();

    /**
     * 获取布局
     */
    protected abstract int getLayout();

    /**
     * 获取沉浸状态栏颜色，如果是透明状态栏，返回0即可
     */
    protected abstract int getStatusBarColor();

    /*
     * 在onResume()中初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化view
     *
     * @param savedInstanceState
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 设置布局之前
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
        //在onResume中加载数据，只有第一次可见时才加载
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

    //监听Activity声明周期，当Activity销毁后，停止网络请求
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
            ToastUtil.warn("再按一次退出App");
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
