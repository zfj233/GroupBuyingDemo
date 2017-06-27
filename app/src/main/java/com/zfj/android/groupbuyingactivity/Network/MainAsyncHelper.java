package com.zfj.android.groupbuyingactivity.Network;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zfj.android.groupbuyingactivity.Data.NetFood;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by zfj_ on 2017/5/30.
 */

public class MainAsyncHelper {
    public static void RequestFood(String url, onRequestListener listener){
        MainAsyncTask task = new MainAsyncTask(url, listener);
        task.execute();
    }
    public interface onRequestListener{
        void onSuccess(ArrayList<NetFood> list);
        void onFail(String msg);
    }
    public static class MainAsyncTask extends AsyncTask<String, Void, ArrayList<NetFood>> {
        private static final String TAG = "MainAsyncTask";
        private String mUrl;
        private onRequestListener mListener;
        public MainAsyncTask(String url, onRequestListener listener) {
            mUrl = url;
            mListener = listener;
        }

        @Override
        protected ArrayList<NetFood> doInBackground(String... params) {
            if(mUrl.equals("")){
                if(mListener != null){
                    mListener.onFail("url为空！");
                }
            }
            try {
                URL url = new URL(mUrl);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setConnectTimeout(10 * 100);
                if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream is = con.getInputStream();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int len;
                    byte[] buf = new byte[1024];
                    while ((len = is.read(buf)) > -1) {
                        baos.write(buf, 0, len);
                    }
                    String result = baos.toString();
                    JSONObject job = new JSONObject(result);
                    if (job.getInt("status") == 1 && job.getString("msg").equals("成功")) {
                        String data = job.getString("data");
                        Gson gson = new Gson();
                        ArrayList<NetFood> foods = gson.fromJson(data,
                                new TypeToken<ArrayList<NetFood>>() {
                                }.getType());
                        return foods;
                    }else{
                        if(mListener != null){
                            mListener.onFail("status，msg异常");
                        }
                    }
                }else {
                    if(mListener != null){
                        mListener.onFail("请求失败");
                    }
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
                if(mListener != null){
                    mListener.onFail(e.getMessage());
                }
            } catch (IOException e) {
                e.printStackTrace();
                if(mListener != null){
                    mListener.onFail(e.getMessage());
                }
            } catch (JSONException e) {
                e.printStackTrace();
                if(mListener != null){
                    mListener.onFail(e.getMessage());
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<NetFood> netFoods) {
            super.onPostExecute(netFoods);
            if (netFoods == null) {
                if(mListener != null){
                    mListener.onFail("Null Foods");
                }
                return;
            }
            if(mListener != null){
                mListener.onSuccess(netFoods);
            }
        }
    }
}
