package com.android.zxb.engine.base.subscription;

import androidx.annotation.NonNull;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Sloth框架：RxJava相关的具体执行类
 */

public class SlothSubscriptionImpl implements SlothSubscription {

    private CompositeSubscription mCompositeSubscription;

    public SlothSubscriptionImpl() {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
    }

    @Override
    public void unSubscribe() {
        mCompositeSubscription.clear();
        mCompositeSubscription.unsubscribe();
    }

    @Override
    public void addRxSubscription(@NonNull Subscription subscription) {
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void removeRxSubscription(@NonNull Subscription subscription) {
        mCompositeSubscription.remove(subscription);
    }
}
