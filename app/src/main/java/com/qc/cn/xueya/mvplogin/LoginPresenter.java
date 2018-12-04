package com.qc.cn.xueya.mvplogin;

import com.google.gson.Gson;
import com.qc.cn.xueya.bean.User;
import com.qc.cn.xueya.http.OkHttpUitls;
import com.qc.cn.xueya.http.RequestCallBack;
import com.qc.cn.xueya.utils.Config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by smz on 2018/11/20.
 */

public class LoginPresenter {
     private   LoginInterface  loginInterface;

     private OkHttpUitls  okhttp;

    public LoginPresenter(LoginInterface loginInterface) {
        this.loginInterface = loginInterface;
        okhttp=OkHttpUitls.getInstance();
    }

    public void    getLoginInfo(boolean flag){

        if(!flag){
            return;
        }
        String url=Config.getUrl("kbb", "users")+"&type=login";

        Map<String,String> map=new HashMap<>();
        map.put("phonenum",loginInterface.getphone());
        map.put("password",loginInterface.getpas());
        okhttp.post(url, map, new RequestCallBack<String>() {
            @Override
            public void sucess(Call call, String t) {
                Gson gson=new Gson();
                User user =gson.fromJson(t,User.class);
                if(user.getState()==200){
//                    loginInterface.setValue(user.getUserid());
                    loginInterface.enteryAct();
                }else{
                      loginInterface.showmsg("格式错误");
                }
            }

            @Override
            public void fail(Call call, IOException e) {

            }
        });
    }

}
