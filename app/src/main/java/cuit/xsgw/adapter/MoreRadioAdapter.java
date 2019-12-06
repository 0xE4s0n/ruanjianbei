package cuit.xsgw.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cuit.xsgw.R;
import cuit.xsgw.bean.MoreRadioBean;

public class MoreRadioAdapter extends RecyclerView.Adapter<MoreRadioAdapter.ViewHolder> {
    private Context mContext;
    private List<MoreRadioBean> mDatas;

    public MoreRadioAdapter(Context context, List<MoreRadioBean> data) {
        this.mContext = context;
        if (data == null) data = new ArrayList<>();
        this.mDatas = data;
    }

    @Override
    public MoreRadioAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_more_radio, parent, false);
        return new MoreRadioAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoreRadioAdapter.ViewHolder holder, int position) {
        holder.itemName.setText(mDatas.get(position).getName());
        holder.itemAdapter = new MRadioAdapter(mContext, mDatas.get(position).getList());
        holder.itemList.setAdapter(holder.itemAdapter);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName;
        RecyclerView itemList;
        MRadioAdapter itemAdapter;

        public ViewHolder(View view) {
            super(view);
            itemName = itemView.findViewById(R.id.item_more_radio_name);
            itemList = itemView.findViewById(R.id.item_more_radio_checkbox);
            itemList.setLayoutManager(new GridLayoutManager(mContext, 3));
        }
    }
}
