package cuit.xsgw.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.android.zxb.engine.util.MoneyTextWatcher;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cuit.xsgw.R;
import cuit.xsgw.bean.DiseaseHistoryBean;
import cuit.xsgw.pickerview.listener.OnTimeSelectListener;
import cuit.xsgw.utils.ShowTimeUtil;
import cuit.xsgw.utils.date.DateStyle;
import cuit.xsgw.utils.date.DateUtils;

public class DiseaseHistoryAdapter extends RecyclerView.Adapter<DiseaseHistoryAdapter.ViewHolder> {
    private Context mContext;
    private List<DiseaseHistoryBean> mDatas;

    public DiseaseHistoryAdapter(Context context, List<DiseaseHistoryBean> data) {
        this.mContext = context;
        if (data == null) data = new ArrayList<>();
        this.mDatas = data;
    }

    @Override
    public DiseaseHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_disease_history, parent, false);
        return new DiseaseHistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DiseaseHistoryAdapter.ViewHolder holder, final int position) {
        holder.itemName.setText(mDatas.get(position).getName());
        holder.itemSubName.setText(mDatas.get(position).getSubName());
        if (TextUtils.isEmpty(mDatas.get(position).getSubName())) {
            holder.itemSubName.setVisibility(View.GONE);
            holder.itemSubNameValue.setVisibility(View.GONE);
        } else {
            holder.itemSubName.setVisibility(View.VISIBLE);
            holder.itemSubNameValue.setVisibility(View.VISIBLE);
        }
        if (mDatas.get(position).getSureTime() != 0) {
            holder.itemSubTimeValue.setText(DateUtils.formatSecondTimestamp(mDatas.get(position).getSureTime(), DateStyle.YYYY_MM_DD));
        } else {
            holder.itemSubTimeValue.setText(null);
        }
        if (mDatas.get(position).isSelected()) {
            holder.itemImg.setImageResource(R.drawable.btn_bk_gongkai_s);
            if (position == 0) {
                holder.itemSubView.setVisibility(View.GONE);
            } else {
                holder.itemSubView.setVisibility(View.VISIBLE);
            }
        } else {
            holder.itemImg.setImageResource(R.drawable.btn_bk_gongkai_n);
            holder.itemSubView.setVisibility(View.GONE);
        }
        holder.itemSubNameValue.removeTextChangedListener((MoneyTextWatcher) holder.itemSubNameValue.getTag());
        holder.itemSubNameValue.setText(mDatas.get(position).getSubValue());
        MoneyTextWatcher watcher = new MoneyTextWatcher(holder.itemSubNameValue) {
            @Override
            public void afterTextChanged(Editable s) {
                if (s == null || s.toString().trim().length() < 1) {
                    mDatas.get(position).setSubValue(null);
                } else {
                    mDatas.get(position).setSubValue(s.toString());
                }
            }
        };
        holder.itemSubNameValue.addTextChangedListener(watcher);
        holder.itemSubNameValue.setTag(watcher);

        holder.itemImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    if (mDatas.get(0).isSelected()) {
                        mDatas.get(0).setSelected(false);
                        holder.itemImg.setImageResource(R.drawable.btn_bk_gongkai_n);
                    } else {
                        for (int index = 1; index < mDatas.size(); index++) {
                            DiseaseHistoryBean bean = mDatas.get(index);
                            if (bean.isSelected()) {
                                bean.setSelected(false);
                                bean.setSubValue(null);
                                bean.setSureTime(0);
                                DiseaseHistoryAdapter.this.notifyItemChanged(index);
                            }
                        }
                        mDatas.get(0).setSelected(true);
                        holder.itemImg.setImageResource(R.drawable.btn_bk_gongkai_s);
                    }
                } else {
                    if (mDatas.get(position).isSelected()) {
                        mDatas.get(position).setSelected(false);
                        mDatas.get(position).setSubValue(null);
                        mDatas.get(position).setSureTime(0);
                        holder.itemImg.setImageResource(R.drawable.btn_bk_gongkai_n);
                        holder.itemSubNameValue.setText(null);
                        holder.itemSubTimeValue.setText(null);
                        holder.itemSubView.setVisibility(View.GONE);
                    } else {
                        if (mDatas.get(0).isSelected()) {
                            mDatas.get(0).setSelected(false);
                            DiseaseHistoryAdapter.this.notifyItemChanged(0);
                        }
                        mDatas.get(position).setSelected(true);
                        holder.itemImg.setImageResource(R.drawable.btn_bk_gongkai_s);
                        holder.itemSubView.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        holder.itemSubTimeValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowTimeUtil.showTimePicker(mContext, new OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        holder.itemSubTimeValue.setText(DateUtils.getDateFormatYYYYMMDD(date));
                        mDatas.get(position).setSureTime(date.getTime() / 1000);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImg;
        TextView itemName, itemSubName, itemSubTimeValue;
        View itemSubView;
        EditText itemSubNameValue;

        public ViewHolder(View view) {
            super(view);
            itemImg = itemView.findViewById(R.id.item_disease_history_cbox);
            itemName = itemView.findViewById(R.id.item_disease_history_name);

            itemSubView = itemView.findViewById(R.id.item_disease_history_subview);
            itemSubName = itemView.findViewById(R.id.item_disease_subname);
            itemSubNameValue = itemView.findViewById(R.id.item_disease_subname_value);
            itemSubTimeValue = itemView.findViewById(R.id.item_disease_subtime_value);
        }
    }
}
