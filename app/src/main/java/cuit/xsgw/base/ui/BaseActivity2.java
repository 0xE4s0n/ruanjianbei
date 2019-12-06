package cuit.xsgw.base.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.android.zxb.engine.base.ui.mvp.ui.MvpActivity2;
import com.android.zxb.engine.util.ActivityStackManager;

/**
 * Fragment的基类.
 */
public abstract class BaseActivity2<VM, P> extends MvpActivity2<VM, P> {
    public int[] getPuppetAnimations() {
        return new int[]{
                //R.anim.push_left_in_no_alpha, R.anim.push_right_out_no_alpha,
                //R.anim.push_right_in_no_alpha, R.anim.push_left_out_no_alpha
                0, 0, 0, 0
        };
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Activity管理
        ActivityStackManager.getInstance().addActivity(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Activity管理
        ActivityStackManager.getInstance().removeActivity(getActivity());
    }

}
