package com.example.bottomnavigationwithfragment.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.bottomnavigationwithfragment.R;
import com.example.bottomnavigationwithfragment.large_classify_fragment.ServiceFragment;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {
    private ViewGroup rootView;
    private ViewPager viewPager;
    FragmentManager fragmentManager;
    Fragment serviceFragment;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup)inflater.inflate(R.layout.home_fragment,container,false);
        serviceFragment = new ServiceFragment();
        fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction().add(R.id.homecontainer,serviceFragment).commit();
        return rootView;
    }



}
