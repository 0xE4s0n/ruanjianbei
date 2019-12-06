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
import cuit.xsgw.net.res.AreaData;

public class PopAreaAdapter extends RecyclerView.Adapter<PopAreaAdapter.ViewHolder> {
    private Context mContext;
    private List<AreaData> mDatas;

    public PopAreaAdapter(Context context, List<AreaData> data) {
        this.mContext = context;
        if (data == null) data = new ArrayList<>();
        this.mDatas = data;
    }

    public void updateData(List<AreaData> newdata) {
        mDatas.clear();
        mDatas.addAll(newdata);
        notifyDataSetChanged();
    }

    @Override
    public PopAreaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pop_area, parent, false);
        return new PopAreaAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PopAreaAdapter.ViewHolder holder, final int position) {
        if (!mDatas.get(position).getLevel().equals("4")) {
            holder.itemImg.setVisibility(View.VISIBLE);
        } else {
            holder.itemImg.setVisibility(View.INVISIBLE);
        }
        holder.itemName.setText(mDatas.get(position).getName());
        holder.itemName.setSelected(mDatas.get(position).isSelected());
        holder.itemSelect.setVisibility(mDatas.get(position).isSelected() ? View.VISIBLE : View.INVISIBLE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (AreaData data : mDatas) {
                    data.setSelected(false);
                }
                mDatas.get(position).setSelected(true);

                if (!mDatas.get(position).getLevel().equals("4")) {
                    mClickLisitener.doReqest(position);
                } else {
                    mClickLisitener.doChoose(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemSelect;
        TextView itemName;
        ImageView itemImg;

        public ViewHolder(View view) {
            super(view);
            itemSelect = itemView.findViewById(R.id.item_area_select);
            itemName = itemView.findViewById(R.id.item_area_name);
            itemImg = itemView.findViewById(R.id.item_area_next);
        }
    }

    private onClickLisitener mClickLisitener;

    public interface onClickLisitener {
        void doChoose(int index);

        void doReqest(int index);
    }

    public void setDoClickLisitener(onClickLisitener lisitener) {
        this.mClickLisitener = lisitener;
    }
}
