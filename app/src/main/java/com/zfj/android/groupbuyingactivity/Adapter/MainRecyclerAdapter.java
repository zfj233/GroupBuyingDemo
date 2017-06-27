package com.zfj.android.groupbuyingactivity.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zfj.android.groupbuyingactivity.Data.MainFood;
import com.zfj.android.groupbuyingactivity.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zfj_ on 2017/5/30.
 */

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MainRecyclerViewHolder> implements View.OnClickListener {
    Context mContext;
    List<MainFood> listFoods;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    private OnItemClickListener mOnItemClickListener = null;
    public MainRecyclerAdapter(Context mContext, List<MainFood> listFoods) {
        this.mContext = mContext;
        this.listFoods = listFoods;
    }

    @Override
    public MainRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.recycler_item_view, null);
        itemView.setOnClickListener(this);
        return new MainRecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MainRecyclerViewHolder holder, int position) {
        Glide.with(mContext)
                .load(listFoods.get(position).getFoodImg())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.foodImg);
        holder.foodName.setText(listFoods.get(position).getFoodName());
        holder.foodDes.setText(listFoods.get(position).getFoodDes());
        holder.foodPrice.setText("￥" + listFoods.get(position).getFoodPrice());
        holder.foodSale.setText(listFoods.get(position).getFoodSale());
        holder.foodCount.setText("已售:" + listFoods.get(position).getFoodCount());
        holder.itemView.setTag(position);
    }


    @Override
    public int getItemCount() {
        return listFoods.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    class MainRecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView foodImg;
        TextView foodName;
        TextView foodDes;
        TextView foodPrice;
        TextView foodSale;
        TextView foodCount;

        public MainRecyclerViewHolder(View itemView) {
            super(itemView);
            foodImg = (ImageView) itemView.findViewById(R.id.main_food_img);
            foodName = (TextView) itemView.findViewById(R.id.main_food_name);
            foodDes = (TextView) itemView.findViewById(R.id.main_description);
            foodPrice = (TextView) itemView.findViewById(R.id.main_price);
            foodSale = (TextView) itemView.findViewById(R.id.main_sale);
            foodCount = (TextView) itemView.findViewById(R.id.main_count);
        }
    }
    public interface OnItemClickListener {
        void onItemClick(View view , int position);
    }
}
