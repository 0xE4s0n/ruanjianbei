package cuit.xsgw.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cuit.xsgw.R;
import cuit.xsgw.bean.CheckBoxBean;

public class CheckBoxOtherTextAdapter extends RecyclerView.Adapter<CheckBoxOtherTextAdapter.ViewHolder> {
    private Context mContext;
    private List<CheckBoxBean> mDatas;

    public CheckBoxOtherTextAdapter(Context context, List<CheckBoxBean> data) {
        this.mContext = context;
        if (data == null) data = new ArrayList<>();
        this.mDatas = data;
    }

    @Override
    public CheckBoxOtherTextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_text_checkbox, parent, false);
        return new CheckBoxOtherTextAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CheckBoxOtherTextAdapter.ViewHolder holder, final int position) {
        if (mDatas.get(position).isSelected()) {
            holder.itemName.setBackgroundResource(R.drawable.btn_enable_round_bg1);
        } else {
            holder.itemName.setBackgroundResource(R.drawable.btn_unable_round_bg);
        }
        holder.itemName.setText(mDatas.get(position).getName());

        holder.itemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDatas.get(position).isSelected()) {
                    mDatas.get(position).setSelected(false);
                    holder.itemName.setBackgroundResource(R.drawable.btn_unable_round_bg);
                } else {
                    mDatas.get(position).setSelected(true);
                    holder.itemName.setBackgroundResource(R.drawable.btn_enable_round_bg1);
                }
                mClickLisitener.doClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;

        public ViewHolder(View view) {
            super(view);
            itemName = itemView.findViewById(R.id.item_text);

        }
    }

    private onClickLisitener mClickLisitener;

    public interface onClickLisitener {
        void doClick(int position);
    }

    public void setDoClickLisitener(onClickLisitener lisitener) {
        this.mClickLisitener = lisitener;
    }
}
