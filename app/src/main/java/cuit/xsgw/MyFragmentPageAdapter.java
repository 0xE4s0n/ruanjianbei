package cuit.xsgw;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class MyFragmentPageAdapter extends FragmentPagerAdapter {
    private FragmentManager mfragmentManager;
    private List<Fragment> mlist;
    private String[] mtabNames;

    public MyFragmentPageAdapter(FragmentManager fm, int behavior, List<Fragment> list, String[] tabNames) {
        super(fm, behavior);
        mfragmentManager = fm;
        mlist = list;
        mtabNames = tabNames;
    }

    @Override
    public Fragment getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //返回tab选项的名字
        return mtabNames[position];
    }
}
