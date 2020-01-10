package com.example.bottomnavigationwithfragment.large_classify_fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bottomnavigationwithfragment.R;
import com.example.bottomnavigationwithfragment.ResultListActivity;

public class ServiceFragment extends Fragment {
    public static final String ARG_OBJECT = "object";
    private ViewGroup rootVeiw;
    private ImageView barber,sauna,laundry,carfix,photo,gas;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootVeiw = (ViewGroup)inflater.inflate(R.layout.service_fragment,container,false);

        barber =rootVeiw.findViewById(R.id.barber_service);
        barber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ResultListActivity.class);
                intent.putExtra("dae","서비스");
                intent.putExtra("joong","미용실");
                startActivity(intent);


            }
        });


        return rootVeiw;
    }

}
