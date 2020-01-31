package com.example.bottomnavigationwithfragment.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.bottomnavigationwithfragment.MainActivity;
import com.example.bottomnavigationwithfragment.R;
import com.example.bottomnavigationwithfragment.ResultRecyclerViewAdapter;
import com.example.bottomnavigationwithfragment.SingletonHolder;
import com.example.bottomnavigationwithfragment.StoreItem;
import com.example.bottomnavigationwithfragment.retrofit.RetrofitConnection;
import com.example.bottomnavigationwithfragment.retrofit.RetrofitInterface;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteFragment extends Fragment {
    private ViewGroup rootView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<StoreItem> favoriteArrayList;
    private RecyclerView.LayoutManager layoutManager;

    SingletonHolder singletonHolder = SingletonHolder.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup)inflater.inflate(R.layout.favorit_fragment,container,false);

        //이 부분을 좋아요 목록 테이블을 불러서 구현하면 됨
//        RetrofitConnection retrofitConnection = new RetrofitConnection();
//        RetrofitInterface retrofitInterface = retrofitConnection.retrofit.create(RetrofitInterface.class);
//        retrofitInterface.getData().enqueue(new Callback<JsonArray>() {
//            @Override
//            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
//                JsonArray body = response.body();
//                Log.e("response success","success");
//                Log.e("response size",body.size()+"");
//                if(body!=null){
//                    Log.e("response contents",body.toString());
//                    for(int i=0;i<body.size();i++){
//                        JsonObject object= body.get(i).getAsJsonObject();
//                        String storename = object.get("title").getAsString();
//                        String storetime = object.get("completed").getAsString();
//                        String storetoday = object.get("id").getAsString();
//                        StoreItem item = new StoreItem("no",storename,storetime,storetoday);
//                        mArrayList.add(item);
//                        mAdapter.notifyDataSetChanged();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<JsonArray> call, Throwable t) {
//                Log.e("fail","fail");
//            }
//        });
//
//        Intent intent =((MainActivity)getActivity()).getIntent();
//        String name =intent.getStringExtra("storeName");
//        String time =intent.getStringExtra("storeTime");
//        String today=intent.getStringExtra("storeToday");

        if (favoriteArrayList == null){
            favoriteArrayList = singletonHolder.favoriteArrayList;

        }
//        favoriteArrayList.add(new StoreItem("null",name,time,today));
        if(favoriteArrayList!=null){
            Log.e("arrinfragment",favoriteArrayList.toString());
        Context context = rootView.getContext();
        recyclerView = (RecyclerView)rootView.findViewById(R.id.favoritelist_recyclerview);

        //구분선 추가하기
        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(recyclerView.getContext(),new LinearLayoutManager(context).getOrientation());

        recyclerView.addItemDecoration(dividerItemDecoration);
        mAdapter = new ResultRecyclerViewAdapter(favoriteArrayList);
        mAdapter.notifyDataSetChanged();
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        if(!singletonHolder.favoriteArrayList.isEmpty()){mAdapter.notifyDataSetChanged();}
        }

        return rootView;

    }

}
