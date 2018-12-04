package com.qc.cn.xueya.mvplogin;

/**
 * Created by smz on 2018/11/20.
 */

public interface LoginInterface {
    String  getphone();  //获取手机号
    String  getpas();  //获取密码
    void  enteryAct();
    void   showmsg(String msg);

//    void  setValue(String  valye);
}
