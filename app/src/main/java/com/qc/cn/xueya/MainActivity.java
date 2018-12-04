package com.qc.cn.xueya;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.qc.cn.xueya.adapter.MyFragmentAdapter;
import com.qc.cn.xueya.bloodmanger.BloodMangerFragment;
import com.qc.cn.xueya.doctoronline.DoctorOnLineFragment;
import com.qc.cn.xueya.framework.base.BaseFragment;
import com.qc.cn.xueya.personal.PersonalFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private ViewPager viewapge;
    private BaseFragment doc_on_line;
    private BaseFragment bloodmanger;
    private BaseFragment personal;
    private FragmentManager manager;
    private MyFragmentAdapter adapter;
    private FragmentTransaction transaction;
    private TextView tv_doc_online;
    private TextView tv_blood_manger;
    private TextView tv_personal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initClick();
        initLogic();
    }

    private void initLogic() {

        List<Fragment> list=new ArrayList<>();

        doc_on_line = new DoctorOnLineFragment();
        bloodmanger = new BloodMangerFragment();
        personal = new PersonalFragment();
        list.add(doc_on_line);
        list.add(bloodmanger);
        list.add(personal);
        manager = getSupportFragmentManager();
        adapter = new MyFragmentAdapter(manager,MainActivity.this,list);
        viewapge.setAdapter(adapter);

        setCurrentZeron();
    }

    private void initClick() {
        tv_doc_online.setOnClickListener(this);
        tv_blood_manger.setOnClickListener(this);
        tv_personal.setOnClickListener(this);
    }

    private void initView() {

        tv_doc_online = findViewById(R.id.tv_doc_online);
        tv_blood_manger = findViewById(R.id.tv_blood_manger);
        tv_personal = findViewById(R.id.tv_personal);

        viewapge = findViewById(R.id.viewapge);
    }

    public  void   hide(){
        if(transaction==null){
            transaction = manager.beginTransaction();
        }

        if(doc_on_line!=null){
            transaction.hide(doc_on_line);
        }
        if(bloodmanger!=null){
            transaction.hide(bloodmanger);
        }
        if(personal!=null){
            transaction.hide(personal);
        }
    }

    @Override
    public void onClick(View v) {
        hide();
        switch (v.getId()){
            case R.id.tv_doc_online:
                setCurrentZeron();
                break;
            case R.id.tv_blood_manger:
                viewapge.setCurrentItem(1);
                setDrawable(tv_doc_online,R.mipmap.doctor_head_normal,R.color.personal_color);
                setDrawable(tv_blood_manger,R.mipmap.blood_manger_press,R.color.title_color);
                setDrawable(tv_personal,R.mipmap.persional_normal,R.color.personal_color);
                break;
            case R.id.tv_personal:
                viewapge.setCurrentItem(2);
                setDrawable(tv_doc_online,R.mipmap.doctor_head_normal,R.color.personal_color);
                setDrawable(tv_blood_manger,R.mipmap.blood_manger_normal,R.color.personal_color);
                setDrawable(tv_personal,R.mipmap.persional_press,R.color.title_color);
                break;
        }
    }

    public void  setDrawable(TextView textView,int Resourid,int colorid){
        Drawable drawable =getResources().getDrawable(Resourid);
        drawable.setBounds(0,0,drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, drawable, null, null);
        textView.setTextColor(getResources().getColor(colorid));
    }

    public void  setCurrentZeron(){
        viewapge.setCurrentItem(0);
        setDrawable(tv_doc_online,R.mipmap.doctor_head_press,R.color.title_color);
        setDrawable(tv_blood_manger,R.mipmap.blood_manger_normal,R.color.personal_color);
        setDrawable(tv_personal,R.mipmap.persional_normal,R.color.personal_color);
    }
}
