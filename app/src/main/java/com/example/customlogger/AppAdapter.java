package com.example.customlogger;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.ViewHolder>{

    private List<AppItem> appList;


    public AppAdapter(List<AppItem> appList) {
        this.appList = appList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_app, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppItem appItem = appList.get(position);
        holder.appNameTextView.setText(appItem.getAppName());
        holder.appIconImageView.setImageDrawable(appItem.getAppIcon());
    }

    @Override
    public int getItemCount() {
        return appList.size();
    }
    public void setDataList(ArrayList<AppItem> tempList) {
        this.appList=tempList;
        notifyDataSetChanged();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView appNameTextView;
        ImageView appIconImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            appNameTextView = itemView.findViewById(R.id.appNameTextView);
            appIconImageView = itemView.findViewById(R.id.appIconImageView);
        }
    }
}
