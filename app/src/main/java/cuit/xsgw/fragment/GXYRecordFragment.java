package cuit.xsgw.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cuit.xsgw.R;
import cuit.xsgw.RecordFormAdapter;
import cuit.xsgw.bean.BaseForm;
import cuit.xsgw.net.req.HighBloodQuest;
import cuit.xsgw.utils.FormUtil;

public class GXYRecordFragment extends Fragment {
    private RecyclerView mFormList;
    private RecyclerView.LayoutManager layoutManager;
    private View view;
    private List<BaseForm> mData;
    private RecyclerView.Adapter mAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.tnb_record_fragment, container, false);
        mFormList = view.findViewById(R.id.highblood_list);
        layoutManager = new LinearLayoutManager(getContext());
        HighBloodQuest highBloodQuest = new HighBloodQuest();
        highBloodQuest.setVisitTime(System.currentTimeMillis() / 1000);
        highBloodQuest.setNextTime(System.currentTimeMillis() / 1000 + 30 * 24 * 60 * 60);//默认3个月后
        this.mData = FormUtil.getHighBloodFollowRecordForm(highBloodQuest);
        this.mAdapter = new RecordFormAdapter(this.getContext(), this.mData);
        this.mFormList.setAdapter(this.mAdapter);
        this.mFormList.setLayoutManager(this.layoutManager);
        return view;
    }
}