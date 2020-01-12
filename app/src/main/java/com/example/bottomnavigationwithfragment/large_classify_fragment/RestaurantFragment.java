package com.example.bottomnavigationwithfragment.large_classify_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bottomnavigationwithfragment.R;

public class RestaurantFragment extends Fragment {
private ViewGroup rootVeiw;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootVeiw = (ViewGroup)inflater.inflate(R.layout.restaurant_fragment,container,false);
        return rootVeiw;
    }
}
