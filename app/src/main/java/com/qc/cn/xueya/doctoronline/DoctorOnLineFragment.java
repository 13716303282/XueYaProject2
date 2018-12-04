package com.qc.cn.xueya.doctoronline;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.qc.cn.xueya.R;
import com.qc.cn.xueya.framework.base.BaseFragment;

/**
 * Created by smz on 2018/11/10.
 */

public class DoctorOnLineFragment extends BaseFragment {

    private ImageView iv_location;

    @Override
    public View getView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_doctor_online,null);
    }

    @Override
    public void initView(View view) {
        iv_location = view.findViewById(R.id.iv_location);
    }

    @Override
    public void initClick() {
        iv_location.setOnClickListener(this);
    }

    @Override
    public void initLogic() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()){
            case R.id.iv_location:
                  enterActivity(LocationActivty.class);

                break;
        }
    }
}
