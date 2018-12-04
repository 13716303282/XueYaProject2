package com.qc.cn.xueya.http;



import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by smz on 2018/11/7.
 */

public  class OkHttpUitls<T> {

    private static OkHttpUitls OkHttpUitls =new OkHttpUitls<>();

    private OkHttpUitls(){

    }

    public  static OkHttpUitls getInstance( ){
           return OkHttpUitls;
    }


    /**
     *
     * @param url
//     * @param clazz   //解析成什么类型
     * @param map     //post
     * @param requestCallBack   //接口
     */
    public void  post(String url, Map<String ,String> map, final RequestCallBack<String> requestCallBack){
           OkHttpClient client=new OkHttpClient.Builder().build();
           FormBody.Builder  builder=new FormBody.Builder();
           Set<String> set= map.keySet();
           Iterator<String> iterator=set.iterator();
            while (iterator.hasNext()){
              String key=iterator.next();
              String value= map.get(key);
              builder.add(key,value);
            }

         Request  request=new Request.Builder().url(url).post(builder.build()).
                build();

         Call call= client.newCall(request);
         call.enqueue(new Callback() {  //异步请求
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                requestCallBack.fail(call,  e);
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str=response.body().string();
                requestCallBack.sucess(call,str);

            }
        });
    }

    public  void  get(String url,final RequestCallBack<String> requestCallBack){
        OkHttpClient client=new OkHttpClient.Builder().build();

        Request  request=new Request.Builder().url(url).build();

        Call call= client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                requestCallBack.fail(call ,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str=response.body().string();
                requestCallBack.sucess(call,str);
            }
        });
    }

    public  void   updateFile(String url , File file, final RequestCallBack<String> callBack){
        OkHttpClient client=new OkHttpClient.Builder().build();
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);//文件类型
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)  //Form 逼到单
                .addFormDataPart("file", file.getName(), fileBody)
                .addFormDataPart("button","submit")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Call call=  client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                callBack.fail(call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.sucess(call,response.body().string());
            }
        });
    }

}
