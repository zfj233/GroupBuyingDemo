package com.zfj.android.groupbuyingactivity.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.zfj.android.groupbuyingactivity.Adapter.MainGridAdapter;
import com.zfj.android.groupbuyingactivity.Adapter.MainRecyclerAdapter;
import com.zfj.android.groupbuyingactivity.Data.Business;
import com.zfj.android.groupbuyingactivity.Data.FoodDetail;
import com.zfj.android.groupbuyingactivity.Data.MainFood;
import com.zfj.android.groupbuyingactivity.Data.NetFood;
import com.zfj.android.groupbuyingactivity.Network.GetDetail;
import com.zfj.android.groupbuyingactivity.Network.MainAsyncHelper;
import com.zfj.android.groupbuyingactivity.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zfj_ on 2017/5/29.
 */

public class MainActivity extends Activity {
    public static final String GET_FOOD_LIST = "http://www.imooc.com/api/shopping?type=11";
    private GridView mGridView;
    private RecyclerView mRecyclerView;
    List<Business> listBusiness = new ArrayList<>();
    private static final String TAG = "MainActivity";
    List<MainFood> listFoods = new ArrayList<>();
    MainRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        setAdapters();
    }

    private void setAdapters() {
        mGridView.setAdapter(new MainGridAdapter(this, listBusiness));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mAdapter = new MainRecyclerAdapter(MainActivity.this, listFoods);
        mAdapter.setOnItemClickListener(new MainRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(MainActivity.this, DetailActivity.class));
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initView() {
        mGridView = (GridView) findViewById(R.id.main_grid_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
    }

    private void initData() {
        Business business = new Business("飞机", getResources().getDrawable(R.drawable.fly1));
        listBusiness.add(business);
        business = new Business("车票", getResources().getDrawable(R.drawable.car));
        listBusiness.add(business);
        business = new Business("汽车", getResources().getDrawable(R.drawable.autombile1));
        listBusiness.add(business);
        business = new Business("蛋糕", getResources().getDrawable(R.drawable.car));
        listBusiness.add(business);
        business = new Business("美食", getResources().getDrawable(R.drawable.food));
        listBusiness.add(business);
        business = new Business("手表", getResources().getDrawable(R.drawable.watch));
        listBusiness.add(business);
        business = new Business("电脑", getResources().getDrawable(R.drawable.cp));
        listBusiness.add(business);
        business = new Business("手机", getResources().getDrawable(R.drawable.phone));
        listBusiness.add(business);


        MainAsyncHelper.RequestFood(GET_FOOD_LIST, new MainAsyncHelper.onRequestListener() {
            @Override
            public void onSuccess(ArrayList<NetFood> list) {
                for (int i = 0; i < list.size(); i++) {
                    MainFood food = new MainFood();
                    food.setFoodImg(list.get(i).getImg());
                    food.setFoodName(list.get(i).getName());
                    food.setFoodDes(list.get(i).getDescription());
                    food.setFoodPrice(list.get(i).getPrice());
                    food.setFoodSale(list.get(i).getAction());
                    food.setFoodCount(String.valueOf(list.get(i).getCount()));
                    listFoods.add(food);
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail(String msg) {
                Log.e(TAG, msg);
            }
        });
    }
}
