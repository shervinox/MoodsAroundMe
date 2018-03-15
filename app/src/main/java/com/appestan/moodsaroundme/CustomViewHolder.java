package com.appestan.moodsaroundme;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created with love and care by shervin on 03/15/2018.
 */

public class CustomViewHolder extends RecyclerView.ViewHolder {
    public TextView userName;
    public TextView cycle_1;
    public TextView cycle_2;
    public TextView cycle_3;

    public CustomViewHolder(View itemView) {
        super(itemView);
        userName = itemView.findViewById(R.id.name_cell_item);
        cycle_1 = itemView.findViewById(R.id.cycle1_textview);
        cycle_2 = itemView.findViewById(R.id.cycle2_textview);
        cycle_3 = itemView.findViewById(R.id.cycle3_textview);
    }
}
