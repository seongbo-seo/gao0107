package com.example.bottomnavigationwithfragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bottomnavigationwithfragment.retrofit.RetrofitConnection;
import com.example.bottomnavigationwithfragment.retrofit.RetrofitInterface;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        Log.e("b",b+"");
        Toast.makeText(getApplicationContext(),"분류는 다음과 같습니다."+a+"의"+b,Toast.LENGTH_LONG).show();

        Toolbar tb1 = findViewById(R.id.toolbar1);
        setSupportActionBar(tb1);
        getSupportActionBar().setTitle(b);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_24px);



        //use retrofit2
        RetrofitConnection retrofitConnection = new RetrofitConnection();
        RetrofitInterface retrofitInterface = retrofitConnection.retrofit.create(RetrofitInterface.class);
        retrofitInterface.getData().enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                JsonArray body = response.body();
                Log.e("response success","success");
                Log.e("response size",body.size()+"");
                if(body!=null){
                    Log.e("response contents",body.toString());
                    for(int i=0;i<body.size();i++){
                        JsonObject object= body.get(i).getAsJsonObject();
                        String storename = object.get("title").getAsString();
                        String storetime = object.get("completed").getAsString();
                        String storetoday = object.get("id").getAsString();
                        StoreItem item = new StoreItem("no",storename,storetime,storetoday);
                        mArrayList.add(item);
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Log.e("fail","fail");
            }
        });



        mArrayList = new ArrayList<>();


        recyclerView = (RecyclerView)findViewById(R.id.resultlist_recyclerview);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);

        //구분선 추가하기
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(),new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        //간격 조절
        //RecyclerViewDeco deco = new RecyclerViewDeco(100);
        //recyclerView.addItemDecoration(deco);

        mAdapter = new ResultRecyclerViewAdapter(mArrayList);
        recyclerView.setAdapter(mAdapter);

//        for(int i = 0; i<20;i++) {
//            StoreItem storeItem = new StoreItem("hi", i+"교촌치킨", "12:00~23:00", "콜라 500ml"+i+"병 무료증정");
//            mArrayList.add(storeItem);
//            mAdapter.notifyDataSetChanged();
//        }
//            Log.e("배열크기",mArrayList.size()+"");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }

        }
        return super.onOptionsItemSelected(item);
    }
}
