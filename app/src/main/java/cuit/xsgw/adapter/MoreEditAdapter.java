package cuit.xsgw.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.zxb.engine.util.MoneyTextWatcher;

import java.util.ArrayList;
import java.util.List;

import cuit.xsgw.R;
import cuit.xsgw.bean.EditInputType;
import cuit.xsgw.bean.MoreEditBean;

public class MoreEditAdapter extends RecyclerView.Adapter<MoreEditAdapter.ViewHolder> {
    private Context mContext;
    private List<MoreEditBean> mDatas;

    public MoreEditAdapter(Context context, List<MoreEditBean> data) {
        this.mContext = context;
        if (data == null) data = new ArrayList<>();
        this.mDatas = data;
    }

    @Override
    public MoreEditAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_sub_more_edit, parent, false);
        return new MoreEditAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoreEditAdapter.ViewHolder holder, final int position) {
        holder.itemName.setText(mDatas.get(position).getName());
        holder.itemUnit.setText(mDatas.get(position).getUnitData());
        holder.itemValue.setText(mDatas.get(position).getCurValue());
        setInputType(holder.itemValue, mDatas.get(position).getInputType());
        holder.itemValue.addTextChangedListener(new MoneyTextWatcher(holder.itemValue) {
            @Override
            public void afterTextChanged(Editable s) {
                mDatas.get(position).setCurValue(s.toString());
                mComputeLisitener.doCompute(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        EditText itemValue;
        TextView itemName, itemUnit;

        public ViewHolder(View view) {
            super(view);
            itemName = itemView.findViewById(R.id.sub_more_title);
            itemValue = itemView.findViewById(R.id.sub_more_value);
            itemUnit = itemView.findViewById(R.id.sub_more_unitdata);
        }
    }

    private void setInputType(EditText view, int type) {
        if (type == EditInputType.TYPE_NUMBER_DECIMAL) {
            view.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        } else if (type == EditInputType.TYPE_JUST_NUMBER) {
            view.setInputType(InputType.TYPE_CLASS_NUMBER);
        } else if (type == EditInputType.TYPE_NULL) {
            view.setInputType(InputType.TYPE_NULL);
        } else {
            view.setInputType(InputType.TYPE_CLASS_TEXT);
        }
    }

    private onComputeLisitener mComputeLisitener;

    public interface onComputeLisitener {
        void doCompute(int index);
    }

    public void setDoComputeLisitener(onComputeLisitener lisitener) {
        this.mComputeLisitener = lisitener;
    }
}
