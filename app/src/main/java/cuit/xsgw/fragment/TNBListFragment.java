package cuit.xsgw.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cuit.xsgw.R;
import cuit.xsgw.adapter.MedicalUserAdapter;

import cuit.xsgw.net.Api;
import cuit.xsgw.net.Http;
import cuit.xsgw.net.bean.MedicalUser;
import cuit.xsgw.utils.ToastUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TNBListFragment extends Fragment {
    View view;
    RecyclerView listView;
    List<MedicalUser> users;
    MedicalUserAdapter medicalUserAdapter;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.list_frag, container, false);
        listView = view.findViewById(R.id.user_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        listView.setLayoutManager(layoutManager);
        medicalUserAdapter = new MedicalUserAdapter();
        listView.setAdapter(medicalUserAdapter);
        new Thread(() -> requestData()).start();
        return view;
    }

    void requestData() {
        Gson gson = new Gson();
        new Thread(()->
        {
            try {
                new Http().Get(Api.GetTNBUser, null, null, new Callback() {
                    @Override
                    public void onFailure(@org.greenrobot.greendao.annotation.NotNull Call call, @org.greenrobot.greendao.annotation.NotNull IOException e) {
                        getActivity().runOnUiThread(() ->ToastUtil.show(view, e.getMessage()));
                    }

                    @Override
                    public void onResponse(@org.greenrobot.greendao.annotation.NotNull Call call, @NotNull Response response) throws IOException {
                        if (response.isSuccessful()) {
                            users = gson.fromJson(response.body().string(), new TypeToken<List<MedicalUser>>() {
                            }.getType());
                            medicalUserAdapter.addAll(users);
                            getActivity().runOnUiThread(() -> medicalUserAdapter.notifyDataSetChanged());
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
}