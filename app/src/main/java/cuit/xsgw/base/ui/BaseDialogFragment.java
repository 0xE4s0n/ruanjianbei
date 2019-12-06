package cuit.xsgw.base.ui;

import com.android.zxb.engine.base.ui.mvp.ui.MvpDialogFragment;

/**
 * DialogFragment基类.
 */

public abstract class BaseDialogFragment<VM, P> extends MvpDialogFragment<VM, P> {

    @Override
    public int getDialogStyle() {
        return com.android.zxb.engine.R.style.dialog_up_down_anim;
    }
}
