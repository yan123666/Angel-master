package com.example.wenews;

import android.util.Log;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;



public class channelPager extends FragmentStatePagerAdapter {
    List<Fragment> fragmentList=new ArrayList<>();
    LegalActivity mContext;
    public  channelPager(FragmentManager fm , List<Fragment> list, LegalActivity mContext)
    {
        super(fm);
        fragmentList=list;
        this.mContext=mContext;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "财经";
            case 1:
                return "军事";
            case 2:
                return "娱乐";
            case 3:
                return "国际";
            case 4:
                return "头条";
            case 5:
                return "News";
            default:
                return "微博";
        }
    }
    @Override
    public Fragment getItem(int position) {
        Log.d("getItem","good");

        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList!= null ? fragmentList.size() : 0;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        LegalActivity.tagPointer=position;
    }
}
