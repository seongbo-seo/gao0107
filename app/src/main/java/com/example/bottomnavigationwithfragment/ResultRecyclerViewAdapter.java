package com.example.bottomnavigationwithfragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ResultRecyclerViewAdapter extends RecyclerView.Adapter<ResultRecyclerViewAdapter.ResultViewHolder> {

    public static ArrayList<StoreItem> mDataSet;

    public static class ResultViewHolder extends RecyclerView.ViewHolder{
       private ImageView storeImage;
       private TextView storeName, storeTime, storeToday;


        public ResultViewHolder(View itemView){
            super(itemView);
//            v.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.e("recyclerviewItem",v.getId()+"클릭");
//                }
//            });
            storeImage = (ImageView)itemView.findViewById(R.id.store_image);
            storeName = (TextView)itemView.findViewById(R.id.store_name);
            storeTime = (TextView)itemView.findViewById(R.id.store_time);
            storeToday = (TextView)itemView.findViewById(R.id.store_today);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Toast.makeText(v.getContext(),"아이템선택"+getAdapterPosition(),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(context,StoreDetail.class);
                    StoreItem storeItem = mDataSet.get(getAdapterPosition());
                    intent.putExtra("storeName",storeItem.getStoreName());
                    intent.putExtra("storeTime",storeItem.getStoreTime());
                    intent.putExtra("storeToday",storeItem.getStoreToday());
                    Log.e("position item",storeItem.getStoreName());
                    ((ResultListActivity)context).startActivityForResult(intent,0);
                    Log.e("intent activate","intent act");

                }
            });

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
       //데이터 관리에 집중
        holder.storeName.setText(mDataSet.get(position).getStoreName());
        holder.storeTime.setText(mDataSet.get(position).getStoreTime());
        holder.storeToday.setText(mDataSet.get(position).getStoreToday());

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
