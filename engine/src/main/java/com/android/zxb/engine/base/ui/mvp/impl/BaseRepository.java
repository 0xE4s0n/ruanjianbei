package com.android.zxb.engine.base.ui.mvp.impl;

import androidx.annotation.NonNull;

import com.android.zxb.engine.base.subscription.SlothSubscription;
import com.android.zxb.engine.base.subscription.SlothSubscriptionImpl;
import com.android.zxb.engine.base.ui.mvp.IModel;
import com.android.zxb.engine.net.frame.BaseRspObserver;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * MVP框架:Model层的基类.
 */

public class BaseRepository implements IModel {

    //proxy
    private SlothSubscription mSlothRx;

    @Override
    public void refreshCache() {

    }

    @Override
    public void onDestroy() {
        getSlothRx().unSubscribe();
    }

    /**
     * 返回SlothRx的具体执行对象，可进RxJava或者网络相关操作
     */
    protected SlothSubscription getSlothRx() {
        if (mSlothRx == null) mSlothRx = new SlothSubscriptionImpl();
        return mSlothRx;
    }

    /**
     * 添加为订阅者
     *
     * @param object 订阅者对象
     */
    protected void addRxSubscription(@NonNull Object object, @NonNull Subscription subscription) {
        getSlothRx().addRxSubscription(subscription);
    }

    /**
     * 取消Rx订阅
     */
    protected void removeRxSubscription(@NonNull Object object, @NonNull Subscription subscription) {
        getSlothRx().removeRxSubscription(subscription);
    }

    /**
     * 创建Body对象,为了提交json参数的数据
     */
    protected RequestBody createBody(@NonNull Object object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        return RequestBody.create(MediaType.parse("application/json"), json);
    }

    /**
     * 订阅
     */
    protected <T> Subscription wrap(Observable<T> observable, Class<?> rspClass, Action1<T> callback) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseRspObserver<T>(rspClass, callback));
    }
}
