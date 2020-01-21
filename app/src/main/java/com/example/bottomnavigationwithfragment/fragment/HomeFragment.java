package com.example.bottomnavigationwithfragment.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.bottomnavigationwithfragment.viewpagerinfragmentadapter.MyAdapter;
import com.example.bottomnavigationwithfragment.R;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {
    MyAdapter myAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.home_fragment,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        myAdapter = new MyAdapter(getChildFragmentManager());
        viewPager = view.findViewById(R.id.home_container);
        viewPager.setAdapter(myAdapter);
        tabLayout = view.findViewById(R.id.large_align);
        tabLayout.setupWithViewPager(viewPager);



    }
}
