package com.qc.cn.xueya.mvplogin;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qc.cn.xueya.MainActivity;
import com.qc.cn.xueya.R;
import com.qc.cn.xueya.framework.base.BaseActivity;

/**
 * Created by smz on 2018/11/20.
 */

public class MvpLoginActivty extends BaseActivity implements LoginInterface {

    private EditText ed_phone;
    private EditText ed_pas;
    private TextView tv_login;
    private LoginPresenter presenter;

    @Override
    public int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        ed_phone = findViewById(R.id.ed_phone);
        ed_pas = findViewById(R.id.ed_pas);
        tv_login = findViewById(R.id.tv_login);
    }

    @Override
    public void initClick() {
        tv_login.setOnClickListener(this);
    }

    @Override
    public void initLogic() {
        presenter = new LoginPresenter(MvpLoginActivty.this);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_login:
//                OkHttpUitls.getInstance().post();    //不允许直接操作modle  v--->  p---->m
                presenter.getLoginInfo(checkInfo(getphone(),getpas()));

                break;
        }
    }

    @Override
    public String getphone() {
        String phone=ed_phone.getText().toString().trim();
        return phone.isEmpty()?"":phone;
    }

    @Override
    public String getpas() {
        String pas=ed_pas.getText().toString().trim();
        return pas.isEmpty()?"":pas;
    }

    @Override
    public void enteryAct() {
        enterActivity(MainActivity.class);
        finish();
    }



    @Override
    public void showmsg(String msg) {
        show(msg);
    }

//    @Override
//    public void setValue(String value) {
//         tv_login.setText(value);
//    }

    public boolean checkInfo(String phone ,String pas) {
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
