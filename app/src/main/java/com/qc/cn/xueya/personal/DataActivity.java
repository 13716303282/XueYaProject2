package com.qc.cn.xueya.personal;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qc.cn.xueya.R;
import com.qc.cn.xueya.bean.User;
import com.qc.cn.xueya.customview.MyDialog;
import com.qc.cn.xueya.framework.base.BaseActivity;
import com.qc.cn.xueya.http.OkHttpUitls;
import com.qc.cn.xueya.http.RequestCallBack;
import com.qc.cn.xueya.utils.Config;
import com.qc.cn.xueya.utils.LogUtils;
import com.qc.cn.xueya.utils.MD5;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;


/**
 * Created by ASUS on 2018/11/11.
 */

public class DataActivity extends BaseActivity implements View.OnClickListener {
    private NumberPicker nb;
    private String[] sex = {"男", "女"};
    private String Ssex = "";
    private Button xuanze;
    private RelativeLayout re_birthday;
    private RelativeLayout re_weight;
    private RelativeLayout re_hight;
    private RelativeLayout re_sex;
    private RelativeLayout re_name;
    private ImageView iv_back;
//    private Dialog dialog;
//    private View view;
    private TextView tv_choose1;
    private User user;
    private TextView tv_name;
    private TextView tv_sex;
    private TextView tv_hight;
    private TextView tv_weight;
    private TextView tv_birthday;
    //    private TextView tanchu;
//    private TextView tv_sex;

    @Override
    public int initLayout() {
        return R.layout.activity_data;
    }

    @Override
    public void initView() {
        iv_back = findViewById( R.id.iv_title_left );
        iv_back.setVisibility( View.VISIBLE );
        TextView tv_title = findViewById( R.id.tv_middle_title );
        tv_title.setText( "个人资料" );
        re_name = findViewById( R.id.re_name );
        re_sex = findViewById( R.id.re_sex );
        re_hight = findViewById( R.id.re_hight );
        re_weight = findViewById( R.id.re_weight );
        re_birthday = findViewById( R.id.re_birthday );
        tv_choose1 = findViewById( R.id.tv_choose1 );

//        dialog = new Dialog( DataActivity.this );
//        view = View.inflate( DataActivity.this, R.layout.sex, null );
//        dialog.setContentView( view );

        tv_name = findViewById(R.id.tv_name);
        tv_sex = findViewById(R.id.tv_choose1);
        tv_hight = findViewById(R.id.tv_choose2);
        tv_weight = findViewById(R.id.tv_choose3);
        tv_birthday = findViewById(R.id.tv_choose4);


    }

    @Override
    public void initClick() {
        re_name.setOnClickListener( this );
        re_sex.setOnClickListener( this );
        re_hight.setOnClickListener( this );
        re_weight.setOnClickListener( this );
        re_birthday.setOnClickListener( this );
        iv_back.setOnClickListener( this );
    }

    @Override
    public void initLogic() {
        user = Config.getUser(DataActivity.this);
        tv_name .setText(user.getAccountstr());
        tv_sex .setText(user.getSex());
        tv_hight .setText(user.getHeight()+"");
//        tv_weight.setText(user.get);
        tv_birthday .setText(user.getBirthday());
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_title_left:
                finish();
                break;
            case R.id.re_name:
//                enterActivity( NameActivity.class );
                break;
            case R.id.re_sex:
//               dialog.show();
//                nb = view.findViewById( R.id.test_np );
//                xuanze = view.findViewById( R.id.test_button );
//                nb.setMinValue( 0 );
//                nb.setMaxValue( srt.length - 1 );
//                nb.setDisplayedValues( srt );
//                nb.setValue( 0 );
//                nb.invalidate();
//                sex = srt[nb.getValue()];
//                nb.setOnValueChangedListener( new NumberPicker.OnValueChangeListener() {
//                    @Override
//                    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
//                        tv_choose1.setText(  srt[newVal] );
//                    }
//                } );
//                xuanze.setOnClickListener( new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
////                        updata();
//                    }
//                } );


                 showSexDialog();












                break;
            case R.id.re_hight:

                break;
            case R.id.re_weight:

                break;
            case R.id.re_birthday:

                showDatePickerDialog();

                break;
        }
    }

    private void updata(String str) {
//        POST /index.php?act=kbb&fun=resetProperty&tag=BloodAndroid&sign=2c19b2821ebc5306c3ac37bac5b4288b HTTP/1.1
        String url= Config.getUrl( "kbb","resetProperty" );
       User user= Config.getUser( DataActivity.this );
        Map<String,String> map=new HashMap<>(  );
        map.put( "userid", user.getUserid());
        map.put( "sex",1+"" );//1 代表男  2 代表是女
        map.put(  "appid","2" );
//        OkHttpUtils.getInstance().post( url, );

    }


    /* 性别 */
    private void showSexDialog() {
        final MyDialog myDialog = new MyDialog(DataActivity.this,
                R.style.MyDialogStyle);
        myDialog.show();
        myDialog.setCancelable(false);
        myDialog.setCanceledOnTouchOutside(false);
        myDialog.setContentView(R.layout.mydialog_twobutton_layout);
        TextView dialogTitle = myDialog
                .findViewById(R.id.mydialog_title);
        dialogTitle.setText("设置性别");
        NumberPicker numberPicker =  myDialog
                .findViewById(R.id.picker);
        // numberPicker.setFormatter(this);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

            @Override
            public void onValueChange(NumberPicker picker, int oldVal,
                                      int newVal) {
                tv_choose1.setText(sex[newVal]);
                if("男".equals(sex[newVal])){
                    Ssex="1";
                }else{
                    Ssex="2";
                }


            }
        });
//         numberPicker.setOnScrollListener(this);
        numberPicker.setDisplayedValues(sex);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(sex.length - 1);
//        if ("男".equals(tv_choose1.getText().toString())) {
//            numberPicker.setValue(0);
//        } else if ("女".equals(tv_choose1.getText().toString())) {
//            numberPicker.setValue(1);
//        }
        TextView leftButton = myDialog
                .findViewById(R.id.mydialog_twobutton_layout_left_button);
        leftButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                myDialog.dismiss();
            }
        });
        leftButton.setVisibility(View.GONE);
        myDialog.findViewById(R.id.mydialog_twobutton_layout_right_button)
                .setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        myDialog.dismiss();
                        try {
                            changOnServer();
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }

    private void changOnServer() throws ParseException {
        String url = Config.getUrl("kbb","resetProperty");

        Map<String,String> map=new HashMap<>();
        map.put("userid",user.getUserid());
        map.put("app_id",2+"");
        map.put("accountstr",user.getAccountstr());
        map.put("sex", Ssex);
        map.put("height",user.getHeight()+"");
        if(user.getBirthday().isEmpty()){

            map.put("birthday","0");//生日上传的是   秒
        }else{

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date time=format.parse(user.getBirthday());
            map.put("birthday",time.getTime()/1000+"");//生日上传的是   秒
        }
        map.put("keyword", "edit");

        OkHttpUitls.getInstance().post(url, map, new RequestCallBack<String>() {
            @Override
            public void sucess(Call call, String t) {
                LogUtils.log_i(DataActivity.this,"获取的个人信息"+t);
            }

            @Override
            public void fail(Call call, IOException e) {

            }
        });

    }


    /* 时间 */
    public void showDatePickerDialog() {
        System.out.println("showDatePickerDialog---");
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        LogUtils.log_i(DataActivity.this,"获取当前的时间"+mYear+"-"+(mMonth+1)+"-"+mDay);
        String str = user.getBirthday();
        LogUtils.log_i(DataActivity.this,"获取自己的"+str);
        if (!TextUtils.isEmpty(str)) {
            mYear = Integer.parseInt(str.substring(0, 4));
            mMonth = Integer.parseInt(str.substring(5, 7));
            mMonth = mMonth - 1;
            mDay = Integer
                    .parseInt(str.substring(Math.max(str.length() - 2, 0)));
        }

        //系统提供的
        DatePickerDialog dialog = new DatePickerDialog(DataActivity.this, DatePickerDialog.THEME_HOLO_LIGHT,mDateSetListener,
                mYear, mMonth, mDay);
        dialog.show();

    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            monthOfYear = monthOfYear + 1;
            String birthday = "";
            if (monthOfYear < 10) {
                birthday = year + "-0" + monthOfYear + "-" + dayOfMonth;
                tv_birthday.setText(birthday);
            } else {
                birthday = year + "-" + monthOfYear + "-" + dayOfMonth;
                tv_birthday.setText(birthday);
            }
           user.setBirthday(birthday);
            try {
                changOnServer();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    };

}
