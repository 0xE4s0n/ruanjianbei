package cuit.xsgw.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cuit.xsgw.R;
import cuit.xsgw.bean.RadioStringBean;

public class RadioAdapter extends RecyclerView.Adapter<RadioAdapter.ViewHolder> {
    private Context mContext;
    private List<RadioStringBean> mDatas;

    public RadioAdapter(Context context, List<RadioStringBean> data) {
        this.mContext = context;
        if (data == null) data = new ArrayList<>();
        this.mDatas = data;
    }

    @Override
    public RadioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_radio, parent, false);
        return new RadioAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RadioAdapter.ViewHolder holder, final int position) {
        if (mDatas.get(position).isSelected()) {
            holder.itemImg.setImageResource(R.drawable.btn_bk_gongkai_s);
        } else {
            holder.itemImg.setImageResource(R.drawable.btn_bk_gongkai_n);
        }
        holder.itemName.setText(mDatas.get(position).getName());

        if (mClickLisitener != null)
            mClickLisitener.doShow(position, mDatas.get(position).isNeedDetail()
                    && mDatas.get(position).isSelected());

        holder.itemImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioAdapter.this.resetSelect();
                mDatas.get(position).setSelected(true);
                RadioAdapter.this.notifyDataSetChanged();
                if (mClickLisitener != null)
                    mClickLisitener.doShow(position, mDatas.get(position).isNeedDetail());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImg;
        TextView itemName;

        public ViewHolder(View view) {
            super(view);
            itemImg = itemView.findViewById(R.id.item_radio_cbox);
            itemName = itemView.findViewById(R.id.item_radio_value);

        }
    }

    private void resetSelect() {
        for (RadioStringBean bean : mDatas) {
            bean.setSelected(false);
        }
    }

    private onClickLisitener mClickLisitener;

    public interface onClickLisitener {
        void doShow(int index, boolean show);
    }

    public void setDoClickLisitener(onClickLisitener lisitener) {
        this.mClickLisitener = lisitener;
    }
}
