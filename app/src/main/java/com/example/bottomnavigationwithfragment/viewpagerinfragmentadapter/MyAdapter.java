package com.example.bottomnavigationwithfragment.viewpagerinfragmentadapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.bottomnavigationwithfragment.large_classify_fragment.EduFragment;
import com.example.bottomnavigationwithfragment.large_classify_fragment.EntertainFragment;
import com.example.bottomnavigationwithfragment.large_classify_fragment.MediFragment;
import com.example.bottomnavigationwithfragment.large_classify_fragment.RestaurantFragment;
import com.example.bottomnavigationwithfragment.large_classify_fragment.RetailFragment;
import com.example.bottomnavigationwithfragment.large_classify_fragment.ServiceFragment;

public class MyAdapter extends FragmentStatePagerAdapter {

    String title[] ={"생활서비스","소매/유통","여가/오락","음식","의료/건강","학문/교육"};

    public MyAdapter(FragmentManager fm){
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Fragment fragment =new ServiceFragment();
                return fragment;
            case 1:
                return new RetailFragment();
            case 2:
                return new EntertainFragment();
            case 3:
                return new RestaurantFragment();
            case 4:
                return new MediFragment();
            case 5:
                return new EduFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
