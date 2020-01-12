package com.example.bottomnavigationwithfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class ResultListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<StoreItem> mArrayList;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_list);

        Intent intent = getIntent();
        String a = intent.getStringExtra("dae");
        String b = intent.getStringExtra("joong");

        Toast.makeText(getApplicationContext(),"분류는 다음과 같습니다."+a+"의"+b,Toast.LENGTH_LONG).show();
        mArrayList = new ArrayList<>();


        recyclerView = (RecyclerView)findViewById(R.id.resultlist_recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ResultRecyclerViewAdapter(mArrayList);
        recyclerView.setAdapter(mAdapter);

        for(int i = 0; i<20;i++) {
            StoreItem storeItem = new StoreItem("hi", i+"교촌치킨", "12:00~23:00", "콜라 500ml"+i+"병 무료증정");
            mArrayList.add(storeItem);
            mAdapter.notifyDataSetChanged();
        }
            Log.e("배열크기",mArrayList.size()+"");
    }
}
