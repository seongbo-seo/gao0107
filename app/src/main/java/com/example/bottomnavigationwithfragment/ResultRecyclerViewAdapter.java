package com.example.bottomnavigationwithfragment;

import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ResultRecyclerViewAdapter extends RecyclerView.Adapter<ResultRecyclerViewAdapter.ResultViewHolder> {

    private ArrayList<StoreItem> mDataSet;

    public static class ResultViewHolder extends RecyclerView.ViewHolder {
       private ImageView storeImage;
       private TextView storeName, storeTime, storeToday;

        public ResultViewHolder(View v){
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("recyclerviewItem",v.getId()+"클릭");
                }
            });
            storeImage = (ImageView)v.findViewById(R.id.store_image);
            storeName = (TextView)v.findViewById(R.id.store_name);
            storeTime = (TextView)v.findViewById(R.id.store_time);
            storeToday = (TextView)v.findViewById(R.id.store_today);
        }
    }

    public ResultRecyclerViewAdapter(ArrayList<StoreItem> list){
        mDataSet=list;
    }

    @NonNull
    @Override
    public ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //뷰를 생성
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.resultrecyclerview_item, parent, false);

        return new ResultViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultViewHolder holder, int position) {
        holder.storeName.setText(mDataSet.get(position).getStoreName());
        holder.storeTime.setText(mDataSet.get(position).getStoreTime());
        holder.storeToday.setText(mDataSet.get(position).getStoreToday());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
