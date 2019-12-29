package cuit.xsgw.views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.zxb.engine.base.ui.view.BasePopupWindow;
import com.android.zxb.engine.net.manager.RxApiManager;

import java.util.ArrayList;
import java.util.List;

import cuit.xsgw.adapter.PopAreaAdapter;
import cuit.xsgw.net.bean.AreaData;

/**
 * 选择地区弹出框
 */
public class ChooseAreaPopupWindow extends BasePopupWindow implements View.OnClickListener {
    private LinearLayout mTabsView;
    private RecyclerView mAreaListView;
    private ProgressBar mProgressBar;
    private List<List<AreaData>> mData = new ArrayList<>();
    private PopAreaAdapter mAdapter;
    private LinearLayout.LayoutParams txt_param;

    public ChooseAreaPopupWindow(Activity context) {
        super(context);
        initView();
        initPopWindow();
    }

    public void clearData() {
        mData.clear();
    }

    @Override
    public void getLayoutView() {
        view = inflater.inflate(com.android.zxb.engine.R.layout.pop_choose_area, null);
    }

    @Override
    protected void initPopWindow() {
        super.initPopWindow();
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(mContext, 1f);
                RxApiManager.get().cancel("requestAreaList");
            }
        });
    }

    @Override
    public void initView() {
        txt_param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        txt_param.setMargins(10, 0, 10, 0);

        mTabsView = view.findViewById(com.android.zxb.engine.R.id.choose_area_tabs);
        mProgressBar = view.findViewById(com.android.zxb.engine.R.id.choose_area_progress);
        mAreaListView = view.findViewById(com.android.zxb.engine.R.id.choose_area_list);
        mAreaListView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new PopAreaAdapter(mContext, null);
        mAreaListView.setAdapter(mAdapter);
        view.findViewById(com.android.zxb.engine.R.id.choose_cancle).setOnClickListener(this);
        view.findViewById(com.android.zxb.engine.R.id.choose_ok).setOnClickListener(this);
        mAdapter.setDoClickLisitener(new PopAreaAdapter.onClickLisitener() {
            @Override
            public void doChoose(int index) {
                String areaName = mData.get(mData.size() - 1).get(index).getName();
                String areaCode = mData.get(mData.size() - 1).get(index).getAreaCode();
                if (mSetValueListener != null) mSetValueListener.onSetValue(areaName, areaCode);
                dismiss();
            }

            @Override
            public void doReqest(int index) {
                //update curTab and add new tab
                TextView curTV = (TextView) mTabsView.getChildAt(mData.size() - 1);
                curTV.setText(mData.get(mData.size() - 1).get(index).getName());
                curTV.setTag((mData.size() - 1) + "#" + mData.get(mData.size() - 1).get(index).getAreaCode());
                addNewTab();
                //request next
                requestAreas(mData.get(mData.size() - 1).get(index).getAreaCode());
            }
        });
        initData();
    }

    private void initData() {
        addNewTab();
        requestAreas(null);
    }

    @SuppressLint("HandlerLeak")
    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                updateListView();
            } else if (msg.what == 1) {
                showProgressBar(true);
            } else if (msg.what == 2) {
                showProgressBar(false);
            }
        }
    };

    private void showProgressBar(boolean show) {
        mProgressBar.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == com.android.zxb.engine.R.id.choose_cancle) {
            dismiss();
        } else if (id == com.android.zxb.engine.R.id.choose_ok) {
            String areaName = null;
            String areaCode = null;
            for (int i = mTabsView.getChildCount() - 1; i >= 0; i--) {
                TextView txt_tab = (TextView) mTabsView.getChildAt(i);
                String tag = txt_tab.getTag().toString();
                String[] s = tag.split("#");
                if (!s[1].equals("0")) {
                    areaName = txt_tab.getText().toString();
                    areaCode = s[1];
                    break;
                }
            }
            if (mSetValueListener != null) mSetValueListener.onSetValue(areaName, areaCode);
            dismiss();
        }
    }

    private void addNewTab() {
        final TextView txt_tab = new TextView(mContext);
        txt_tab.setLayoutParams(txt_param);
        txt_tab.setText("请选择");
        txt_tab.setTag(mData.size() + "#" + "0");
        txt_tab.setTextColor(mContext.getResources().getColor(com.android.zxb.engine.R.color.green));
        txt_tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txt_tab.getText().equals("请选择")) {
                    String tag = txt_tab.getTag().toString();
                    String[] s = tag.split("#");
                    int curTabIndex = Integer.parseInt(s[0]);
                    mTabsView.removeViews(curTabIndex + 1, mTabsView.getChildCount() - 1 - curTabIndex);
                    mData = mData.subList(0, curTabIndex + 1);
                    ChooseAreaPopupWindow.this.updateListView();
                }
            }
        });
        mTabsView.addView(txt_tab);
    }

    private void updateListView() {
        if (mData.size() > 0) {
            mAdapter.updateData(mData.get(mData.size() - 1));
        }
    }

    private void requestAreas(String areaCode) {
//        handler.sendEmptyMessage(1);
//        RxApiManager.get().add("requestAreaList", WorkApi.get().requestAreaList(areaCode, new Action1<BaseResponse<List<AreaData>>>() {
//            @Override
//            public void call(BaseResponse<List<AreaData>> listBaseResponse) {
//                handler.sendEmptyMessage(2);
//                if (listBaseResponse.code == ApiConfig.ErrorCode.SUCCESS) {
//                    mData.add(listBaseResponse.data);
//                    handler.sendEmptyMessage(0);
//                } else {
//                    ToastUtils.showToast(mContext, "获取区域失败了");
//                }
//            }
//        }));
    }

    public void setOnSetValueListener(OnSetValueListener ck) {
        this.mSetValueListener = ck;
    }

    private OnSetValueListener mSetValueListener;

    public interface OnSetValueListener {
        void onSetValue(String name, String code);
    }
}
