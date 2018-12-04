package com.qc.cn.xueya.login;

import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qc.cn.xueya.MainActivity;
import com.qc.cn.xueya.R;
import com.qc.cn.xueya.bean.GetDetailUserInfo;
import com.qc.cn.xueya.bean.User;
import com.qc.cn.xueya.framework.base.BaseActivity;
import com.qc.cn.xueya.http.RequestCallBack;
import com.qc.cn.xueya.http.OkHttpUitls;
import com.qc.cn.xueya.utils.Config;
import com.qc.cn.xueya.utils.LogUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by smz on 2018/11/6.
 */

public class LoginActivity  extends BaseActivity {

    private EditText ed_phone;
    private EditText ed_pas;
    private TextView tv_input_phone;
    private TextView tv_input_pas;
    private TextView tv_forgotpas;
    private TextView tv_login;
    private TextView tv_register;
    private String phone;
    private String pas;

    @Override
    public int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

        TextView tv_middle_title= findViewById(R.id.tv_middle_title);
        tv_middle_title.setText("nihao");

        ed_phone = findViewById(R.id.ed_phone);
        ed_pas = findViewById(R.id.ed_pas);

        tv_input_phone = findViewById(R.id.tv_input_phone);
        tv_input_pas = findViewById(R.id.tv_input_pas);

        tv_forgotpas = findViewById(R.id.tv_forgotpas);
        tv_login = findViewById(R.id.tv_login);
        tv_register = findViewById(R.id.tv_register);

    }

    @Override
    public void initClick() {

        tv_forgotpas.setOnClickListener(this);
        tv_login.setOnClickListener(this);
        tv_register.setOnClickListener(this);

    }

    @Override
    public void initLogic() {
        ed_phone.addTextChangedListener(new PhoneTextWatcher());
        ed_pas.addTextChangedListener(new PasTextWatcher());
    }


    public  class  PasTextWatcher  implements  TextWatcher{

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            int len=s.length();
            if(len>=6&&len<=16){
                tv_input_pas.setText("");
                tv_input_pas.setBackgroundResource(R.mipmap.checkbox_press);
            }else{
                if(s.length()==0){

                    tv_input_pas.setText(getText(R.string.login_pas));
                }
                else{
                    tv_input_pas.setText("");
                }
                tv_input_pas.setBackgroundResource(R.color.white);
            }

        }
    }
    public class PhoneTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {


            if(s.length()==11){
                tv_input_phone.setText("");
                tv_input_phone.setBackgroundResource(R.mipmap.checkbox_press);
            }else{
                if(s.length()==0){
                    tv_input_phone.setText(getText(R.string.login_phone));
                }
                else{
                    tv_input_phone.setText("");
                }
                tv_input_phone.setBackgroundResource(R.color.white);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_forgotpas:
                enterActivity(ForgotPassWordActivity.class);

                break;
            case R.id.tv_login:
                phone = ed_phone.getText().toString().trim();
                pas = ed_pas.getText().toString().trim();
               boolean  is_right=checkInfo(phone, pas);
               if(is_right) {
                   //用户名密码请求服务器

                    String url=Config.getUrl("kbb", "users")+"&type=login";
                    System.out.println("url链接"+url);

                   Map<String ,String> map=new HashMap<>() ;
                   map.put("phonenum", phone);
                   map.put("password" , pas);
                    OkHttpUitls.getInstance().post(url, map,new AA());


               }
                break;
            case R.id.tv_register:
                  enterActivity(RegisterActivty.class);
                break;
        }
    }
private   Gson gson;
    public  class  AA implements   RequestCallBack<String> {
        @Override
        public void sucess(Call call ,String s) {//Call call, String s
            gson=new Gson();
            User user =gson.fromJson(s,User.class);
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



                LogUtils.log_i(LoginActivity.this,t);

                GetDetailUserInfo info=gson.fromJson(t,GetDetailUserInfo.class);
                if(info.getState()==200){
                    us.setAccountstr(info.getAccounts().get(0).getAccountstr());
                    us.setAvatar(info.getAvatar());
                    us.setEmail(info.getEmail());
                    us.setPushable(info.getPushable());
                    SharedPreferences sp= Config.getSp(LoginActivity.this);
                    sp.edit().putString("phone",phone).putString("pas",pas).putBoolean("login",true).commit();
                    Config.setUser(LoginActivity.this,us);
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

    private boolean checkInfo(String phone ,String pas) {
        int phone_len=phone.length();
        int pas_len=pas.length();

        if(phone_len==11&&pas_len>=6&&pas_len<=16){
            return  true;
        }
        if(phone_len!=11){
           show("请输入正确的手机号");
           return  false;
        }
        if(!(pas_len>=6&&pas_len<=16)){
            show("请输入正确的密码");
            return  false;
        }


        return  false;
    }
}
