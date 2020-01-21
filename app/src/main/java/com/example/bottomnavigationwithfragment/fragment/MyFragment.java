package com.example.bottomnavigationwithfragment.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bottomnavigationwithfragment.R;
import com.example.bottomnavigationwithfragment.StoreSignUp;

public class MyFragment extends Fragment {
    private ViewGroup rootView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup)inflater.inflate(R.layout.my_fragment,container,false);

        String [] list_menu ={"공지사항","이벤트","광고 문의","1:1 문의","현재버전"};
        ListView listView = (ListView)rootView.findViewById(R.id.my_list);
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,list_menu);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(id ==2){
                Intent intent = new Intent(getContext(), StoreSignUp.class);
                startActivity(intent);
                }
            }
        });

        return rootView;

    }
}
