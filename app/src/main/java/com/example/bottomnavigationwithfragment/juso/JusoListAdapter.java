package com.example.bottomnavigationwithfragment.juso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bottomnavigationwithfragment.R;

import java.util.ArrayList;

public class JusoListAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater = null;
    ArrayList<JusoItem> jusoItems;

    public JusoListAdapter(Context context, ArrayList<JusoItem> data){
        mContext=context;
        jusoItems=data;
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return jusoItems.size();
    }

    @Override
    public JusoItem getItem(int position) {
        return jusoItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.juso_item,null);
        TextView mainJuso = (TextView)view.findViewById(R.id.main_juso);
        TextView subJuso = (TextView)view.findViewById(R.id.sub_juso);

        mainJuso.setText(jusoItems.get(position).getMainJuso());
        subJuso.setText(jusoItems.get(position).getSubJuso());

        return view;
    }
}
