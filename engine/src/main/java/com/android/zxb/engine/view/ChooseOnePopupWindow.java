package com.android.zxb.engine.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.zxb.engine.R;
import com.android.zxb.engine.base.ui.view.BasePopupWindow;

/**
 * 必须单选弹出框
 * created by zhaoxiangbin on 2019/3/27 16:03
 * 460837364@qq.com
 */
public class ChooseOnePopupWindow extends BasePopupWindow {

    private Context mContext;
    private String[] mDatas;
    private String mTitleValue;
    private int selected = 0;
    private TextView mTitleView;
    private RecyclerView mListView;
    private OneAdapter mAdapter;

    public ChooseOnePopupWindow(Activity context, String[] data, String title, int choosed) {
        super(context);
        this.mContext = context;
        this.mDatas = data;
        this.mTitleValue = title;
        this.selected = choosed;
        initView();
        initPopWindow();
    }

    public void setOnItemClickListener(OnItemClickListener ck) {
        this.mOnItemClickListener = ck;
    }

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public void getLayoutView() {
        view = inflater.inflate(R.layout.pop_choose_one_layout, null);
    }

    @Override
    public void initView() {
        mTitleView = view.findViewById(R.id.pop_title);
        mListView = view.findViewById(R.id.choose_one_list);
        mListView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        initData();
    }

    private void initData() {
        mTitleView.setText(mTitleValue);
        mAdapter = new OneAdapter();
        mListView.setAdapter(mAdapter);
    }

    class OneAdapter extends RecyclerView.Adapter<OneAdapter.ViewHolder> {

        public OneAdapter() {
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_choose_one, parent, false);
            return new OneAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.itemTxv.setText(mDatas[position]);
            if (selected == position) {
                holder.itemSelectImg.setImageResource(R.drawable.btn_bk_gongkai_s);
            } else {
                holder.itemSelectImg.setImageResource(R.drawable.btn_bk_gongkai_n);
            }
            holder.itemTxv.setOnClickListener(v -> {
                if (selected != position) {
                    selected = position;
                    mOnItemClickListener.onItemClick(selected);
                }
                dismiss();
            });
        }

        @Override
        public int getItemCount() {
            return mDatas.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView itemTxv;
            ImageView itemSelectImg;

            public ViewHolder(View view) {
                super(view);
                itemTxv = view.findViewById(R.id.item_title);
                itemSelectImg = view.findViewById(R.id.item_select);
            }
        }
    }
}
