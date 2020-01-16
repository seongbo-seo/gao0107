package com.example.bottomnavigationwithfragment;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewDeco extends RecyclerView.ItemDecoration {

    private int divHeigth;

    public RecyclerViewDeco(int divHeigth){
        this.divHeigth = divHeigth;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = divHeigth;
    }
}
