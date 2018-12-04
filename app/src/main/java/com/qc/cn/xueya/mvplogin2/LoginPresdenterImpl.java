package com.qc.cn.xueya.mvplogin2;

import com.qc.cn.xueya.http.OkHttpUitls;
import com.qc.cn.xueya.http.RequestCallBack;
import com.qc.cn.xueya.utils.Config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by smz on 2018/12/3.   p层  操作m层  请求数据       操作view层（ 进去到下一个页面     拿到v层的手机号 密码 ）
 */

public class LoginPresdenterImpl  implements  LoginPresenterInterface{

    private LoginViewInterface loginViewInterface;
    private final OkHttpUitls okhttp;

    public LoginPresdenterImpl(LoginViewInterface loginViewInterface) {
        this.loginViewInterface = loginViewInterface;
        //初始化modle层对象
        okhttp = OkHttpUitls.getInstance();
    }


    @Override
    public void getUserInfo() {
        String url=Config.getUrl("kbb", "users")+"&type=login";
        Map<String,String> map=new HashMap<>();
        map.put("phonenum",loginViewInterface.getphone());
        map.put("password",loginViewInterface.getpas());
        okhttp.post(url, map, new RequestCallBack<String>() {
            @Override
            public void sucess(Call call, String t) {

                loginViewInterface.enterAct();

            }

            @Override
            public void fail(Call call, IOException e) {
               loginViewInterface.show_msg("我失败了");
            }
        });
    }
}
