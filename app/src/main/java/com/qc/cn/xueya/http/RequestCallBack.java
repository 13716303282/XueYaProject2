package com.qc.cn.xueya.http;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by smz on 2018/11/7.
 */

public interface RequestCallBack<String> {

    /**
     * 请求成功时的回调
     * @param call
     * @param t
     */
    public  void  sucess(Call call ,String t);//

    public  void  fail(Call call, IOException e);
}
