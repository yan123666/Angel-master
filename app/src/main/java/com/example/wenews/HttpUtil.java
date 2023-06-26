package com.example.wenews;

import okhttp3.OkHttpClient;
import okhttp3.Request;



public class HttpUtil {
    public static void sendOkhttpRequest(String address,okhttp3.Callback callback){
        //建立RequestBody对象存放待提交的参数，参数有 apikey,text,userid.

        OkHttpClient client =new OkHttpClient();
        Request request=new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);//enqueue方法在内部开好了子线程并最终将结果回调到okhttp3.Callback当中。

    }
}
