package com.altair.Rybari.main.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.altair.Rybari.R;


import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecylerViewAdapter";
    private ArrayList<Integer> asdImages = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapter(ArrayList<Integer> ads, Context context) {
        asdImages = ads;
        context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.myAds.setImageResource((asdImages.get(position)));
    }

    @Override
    public int getItemCount() {
        return asdImages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView myAds;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myAds = itemView.findViewById(R.id.ads_imageview);
        }
    }
}
