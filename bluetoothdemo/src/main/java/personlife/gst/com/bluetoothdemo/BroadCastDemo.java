package personlife.gst.com.bluetoothdemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smz on 2018/11/30.
 */

public class BroadCastDemo extends Activity {

    private TwoRecive recive;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast);

        recive = new TwoRecive();
        IntentFilter filter=new IntentFilter();
        filter.addAction("hahha");
        registerReceiver(recive,filter);
    }


    public  void   click(View view){



        //开启activity  startActivty
        //开启服务（service）  startService   bindService
        //开启 内容提供者  contentprovider

        //开启广播 ：发送广播  sendbroadcast

        /**
         *   IntentFilter  filter=new IntentFilter();
         filter.addAction("wochifanle");
         filter.addAction("lala");
         */
        List<Person> list=new ArrayList<>();

        for (int i=0;i<10;i++){
            Person  p=new Person();
            p.setName("刘德华"+i);
            p.setSex(i%2==0?"男":"女");
            p.setAge(i);
            p.setHight(100+i);
            list.add(p);
        }



           Intent  intent=new Intent();
           intent.setAction("wochifanle");
           intent.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>) list);
           sendBroadcast(intent);




    }

    public  void   click2(View view){

    Intent  intent=new Intent(this,TwoActivity.class);
    startActivity(intent);

    }

    public  class  TwoRecive extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            if("hahha".equals(action)){
                Person p=intent.getParcelableExtra("person");
                System.out.println("姓名" +p.getName() +"性别" +p.getSex()+"身高"+p.getHight()+"年龄"+p.getAge());
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(recive); //防止内存泄漏
    }
}

