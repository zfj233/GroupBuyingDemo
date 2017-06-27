package com.zfj.android.groupbuyingactivity.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zfj.android.groupbuyingactivity.Data.Business;
import com.zfj.android.groupbuyingactivity.R;

import java.util.List;

/**
 * Created by zfj_ on 2017/5/29.
 */

public class MainGridAdapter extends BaseAdapter {
    Context mContext;
    List<Business> listBusiness;
    ViewHolder viewHolder;
    public MainGridAdapter(Context mContext, List<Business> listBusiness) {
        this.listBusiness = listBusiness;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return listBusiness.size();
    }

    @Override
    public Object getItem(int position) {
        return listBusiness.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item_view, null);
            viewHolder = new ViewHolder();
            viewHolder.mName = (TextView) convertView.findViewById(R.id.grid_item_text);
            viewHolder.mBitmap = (ImageView) convertView.findViewById(R.id.grid_item_img);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mName.setText(listBusiness.get(position).getBusinessName());
        viewHolder.mBitmap.setImageDrawable(listBusiness.get(position).getBusinessPic());
        return convertView;
    }
    class ViewHolder {
        TextView mName;
        ImageView mBitmap;
    }
}
