package cuit.xsgw;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cuit.xsgw.fragment.AddUserDialogFragment;
import cuit.xsgw.fragment.GXYListFragment;
import cuit.xsgw.fragment.GXYRecordFragment;
import cuit.xsgw.fragment.TNBListFragment;
import cuit.xsgw.fragment.TNBRecordFragment;
import cuit.xsgw.net.Api;
import cuit.xsgw.net.Http;
import cuit.xsgw.utils.ToastUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class MainActivity extends AppCompatActivity implements AddUserDialogFragment.AddUserCallback {
    private DrawerLayout mDrawerLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private FragmentPageAdapter adapter;
    private AddUserDialogFragment addUserDialogFragment;
    private TabLayout tabs;
    private FloatingActionButton fab;
    String[] mTabNames;
    private int frontView = R.id.start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.main_drawer);
        final NavigationView navView = findViewById(R.id.nav_view);
        viewPager = findViewById(R.id.main_viewpager);
        fab = findViewById(R.id.fab);
        fragments = new ArrayList<>();
        fragments.add(new TNBRecordFragment(getSupportFragmentManager()));
        fragments.add(new GXYRecordFragment(getSupportFragmentManager()));
        mTabNames = new String[]{getApplication().getResources().getString(R.string.tnb), getApplication().getResources().getString(R.string.gxy)};
        adapter = new FragmentPageAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments, mTabNames);
        viewPager.setAdapter(adapter);
        tabs = findViewById(R.id.tnb_table);
        tabs.setupWithViewPager(viewPager);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        navView.setCheckedItem(R.id.start);
        navView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.start:
                    if (frontView != R.id.start) {
                        fragments = new ArrayList<>();
                        fragments.add(new TNBRecordFragment(getSupportFragmentManager()));
                        fragments.add(new GXYRecordFragment(getSupportFragmentManager()));
                        SwitchWorkView(fragments);
                        fab.setImageResource(R.drawable.ic_done);
                        frontView = R.id.start;
                    }
                    break;
                case R.id.list:
                    if (frontView != R.id.list) {
                        fragments = new ArrayList<>();
                        fragments.add(new TNBListFragment());
                        fragments.add(new GXYListFragment());
                        SwitchWorkView(fragments);
                        fab.setImageResource(R.drawable.round_add_white_48);
                        frontView = R.id.list;
                    }
                    break;
            }
            mDrawerLayout.closeDrawers();
            return true;
        });
        fab.setOnClickListener(v ->
        {
            if (frontView == R.id.start) {
                //TODO commit information
                ToastUtil.show(v, "done");
                fragments = new ArrayList<>();
                fragments.add(new TNBRecordFragment(getSupportFragmentManager()));
                fragments.add(new GXYRecordFragment(getSupportFragmentManager()));
                SwitchWorkView(fragments);
            } else if (frontView == R.id.list) {
                CreatDialog();
                //ToastUtil.show(v,"add");
            }
        });
    }

    private void SwitchWorkView(List<Fragment> fragments) {
        adapter.setFragment(fragments);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
        }
        return true;
    }

    private void CreatDialog() {
        addUserDialogFragment = new AddUserDialogFragment((AddUserDialogFragment.AddUserCallback) this);
        addUserDialogFragment.show(getSupportFragmentManager(), "dialog");
    }

    @Override
    public void addUser(String id, String phone, String name, String address, String idCard, String passwd, String sex, String disease) {
        View view = getWindow().getDecorView().findViewById(R.id.fab);
        HashMap data = new HashMap<>();
        data.put("id", id);
        data.put("phone", phone);
        data.put("name", name);
        data.put("sex", sex);
        data.put("password", passwd);
        data.put("address", address);
        data.put("disease", disease);
        data.put("idCard", idCard);
        new Thread(() -> {
            try {
                new Http().Post(Api.AddUser, null, null, data, new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        ToastUtil.show(view, "添加失败：" + e.getMessage());
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if (response.isSuccessful()&Integer.parseInt(response.body().string())>0) {
                            ToastUtil.show(view, "添加成功");
                            fragments = new ArrayList<>();
                            fragments.add(new TNBListFragment());
                            fragments.add(new GXYListFragment());
                            runOnUiThread(()->SwitchWorkView(fragments));
                        } else
                            ToastUtil.show(view, "添加失败");
                    }
                });
            } catch (IOException e) {
                ToastUtil.show(view, e.getMessage());
            }
        }).start();
        addUserDialogFragment.dismiss();
    }

    @Override
    public void Erro(String e, View v) {
        ToastUtil.show(v, e);
    }

    private long backTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - backTime <= 2000) {
                finish();
            } else {
                backTime = System.currentTimeMillis();
                ToastUtil.show(getWindow().getDecorView().findViewById(R.id.fab), "再按一次退出");
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
