package com.zfj.android.groupbuyingactivity.Network;

import android.util.Log;

import com.zfj.android.groupbuyingactivity.Data.FoodDetail;
import com.zfj.android.groupbuyingactivity.Tools.GetJSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



/**
 * Created by zfj_ on 2017/5/31.
 */

public class GetDetail {

    public static final String COM_ZFJ_ANDROID_GROUPBUYINGACTIVITY_DATA = "com.zfj.android.groupbuyingactivity.Data.FoodDetail";
    private static final String TAG = "GetDetail";
    public static void getDetail(final String url, final getDetailListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL mUrl = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) mUrl.openConnection();
                    con.setRequestMethod("GET");
                    con.setConnectTimeout(10 * 1000);
                    if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        InputStream is = con.getInputStream();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        byte[] bytes = new byte[1024];
                        int len;
                        while ((len = is.read(bytes)) > -1) {
                            baos.write(bytes, 0, len);
                        }
                        String result = baos.toString();
                        FoodDetail foodDetail = (FoodDetail) GetJSONObject.getJSONObject(result,
                                Class.forName(COM_ZFJ_ANDROID_GROUPBUYINGACTIVITY_DATA));
                        if(foodDetail != null && listener != null){
                            listener.onSuccess(foodDetail);
                        }

                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    if(listener != null){
                        listener.onFail(e.getMessage());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    if(listener != null){
                        listener.onFail(e.getMessage());
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    if(listener != null){
                        listener.onFail(e.getMessage());
                    }
                }
            }
        }).start();

    }

    public interface getDetailListener {
        void onSuccess(FoodDetail foodDetail);

        void onFail(String msg);
    }
}
