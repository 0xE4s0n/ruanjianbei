package com.android.zxb.engine.base.ui.engine;

import android.app.Dialog;
import android.content.Context;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.android.zxb.engine.R;
import com.android.zxb.engine.annotation.bus.EventBus;
import com.android.zxb.engine.annotation.contentView.ContentView;
import com.android.zxb.engine.annotation.contentView.ContentViewInjection;
import com.android.zxb.engine.annotation.contentView.IContentView;
import com.android.zxb.engine.annotation.window.Window;
import com.android.zxb.engine.base.ui.engine.action.EngineDialogFragmentAction;
import com.android.zxb.engine.base.ui.engine.action.rigger.RiggerFragmentAction;
import com.hwangjr.rxbus.RxBus;

/**
 * Fragment的基类.
 */
@EventBus(true)
@Window(immersiveStatus = true)
public abstract class EngineDialogFragment<VM> extends DialogFragment implements IContentView,
        EngineDialogFragmentAction, RiggerFragmentAction {

    protected String TAG = getClass().getSimpleName();
    //Android
    protected Context mContext;
    protected Dialog mDialog;
    protected AppCompatActivity mActivity;
    protected FragmentManager mChildFm;
    protected FragmentManager mFm;
    private View mContentView;
    //MVVM
    private VM mBinding;
    private boolean isInited;
    private Bundle savedInstanceState;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
        mContext = context;
        mFm = getFragmentManager();
        mChildFm = getChildFragmentManager();
    }

/*  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    super.onCreateDialog(savedInstanceState);
    mDialog = new Dialog(mActivity, getDialogStyle());
    mContentView = ContentViewInjection.injectContentView(mActivity, getContentViewId(), getToolBarViewId());
    if (mContentView == null) {
      throw new IllegalArgumentException("请在ContentView注解中注入contentViewId");
    }
    mBinding = (VM) new Object();
    if (mBinding.getClass() == ViewDataBinding.class) {
      mBinding = (VM) DataBindingUtil.bind(getRootView());
    }
    //判断是否需要使用事件总线
    Class<?> clazz = getClass();
    EventBus eventBus = clazz.getAnnotation(EventBus.class);
    if (eventBus != null && eventBus.value()) {
      RxBus.get().register(this);
    }
    mDialog.setContentView(mContentView);
    this.savedInstanceState = savedInstanceState;
    initWindowConfig();
    return mDialog;
  }*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, getDialogStyle());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mContentView = ContentViewInjection.injectContentView(mActivity, getContentViewId(), getToolBarViewId());
        if (mContentView == null) {
            throw new IllegalArgumentException("请在ContentView注解中注入contentViewId");
        }
        mBinding = (VM) new Object();
        if (mBinding.getClass() == ViewDataBinding.class) {
            mBinding = (VM) DataBindingUtil.bind(getRootView());
        }
        //判断是否需要使用事件总线
        Class<?> clazz = getClass();
        EventBus eventBus = clazz.getAnnotation(EventBus.class);
        if (eventBus != null && eventBus.value()) {
            RxBus.get().register(this);
        }
        initWindowConfig();
        return getContentView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isInited) {
//      init(savedInstanceState);
        }
    }

    /**
     * 初始化window配置
     */
    private void initWindowConfig() {
        android.view.Window window = getDialog().getWindow();
        if (window == null) return;
        //dialog
  /*  window.setBackgroundDrawableResource(android.R.color.transparent);//防止宽度无法全屏
    WindowManager.LayoutParams lp = window.getAttributes();
    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
    window.setAttributes(lp);
    window.setGravity(getWindowGravy());
    mDialog.setCanceledOnTouchOutside(isDismissOnTouchOutside());*/
        //view
        window.setWindowAnimations(getDialogStyle());
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams wlp = window.getAttributes();
        window.setGravity(getWindowGravy());
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wlp);
        getDialog().setCanceledOnTouchOutside(isDismissOnTouchOutside());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //判断是否需要使用事件总线
        Class<?> clazz = getClass();
        EventBus eventBus = clazz.getAnnotation(EventBus.class);
        if (eventBus != null && eventBus.value()) {
            RxBus.get().unregister(this);
        }
    }

    @Override
    public int getToolBarViewId() {
        Class<?> clazz = this.getClass();
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView != null) return contentView.toolBarViewId();
        return 0;
    }

    @Override
    public int getContentViewId() {
        Class<?> clazz = this.getClass();
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView != null) return contentView.contentViewId();
        return 0;
    }

    @Override
    public boolean isContainChildFragment() {
        return false;
    }

    @Override
    public boolean isTranslucentStatusChild() {
        return false;
    }

    @Override
    public View getContentView() {
        return mContentView;
    }

    @Override
    public View getRootView() {
        if (getContentView().getId() != ContentViewInjection.CONTAINER_VIEW_ID)
            return getContentView();
        ViewGroup contentGroup = (ViewGroup) mContentView;
        switch (contentGroup.getChildCount()) {
            case 0:
                return getContentView();
            case 1:
                return contentGroup.getChildAt(0);
            default:
                return contentGroup.getChildAt(1);
        }
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initView();
        initData(savedInstanceState);
        initListener();
    }

    @Override
    public void initView() {
    }

    @Override
    public void initListener() {
        if (getRootView() != null) {
            getRootView().setOnClickListener(v -> {
                if (isDismissOnTouchOutside()) {
                    dismiss();
                }
            });
        }
    }

    @Override
    public View findViewById(int id) {
        return getContentView().findViewById(id);
    }

    @Override
    public void runOnUiThread(Runnable runnable) {
        mActivity.runOnUiThread(runnable);
    }

    @Override
    public void onRiggerBackPressed() {
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle args) {
    }

    @Override
    public void onLazyLoadViewCreated(Bundle savedInstanceState) {
    }

    @Override
    public int getDialogStyle() {
        return R.style.Engine_DialogStyle_FullScreen;//默认为全屏Dialog
    }

    @Override
    public int getDialogAnim() {
        return 0;
    }

    @Override
    public int getWindowGravy() {
        return Gravity.CENTER;
    }

    @Override
    public boolean isDismissOnTouchOutside() {
        return true;
    }
}
