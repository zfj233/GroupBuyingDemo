package com.zfj.android.groupbuyingactivity.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zfj.android.groupbuyingactivity.Data.FoodDetail;
import com.zfj.android.groupbuyingactivity.Network.GetDetail;
import com.zfj.android.groupbuyingactivity.R;

/**
 * Created by zfj_ on 2017/5/30.
 */

public class DetailActivity extends Activity {
    public static final String DETAIL_URL = "http://www.imooc.com/api/shopping?type=12";
    public static final int GET_DETAIL_CODE = 10001;
    private ImageView detailFoodImg;
    private TextView detailFoodName;
    private TextView detailPrice;
    private TextView detailTPrice;
    private TextView detailJPrice;
    private TextView detailMore;
    private static final String TAG = "DetailActivity";
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == GET_DETAIL_CODE) {
                FoodDetail foodDetail = (FoodDetail) msg.obj;
                detailFoodName.setText(foodDetail.getName());
                Glide.with(DetailActivity.this)
                        .load(foodDetail.getImg())
                        .placeholder(R.mipmap.ic_launcher)
                        .into(detailFoodImg);
                detailJPrice.setText("￥" + foodDetail.getPrice() + "拿下");
                detailTPrice.setText("团购价: ￥" + String.valueOf(foodDetail.gettPrice()));
                detailPrice.setText("￥" + String.valueOf(foodDetail.getOriginalprice()));
                detailMore.setText(foodDetail.getDescription());

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_food);
        initView();
        initData();
    }

    private void initView() {
        detailFoodImg = (ImageView) findViewById(R.id.detail_food_img);
        detailFoodName = (TextView) findViewById(R.id.detail_food_name);
        detailPrice = (TextView) findViewById(R.id.detail_food_price);
        detailTPrice = (TextView) findViewById(R.id.detail_food_tprice);
        detailJPrice = (TextView) findViewById(R.id.detail_food_jprice);
        detailMore = (TextView) findViewById(R.id.more_detail);
        detailMore.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    private void initData() {
        GetDetail.getDetail(DETAIL_URL, new GetDetail.getDetailListener() {
            @Override
            public void onSuccess(FoodDetail foodDetail) {
                Message message = handler.obtainMessage();
                message.what = GET_DETAIL_CODE;
                message.obj = foodDetail;
                handler.sendMessage(message);
            }

            @Override
            public void onFail(String msg) {
                Log.e(TAG, msg);
            }
        });
    }

}
