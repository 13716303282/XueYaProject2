package personlife.gst.com.myapplication1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by smz on 2018/11/21.
 */

public class MyRecycleViewAdapter extends RecyclerView.Adapter<MyRecycleViewAdapter.VH> {
    private Context context;
    private List<String> list;

    public MyRecycleViewAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
       View view=View.inflate(context,R.layout.item, null);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
       holder.tv_1.setText(list.get(position));

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               a.setOnItemClickListener(v,position); //点击item以后执行的
           }
       });

       holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
           @Override
           public boolean onLongClick(View v) {
               a.setOnItemLongClickListener(v,position);
               return true;
           }
       });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public  class VH extends RecyclerView.ViewHolder{

        private final TextView tv_1;

        public VH(View itemView) {
            super(itemView);
            tv_1 = itemView.findViewById(R.id.tv_1);
        }
    }

    public  AAA  a; //成员变量

   //2
    public  void setListencer(AAA  a){
        this.a=a;
    }

   //1
    public  interface AAA{

        void  setOnItemClickListener(View view ,int position); //点击的时候被调用
        void  setOnItemLongClickListener(View view ,int position);
    }



}







