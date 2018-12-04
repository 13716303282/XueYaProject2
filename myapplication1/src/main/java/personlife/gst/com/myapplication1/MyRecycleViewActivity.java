package personlife.gst.com.myapplication1;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import personlife.gst.com.myapplication1.ben.User;
import personlife.gst.com.myapplication1.utils.SQLiteUtils;


/**
 * Created by smz on 2018/11/21.
 */

public class MyRecycleViewActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        RecyclerView recy= findViewById(R.id.recy);

//        getApplication()   //
//        this
//        MyRecycleViewActivity.this



        final List<String> list=new ArrayList<>();
        for(int i=0;i<9;i++){
            list.add("劉德華"+i);
        }
        MyRecycleViewAdapter adapter=new MyRecycleViewAdapter(MyRecycleViewActivity.this,list);
        LinearLayoutManager manager=new LinearLayoutManager(this);
//        GridLayoutManager manager=new GridLayoutManager(this,2);
        recy.setLayoutManager(manager);
        recy.setAdapter(adapter);
        adapter.setListencer(new MyRecycleViewAdapter.AAA() {
            @Override
            public void setOnItemClickListener(View view, int position) {
//                Toast.makeText(MyRecycleViewActivity.this, list.get(position), Toast.LENGTH_SHORT).show();

                User us=new User();
                us.setAge(position+"");
                us.setContent("we是一个小青年");
                us.setName(list.get(position));

                SQLiteUtils.getInstance().addContacts(us);

            }

            @Override
            public void setOnItemLongClickListener(View view, int position) {

            }
        });
    }
}
