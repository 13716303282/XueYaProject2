package com.qc.cn.xueya.login;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qc.cn.xueya.R;
import com.qc.cn.xueya.bean.GetSmsFirstBean;
import com.qc.cn.xueya.framework.base.BaseActivity;
import com.qc.cn.xueya.http.RequestCallBack;
import com.qc.cn.xueya.http.OkHttpUitls;
import com.qc.cn.xueya.utils.Config;
import com.qc.cn.xueya.utils.LogUtils;

import java.io.IOException;
import java.util.regex.Pattern;

import okhttp3.Call;

/**
 * Created by smz on 2018/11/6.
 */

public class RegisterActivty extends BaseActivity {

    private EditText ed_name;
    private EditText ed_phone;
    private EditText ed_pass;
    private TextView tv_send;

    private String  TAG="RegisterActivty";
    private ImageView iv_title_left;

    @Override
    public int initLayout() {
        return R.layout.activty_register;
    }

    @Override
    public void initView() {
        TextView tv_middle_title= findViewById(R.id.tv_middle_title);
        tv_middle_title.setText("快速注册");

        iv_title_left = findViewById(R.id.iv_title_left);
        iv_title_left.setVisibility(View.VISIBLE);


        ed_name = findViewById(R.id.ed_name);
        ed_phone = findViewById(R.id.ed_phone);
        ed_pass = findViewById(R.id.ed_pass);
        tv_send = findViewById(R.id.tv_send);

    }

    @Override
    public void initClick() {
        iv_title_left.setOnClickListener(this);
        tv_send.setOnClickListener(this);
    }

    @Override
    public void initLogic() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
//            case R.id.iv_title_left:
//                finish();
//                break;
            case R.id.tv_send:
                 final String name=Config.getText(ed_name);
                 final String phone=Config.getText(ed_phone);
                 final String pas=Config.getText(ed_pass);

               boolean flag=  checkInfo(name,phone,pas);

               if(flag){
                   //请求验证码请求
                   String  url=Config.getUrl("sms","sendCode")+"&target="+  phone + "&type=4";
                   OkHttpUitls.getInstance().get(url, new RequestCallBack<String>() {
                       @Override
                       public void sucess(Call call, String data) {
                           LogUtils.log_i(RegisterActivty.this,data);
                            Gson gson=new Gson();
                           GetSmsFirstBean firstBean=  gson.fromJson(data, GetSmsFirstBean.class);
                         if(firstBean.getCode()==10000){
                             firstBean.setName(name);
                             firstBean.setPhone(phone);
                             firstBean.setPass(pas);
                             enterActivity(RegisterTwoActivity.class,firstBean);
                         }else{
                             show(firstBean.getError());
                         }

                       }

                       @Override
                       public void fail(Call call, IOException e) {

                       }
                   });



               }

                break;
        }
    }

    private boolean checkInfo(String name, String phone, String pas) {

        if(!Pattern.matches(Config.NAME_MATCH,name)){
            show("请输入正确的名字");
            return  false;
        }
        if(!Pattern.matches(Config.PHONE_MATCH,phone)){
            show("请输入正确的手机号");
            return  false;
        }
        if(!Pattern.matches(Config.PASSWORD,pas)){
            show("请输入正确的密码");
            return  false;
        }


        return  true;
    }


}
