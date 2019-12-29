package cuit.xsgw.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import cuit.xsgw.utils.date.DateUtils;

import java.util.ArrayList;
import java.util.List;

import cuit.xsgw.R;
import cuit.xsgw.net.bean.Diseases;
import cuit.xsgw.net.bean.MedicalUser;

/**
 * created by zhaoxiangbin on 2019/7/23 14:21
 * 460837364@qq.com
 */
public class MedicalUserAdapter extends RecyclerView.Adapter<MedicalUserAdapter.UserViewHolder> {
    private List<MedicalUser> datas;

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent, false);
        return new UserViewHolder(view);
    }



    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        MedicalUser data = datas.get(position);
        holder.itemName.setText(data.getName());
        holder.itemSex.setText(data.getSex());
        holder.itemIdcard.setText(data.getIdCard());
        if (data.getDiseases() == null || data.getDiseases().size() == 0) {
            holder.itemTag.setVisibility(View.INVISIBLE);
            holder.itemTag.setText(null);
        } else {
            holder.itemTag.setVisibility(View.VISIBLE);
            String dis = "";
            for (Diseases diseases : data.getDiseases()) {
                dis += diseases.getShortName() + ",";
            }
            dis = dis.substring(0, dis.length() - 1);
            holder.itemTag.setText(dis);
        }
        String birthYear = data.getIdCard() == null ? null : data.getIdCard().substring(6, 10);
        if (birthYear == null || birthYear.trim().length() == 0) {
            holder.itemAge.setVisibility(View.GONE);
            holder.itemAge.setText(null);
        } else {
            int year = Integer.parseInt(birthYear);
            int age = DateUtils.getYear() - year;
            holder.itemAge.setText(age+"岁");
            if (age < 65) {
                holder.itemAge.setBackgroundResource(R.drawable.round_green_bg);
            } else if (age >= 65 && age < 70) {
                holder.itemAge.setBackgroundResource(R.drawable.round_yellow_bg);
            } else {
                holder.itemAge.setBackgroundResource(R.drawable.round_red_bg);
            }
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public MedicalUserAdapter(List<MedicalUser> list) {
        super();
        this.datas = list;
    }

    public MedicalUserAdapter() {
        datas = new ArrayList<MedicalUser>();
    }

    public void addAll(List<MedicalUser> list)
    {
        if(list.size()==0)
            return;
        for(MedicalUser i:list)
        {
            datas.add(i);
        }
    }
    static class UserViewHolder extends RecyclerView.ViewHolder{
        TextView itemName, itemSex, itemIdcard, itemAge, itemTag;

        public UserViewHolder(View parent) {
            super(parent);
            itemName = parent.findViewById(R.id.item_user_name);
            itemSex = parent.findViewById(R.id.item_user_sex);
            itemIdcard = parent.findViewById(R.id.item_user_idcard);
            itemAge = parent.findViewById(R.id.item_user_age);
            itemTag = parent.findViewById(R.id.item_user_tag);
        }
    }
}
