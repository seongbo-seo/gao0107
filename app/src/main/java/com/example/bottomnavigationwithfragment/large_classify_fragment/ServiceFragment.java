package com.example.bottomnavigationwithfragment.large_classify_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bottomnavigationwithfragment.R;

public class ServiceFragment extends Fragment {
    public static final String ARG_OBJECT = "object";
    private ViewGroup rootVeiw;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootVeiw = (ViewGroup)inflater.inflate(R.layout.service_fragment,container,false);
        return rootVeiw;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        ((TextView) view.findViewById(R.id.text1)).setText(Integer.toString(args.getInt(ARG_OBJECT)));
    }
}
