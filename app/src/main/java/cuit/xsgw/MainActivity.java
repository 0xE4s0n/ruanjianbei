package cuit.xsgw;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.List;

import cuit.xsgw.fragment.GXYRecordFragment;
import cuit.xsgw.fragment.TNBRecordFragment;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private MyFragmentPageAdapter adapter;
    private TabLayout tabs;
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
        fragments = new ArrayList<>();
        fragments.add(new TNBRecordFragment());
        fragments.add(new GXYRecordFragment());
        mTabNames = new String[]{getApplication().getResources().getString(R.string.tnb), getApplication().getResources().getString(R.string.gxy)};
        adapter = new MyFragmentPageAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments, mTabNames);
        viewPager.setAdapter(adapter);
        tabs = findViewById(R.id.tnb_table);
        tabs.setupWithViewPager(viewPager);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        navView.setCheckedItem(R.id.start);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.start:
                        if (frontView != R.id.start) {
                            fragments = new ArrayList<>();
                            fragments.add(new TNBRecordFragment());
                            fragments.add(new GXYRecordFragment());
                            SwitchWorkView(fragments);
                        }
                        break;
                    case R.id.list:
                        if (frontView != R.id.list) {
                            //fragments = new ArrayList<>();
                            //fragments.add(new TNBListFragment());
                            //fragments.add(new GXYListFragment());
                            SwitchWorkView(fragments);
                        }
                        break;
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "FAB clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void SwitchWorkView(List<Fragment> fragments) {
        adapter = new MyFragmentPageAdapter(getSupportFragmentManager(), BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments, mTabNames);
        viewPager.setAdapter(adapter);
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
}
