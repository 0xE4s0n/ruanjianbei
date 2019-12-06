package cuit.xsgw.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cuit.xsgw.R;
import cuit.xsgw.RecordFormAdapter;
import cuit.xsgw.bean.BaseForm;
import cuit.xsgw.net.req.DiabeteQuest;
import cuit.xsgw.utils.FormUtil;

public class TNBRecordFragment extends Fragment {
    private RecyclerView mFormList;
    private RecyclerView.LayoutManager layoutManager;
    private View view;
    private List<BaseForm> mData;
    private RecyclerView.Adapter mAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.tnb_record_fragment, container, false);
        mFormList = view.findViewById(R.id.highblood_list);
        layoutManager = new LinearLayoutManager(getContext());
        DiabeteQuest diabeteQuest = new DiabeteQuest();
        diabeteQuest.setVisitTime(System.currentTimeMillis() / 1000);
        diabeteQuest.setNextTime(System.currentTimeMillis() / 1000 + 30 * 24 * 60 * 60);//默认3个月后
        mData = FormUtil.getDiabetesFollowRecordForm(diabeteQuest);
        mAdapter = new RecordFormAdapter(getContext(), mData);
        mFormList.setAdapter(mAdapter);
        this.mFormList.setLayoutManager(this.layoutManager);
        return view;
    }
}
