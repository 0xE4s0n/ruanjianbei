package cuit.xsgw;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class FragmentPageAdapter extends FragmentPagerAdapter {
    private FragmentManager mfragmentManager;
    private List<Fragment> mlist;
    private String[] mtabNames;

    public FragmentPageAdapter(FragmentManager fm, int behavior, List<Fragment> list, String[] tabNames) {
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
    public long getItemId(int position) {
        return mlist.get(position).hashCode();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        //返回tab选项的名字
        return mtabNames[position];
    }

    public void setFragment(List<Fragment> lists)
    {
        mlist = lists;
    }
}
