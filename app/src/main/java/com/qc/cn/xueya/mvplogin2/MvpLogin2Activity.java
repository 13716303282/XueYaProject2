package com.qc.cn.xueya.mvplogin2;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qc.cn.xueya.MainActivity;
import com.qc.cn.xueya.R;
import com.qc.cn.xueya.framework.base.BaseActivity;

/**
 * Created by smz on 2018/12/3.
 */

public class MvpLogin2Activity extends BaseActivity implements LoginViewInterface {

    private EditText ed_phone;
    private EditText ed_pas;
    private TextView tv_login;
    private LoginPresdenterImpl presenter;

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
        presenter = new LoginPresdenterImpl(MvpLogin2Activity.this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_login:
                presenter.getUserInfo();
                break;
        }
    }

    @Override
    public String getphone() {
        String phone=ed_phone.getText().toString().trim();
        return phone;
    }

    @Override
    public String getpas() {
        String pas=ed_pas.getText().toString().trim();
        return pas;
    }

    @Override
    public void enterAct() {
        enterActivity(MainActivity.class);
    }

    @Override
    public void show_msg(String msg) {
        show(msg);
    }
}
