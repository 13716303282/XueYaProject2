package personlife.gst.com.myapplication1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smz on 2018/11/21.
 */

public class HomeActivity extends FragmentActivity  implements View.OnClickListener{

    private ViewPager vp;
    private TextView tv_1;
    private TextView tv_2;
    private TextView tv_3;

    private FirstFragment firstFragment;
    private TwoFragment   twoFragment;
    private ThirdFragment thirdFragment;
    private FragmentManager fm;
    private List<Fragment> list;
    private MyFragmentAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_home);
        vp = findViewById(R.id.vp);
        tv_1 = findViewById(R.id.tv_1);
        tv_2 = findViewById(R.id.tv_2);
        tv_3 = findViewById(R.id.tv_3);

        fm = getSupportFragmentManager();
        list = new ArrayList<>();
        list.add(new FirstFragment());
        list.add(new TwoFragment());
        list.add(new ThirdFragment());
        adapter = new MyFragmentAdapter(fm,list,this);
        vp.setAdapter(adapter);


        tv_1.setOnClickListener(this);
        tv_2.setOnClickListener(this);
        tv_3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_1:
                vp.setCurrentItem(0);
                break;
            case R.id.tv_2:
                vp.setCurrentItem(1);
                break;
            case R.id.tv_3:
                vp.setCurrentItem(2);
                break;

        }
    }
}
