package cuit.xsgw.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.zxb.engine.util.MoneyTextWatcher;

import java.util.ArrayList;
import java.util.List;

import cuit.xsgw.R;
import cuit.xsgw.net.bean.Medical;

public class NewItemAdapter extends RecyclerView.Adapter<NewItemAdapter.ViewHolder> {
    private Context mContext;
    private List<Medical> mDatas;

    public NewItemAdapter(Context context, List<Medical> data) {
        this.mContext = context;
        if (data == null) data = new ArrayList<>();
        this.mDatas = data;
    }

    @Override
    public NewItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_new_item, parent, false);
        return new NewItemAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewItemAdapter.ViewHolder holder, final int position) {
        holder.itemName.setText(mDatas.get(position).getName());
        holder.itemRate.setText("" + mDatas.get(position).getRate());
        holder.itemCapacity.setText(mDatas.get(position).getCapacity());
        holder.itemRate.setInputType(InputType.TYPE_CLASS_NUMBER);
        holder.itemCapacity.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        holder.itemName.addTextChangedListener(new MoneyTextWatcher(holder.itemName) {
            @Override
            public void afterTextChanged(Editable s) {
                mDatas.get(position).setName(s.toString());
            }
        });
        holder.itemRate.addTextChangedListener(new MoneyTextWatcher(holder.itemRate) {
            @Override
            public void afterTextChanged(Editable s) {
                mDatas.get(position).setRate(TextUtils.isEmpty(s) ? 0 : Integer.parseInt(s.toString()));
            }
        });
        holder.itemCapacity.addTextChangedListener(new MoneyTextWatcher(holder.itemCapacity) {
            @Override
            public void afterTextChanged(Editable s) {
                mDatas.get(position).setCapacity(s.toString());
            }
        });
        holder.itemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatas.remove(position);
                NewItemAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        EditText itemName, itemRate, itemCapacity;
        ImageView itemDelete;

        public ViewHolder(View view) {
            super(view);
            itemName = itemView.findViewById(R.id.item_new_item_name_value);
            itemRate = itemView.findViewById(R.id.item_new_item_rate);
            itemCapacity = itemView.findViewById(R.id.item_new_item_capacity);
            itemDelete = itemView.findViewById(R.id.item_new_item_delete);
        }
    }
}
