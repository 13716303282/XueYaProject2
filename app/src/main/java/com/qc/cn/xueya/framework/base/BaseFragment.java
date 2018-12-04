package com.qc.cn.xueya.framework.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qc.cn.xueya.bean.SerObject;

/**
 * Created by smz on 2018/11/10.
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View  view= getView(inflater);
        initView(view);
        initClick();
        initLogic();
        return view;
    }

    public abstract View getView(LayoutInflater inflater);

    public abstract  void  initView(View view);
    public abstract  void  initClick();
    public  abstract  void  initLogic();

    public void  show(final String msg){

        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast toast=  Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT);
                toast .setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
        });


    }

    public Handler handler=new Handler();

    @Override
    public void onClick(View v) {

    }
    public  void  enterActivity(Class clazz){
        Intent intent=new Intent(getActivity(),clazz);
        startActivity(intent);
    }

    public  void  enterActivity(Class clazz,SerObject object){
        Intent  intent=new Intent(getActivity(),clazz);
        intent.putExtra("object",object);
        startActivity(intent);
    }
}
