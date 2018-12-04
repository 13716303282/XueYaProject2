package personlife.gst.com.bluetoothdemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by smz on 2018/11/30.
 */

public class TwoActivity  extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Person  p=new Person();
        p.setName("刘德华");
        p.setSex("女");
        p.setAge(12);
        p.setHight(100);

        //发送动态广播 是清单文件  没有注册好的
        Intent  intent=new Intent();
        intent.setAction("hahha");
        intent.putExtra("person",p);
        sendBroadcast(intent);

    }




}
