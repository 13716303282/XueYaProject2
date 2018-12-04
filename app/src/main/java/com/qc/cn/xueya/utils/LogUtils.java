package com.qc.cn.xueya.utils;

import android.content.Context;
import android.util.Log;

/**
 * Created by smz on 2018/11/8.
 */

public class LogUtils {



    public static  void  log_i(Context context,String msg){
        if(Config.isPrintLog){
            Log.i(context+"",msg);
        }

    }
}
