package com.appestan.moodsaroundme;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created with love and care by shervin on 03/15/2018.
 */

public class Adapter extends RecyclerView.Adapter<CustomViewHolder> {
    private ArrayList<Friend> friendArrayList;

    public void setFriendArrayList(ArrayList<Friend> buddyArrayList) {
        this.friendArrayList = buddyArrayList;
    }

    public Adapter(ArrayList<Friend> buddyArrayList) {
        this.friendArrayList = buddyArrayList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.cell_layout, parent, false);

        CustomViewHolder viewHolder = new CustomViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.userName.setText(friendArrayList.get(position).getName());
        holder.cycle_1.setText(friendArrayList.get(position).calculateCycle1() + " %");
        holder.cycle_2.setText(friendArrayList.get(position).calculateCycle2() + " %");
        holder.cycle_3.setText(friendArrayList.get(position).calculateCycle3() + " %");
    }

    @Override
    public int getItemCount() {
        return friendArrayList.size();
    }
}
