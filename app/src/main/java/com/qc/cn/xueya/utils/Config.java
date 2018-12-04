package com.qc.cn.xueya.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;

import com.google.gson.Gson;
import com.qc.cn.xueya.bean.User;

/**
 * Created by smz on 2018/11/7.
 */

public class Config {
    public  static  String  tag="BloodAndroid";

    public  static  String Sign="KHy69gsk8%#@kl$";

    public  static  String  url="http://api.wws.xywy.com/index.php?";
    private static SharedPreferences sp;

    public static  boolean isPrintLog=true;//控制是否打印log

    public static final String NAME_MATCH = "^[\u4E00-\u9FA5]{2,8}$";// 2至8个汉字的
    public static final String PHONE_MATCH = "^1[0-9]{10}$";// 手机号正则

    public static final String PASSWORD = "^[0-9a-zA-Z]{6,16}$";

    public  static   String getUrl(String  act ,String fun ){
        String sign=MD5.md5s(Sign+tag);

        return  url+"tag="+tag+"&sign="+sign+"&act="+act+"&fun="+fun;

    }

    public  static  String getUrl(String  act ,String fun,String tag){
        String sign=MD5.md5s(Sign+tag);

        return  url+"tag="+tag+"&sign="+sign+"&act="+act+"&fun="+fun;
    }
    private static   String tagg="wjk";//为了注册用的
    public  static   String getRegirstUrl(String  act ,String fun ){
        String sign=MD5.md5s(Sign+tagg);

        return  url+"tag="+tagg+"&sign="+sign+"&act="+act+"&fun="+fun;

    }

    public static SharedPreferences getSp(Context context){
        if(sp==null){
            sp = context.getSharedPreferences("account.config", Context.MODE_PRIVATE);
        }
        return sp;
    }

    public static boolean isLogin(Context context){
        if(sp==null){
            sp=getSp(context);
        }
        return  sp.getBoolean("login",false);
    }

    public static String  getText(EditText edview){
        String value= edview.getText().toString().trim();
        return  value;
    }

    public  static User  getUser(Context context){
     String json=   getSp(context).getString("user","");
     Gson gson=new Gson();
     return  gson.fromJson(json,User.class);
    }

    /**
     *
     * @param context
     * @param us    就是user对象
     */
    public  static  void  setUser(Context context,User us){
            Gson gson=new Gson();
            String json= gson.toJson(us);  //把对象变成好json串
             getSp(context).edit().putString("user",json).commit();

//             SharedPreferences  sharedPreferences=  getSp(context);
//             SharedPreferences.Editor editor= sharedPreferences.edit();
//             editor.putString("user",josnString);
    }

}
