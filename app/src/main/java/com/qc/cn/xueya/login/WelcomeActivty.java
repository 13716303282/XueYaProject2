package com.qc.cn.xueya.login;

import android.content.SharedPreferences;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.qc.cn.xueya.MainActivity;
import com.qc.cn.xueya.R;
import com.qc.cn.xueya.bean.GetDetailUserInfo;
import com.qc.cn.xueya.bean.User;
import com.qc.cn.xueya.framework.base.BaseActivity;
import com.qc.cn.xueya.http.RequestCallBack;
import com.qc.cn.xueya.http.OkHttpUitls;
import com.qc.cn.xueya.mvplogin.MvpLoginActivty;
import com.qc.cn.xueya.utils.Config;
import com.qc.cn.xueya.utils.LogUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by smz on 2018/11/6.
 */

public class WelcomeActivty extends BaseActivity {

    private RelativeLayout rl_1;
    private Gson gson;

    @Override
    public int initLayout() {
        return R.layout.activty_welcome;
    }

    @Override
    public void initView() {
        rl_1 = findViewById(R.id.rl_1);

    }

    @Override
    public void initClick() {

    }

    @Override
    public void initLogic() {
        AlphaAnimation  animation=new AlphaAnimation(0,1);
        animation.setDuration(2000);
        animation.setAnimationListener(new MyAnimationListener());
        rl_1.setAnimation(animation);
    }


    public class  MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {

            if(Config.isLogin(WelcomeActivty.this)){
                Login();
              }else{
                enterActivity(LoginActivity.class);//mvc
                enterActivity(MvpLoginActivty.class);
              }
                finish();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    public  void     Login(){
        String url=Config.getUrl("kbb", "users")+"&type=login";
        Map<String ,String> map=new HashMap<>() ;
        map.put("phonenum", Config.getSp(WelcomeActivty.this).getString("phone",""));
        map.put("password" , Config.getSp(WelcomeActivty.this).getString("pas",""));
         OkHttpUitls.getInstance().post(url, map,new AA());
    }

    public  class  AA implements RequestCallBack<String> {

        @Override
        public void sucess(Call call , String s) {//Call call, String s
            LogUtils.log_i(WelcomeActivty.this,"获取的登录个人信息"+s);
            gson = new Gson();
            User user = gson.fromJson(s,User.class);
            if(user.getState()==200){
                //我就把我的账号跟信息保存到 sp中
                getMyInfo(user.getUserid(),user);
            }else{
                show("格式错误");
            }

        }

        @Override
        public void fail(Call call, IOException e) {
            show("数据错误");
        }
    }

    public  void   getMyInfo(String userid, final User us){
        //GET http://api.wws.xywy.com/index.php?act=kbb&fun=users&type=pullAccountInfo&tag=wjk&userid=46224456&sign=ee3dd4651821d3a45f4329a86d459cb7
        String url=Config.getUrl("kbb","users","wjk")+"&userid="+userid+"&type=pullAccountInfo";

        OkHttpUitls.getInstance().get(url, new RequestCallBack<String>() {
            @Override
            public void sucess(Call call, String t) {



                LogUtils.log_i(WelcomeActivty.this,t);

                GetDetailUserInfo info=gson.fromJson(t,GetDetailUserInfo.class);
                if(info.getState()==200){
                    us.setAccountstr(info.getAccounts().get(0).getAccountstr());
                    us.setAvatar(info.getAvatar());
                    us.setEmail(info.getEmail());
                    us.setPushable(info.getPushable());
                    SharedPreferences sp= Config.getSp(WelcomeActivty.this);
                    sp.edit().putBoolean("login",true).commit();
                    Config.setUser(WelcomeActivty.this,us);
                    enterActivity(MainActivity.class);
                    finish();
                }else{
                    show("出现错误");
                }


            }

            @Override
            public void fail(Call call, IOException e) {

            }
        });
}
}
