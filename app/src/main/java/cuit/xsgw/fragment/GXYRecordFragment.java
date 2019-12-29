package cuit.xsgw.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cuit.xsgw.R;
import cuit.xsgw.RecordFormAdapter;
import cuit.xsgw.bean.BaseForm;
import cuit.xsgw.net.Api;
import cuit.xsgw.net.Http;
import cuit.xsgw.net.bean.HighBloodQuest;
import cuit.xsgw.net.bean.MedicalUser;
import cuit.xsgw.utils.FormUtil;
import cuit.xsgw.utils.ToastUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GXYRecordFragment extends Fragment {
    private RecyclerView mFormList;
    private RecyclerView.LayoutManager layoutManager;
    private View view;
    private List<BaseForm> mData;
    private RecyclerView.Adapter mAdapter;
    private TextView user;
    private FragmentManager fm;
    private List<MedicalUser> users;
    private List<String> list_users;

    public GXYRecordFragment(FragmentManager fm) {
        this.fm = fm;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.record_fragment, container, false);
        mFormList = view.findViewById(R.id.record_list);
        user = view.findViewById(R.id.work_name_value);
        user.setOnClickListener(v -> {
            new Thread(() -> requestUser()).start();
        });
        layoutManager = new LinearLayoutManager(getContext());
        //获得高血压调查表
        HighBloodQuest highBloodQuest = new HighBloodQuest();
        highBloodQuest.setVisitTime(System.currentTimeMillis() / 1000);
        highBloodQuest.setNextTime(System.currentTimeMillis() / 1000 + 30 * 24 * 60 * 60);//默认3个月后
        this.mData = FormUtil.getHighBloodFollowRecordForm(highBloodQuest);

        this.mAdapter = new RecordFormAdapter(this.getContext(), this.mData);
        this.mFormList.setAdapter(this.mAdapter);
        this.mFormList.setLayoutManager(this.layoutManager);
        return view;
    }

    void requestUser() {
        if (users != null) {
            CreatDialog();
            return;
        }
        Gson gson = new Gson();
        new Thread(() ->
        {
            try {
                new Http().Get(Api.GetGXYUser, null, null, new Callback() {
                    @Override
                    public void onFailure(@org.greenrobot.greendao.annotation.NotNull Call call, @org.greenrobot.greendao.annotation.NotNull IOException e) {
                        getActivity().runOnUiThread(() ->ToastUtil.show(view, e.getMessage()));
                    }

                    @Override
                    public void onResponse(@org.greenrobot.greendao.annotation.NotNull Call call, @NotNull Response response) throws IOException {
                        if (response.isSuccessful()) {
                            users = gson.fromJson(response.body().string(), new TypeToken<List<MedicalUser>>() {
                            }.getType());
                            if (list_users == null)
                                list_users = new ArrayList<>();
                            for (MedicalUser i : users)
                                list_users.add(i.getName() + " " + i.getIdCard());
                            CreatDialog();
                        }else {
                            getActivity().runOnUiThread(() ->ToastUtil.show(view, "请检查网络连接"));
                        }
                    }
                });
            } catch (IOException e) {
                getActivity().runOnUiThread(() ->ToastUtil.show(view, e.getMessage()));
            }
        }).start();
    }

    private void CreatDialog() {
        ChooseUserDialogFragment newFragment = new ChooseUserDialogFragment();

        String[] str_users = new String[list_users.size()];
        for (int i = 0; i < list_users.size(); i++) {
            str_users[i] = list_users.get(i);
        }
        newFragment.show("选择对象", str_users, (dialog, which) -> user.setText(newFragment.getitem(which)), fm);
    }
}