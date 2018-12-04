package com.qc.cn.xueya.mvplogin2;

import com.tencent.wxop.stat.StatSpecifyReportedInfo;

/**
 * Created by smz on 2018/12/3.
 */

public interface LoginViewInterface {

    String  getphone();
    String  getpas();

    void   enterAct();

    void  show_msg(String msg);
}
