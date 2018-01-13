package com.sara.navigationtask;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by sara on 1/11/2018.
 */


public class RecyclerAdapter extends RecyclerView.Adapter <RecyclerAdapter.CustomViewHolder>{

    ArrayList<ItemModel> rowItem = new ArrayList<>();
    private static Context context;

    ItemClickListener itemClickListener;
    int rowIndex = -1;

    public RecyclerAdapter (Context context , ArrayList<ItemModel> rowItem){
        this.context = context;
        this.rowItem = rowItem;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item , parent ,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {

        holder.itemName.setText(rowItem.get(position).getItemText());
        Picasso.with(context).load(rowItem.get(position).getIconImage()).resize(50,50).into(holder.icon);

        //set Background of rowItemSelected
        if (rowIndex == position){
            holder.rowItem.setBackgroundColor( Color.parseColor("#006064"));
        } else{
            holder.rowItem.setBackgroundColor(Color.parseColor("#4DD0E1"));
        }

    }

    @Override
    public int getItemCount() {
        return rowItem.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        LinearLayout rowItem;
        TextView itemName;
        ImageView icon;

        public CustomViewHolder(View itemView) {
            super(itemView);

            rowItem = (LinearLayout) itemView.findViewById(R.id.row);
            itemName = (TextView) itemView.findViewById(R.id.tv_item);
            icon = (ImageView) itemView.findViewById(R.id.icon_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onClick(view , getAdapterPosition());
                    rowIndex=getAdapterPosition();
                    notifyDataSetChanged();
                }
            });
        }
    }

    public void setItemClickLister(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

}
