package personlife.gst.com.bluetoothdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.io.Serializable;
import java.util.List;

/**
 * Created by smz on 2018/11/30.
 */

public class MyBroadCast extends BroadcastReceiver {

    private  Context context;

    public MyBroadCast(Context context) {
        this.context = context;
    }

    public MyBroadCast() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
      String action= intent.getAction();

      if ("wochifanle".equals(action)){
          //Parcela
         // Serializable
          /**
           * 两种序列化的区别 ？
           */
        List<Person> lsit= intent.getParcelableArrayListExtra("list");
          for (Person  p:lsit) {
              System.out.println("姓名" +p.getName() +"性别" +p.getSex()+"身高"+p.getHight()+"年龄"+p.getAge());
          }
      }
    }
}
