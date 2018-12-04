package com.qc.cn.xueya.login;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qc.cn.xueya.MainActivity;
import com.qc.cn.xueya.R;
import com.qc.cn.xueya.bean.GetSmsFirstBean;
import com.qc.cn.xueya.framework.base.BaseActivity;
import com.qc.cn.xueya.http.OkHttpUitls;
import com.qc.cn.xueya.http.RequestCallBack;
import com.qc.cn.xueya.utils.Config;
import com.qc.cn.xueya.utils.LogUtils;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;

/**
 * Created by smz on 2018/11/8.
 */

public class RegisterTwoActivity extends BaseActivity {

    private EditText ed_input_code;
    private TextView tv_time;
    private TextView tv_banding;
    private GetSmsFirstBean bean;

    @Override
    public int initLayout() {
        return R.layout.activity_register_two;
    }

    @Override
    public void initView() {

        ed_input_code = findViewById(R.id.ed_input_code);

        tv_time = findViewById(R.id.tv_time);

        tv_banding = findViewById(R.id.tv_banding);


    }

    @Override
    public void initClick() {

        tv_banding.setOnClickListener(this);
        new CountDownTimer(60000,1000 ){

            @Override
            public void onTick(long millisUntilFinished) {
                 tv_banding.setClickable(true);
                 tv_time.setText("接收短信大约需要"+millisUntilFinished/1000+"秒");
            }

            @Override
            public void onFinish() {
//                 tv_banding.setClickable(false);
//                 tv_banding.setBackgroundColor(getResources().getColor(R.color.not_click_button));
                   finish();
            }
        }.start();
    }

    @Override
    public void initLogic() {
        bean = (GetSmsFirstBean) getIntent().getSerializableExtra("object");
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_banding:
                String code= Config.getText(ed_input_code);
                lastBangdingSmsCode(code);


                break;
        }
    }

    private void lastBangdingSmsCode(String code) {
         String url=Config.getRegirstUrl("kbb","users")+"&type=registAccount";
        HashMap<String, String> params = new HashMap();
        params.put("phonenum", bean.getPhone());
        params.put("password", bean.getPass());
        params.put("accountstr", bean.getName());
        params.put("code", code);
        params.put("sex", "2");
        params.put("height", "0");
        params.put("birthday", System.currentTimeMillis()+"");

        OkHttpUitls.getInstance().post(url, params, new RequestCallBack<String>() {
            @Override
            public void sucess(Call call, String t) {
                LogUtils.log_i(RegisterTwoActivity.this,t);
//                SharedPreferences  sp= Config.getSp(RegisterTwoActivity.this);
//                sp.edit().putString("phone",bean.getPhone()).putString("pas",bean.getPass()).putBoolean("login",true).commit();
//                enterActivity(MainActivity.class);
            }

            @Override
            public void fail(Call call, IOException e) {

            }
        });
    }
}
