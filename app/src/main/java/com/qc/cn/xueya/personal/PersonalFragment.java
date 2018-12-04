package com.qc.cn.xueya.personal;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawableLoadProvider;
import com.google.gson.Gson;
import com.qc.cn.xueya.MainActivity;
import com.qc.cn.xueya.R;
import com.qc.cn.xueya.bean.UpdataHeadinfo;
import com.qc.cn.xueya.bean.User;
import com.qc.cn.xueya.customview.RoundAngleImageView;
import com.qc.cn.xueya.framework.base.BaseFragment;
import com.qc.cn.xueya.http.OkHttpUitls;
import com.qc.cn.xueya.http.RequestCallBack;
import com.qc.cn.xueya.utils.Config;
import com.qc.cn.xueya.utils.LogUtils;
import com.qc.cn.xueya.utils.MD5;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;


/**
 * Created by smz on 2018/11/10.
 */

public class PersonalFragment extends BaseFragment {

    private RoundAngleImageView iv_head;
    private TextView textView;
    private TextView tv_click_login;
    private TextView tv_nickname;
    private RelativeLayout rl_person;
    private File output;
    private Uri imageUri;
    private File file;
    private User us;
    private File ff_file;

    @Override
    public View getView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.fragment_personal,null);
    }

    @Override
    public void initView(View view) {
        iv_head = view.findViewById(R.id.iv_head);

        textView = view.findViewById(R.id.tv_10);
        tv_click_login = view.findViewById(R.id.tv_click_login);
        tv_nickname = view.findViewById(R.id.tv_nickname);

        rl_person = view.findViewById(R.id.rl_person);
    }

    @Override
    public void initClick() {
        rl_person.setOnClickListener(this);
        iv_head.setOnClickListener(this);
    }

    @Override
    public void initLogic() {
        us = Config.getUser(getActivity());
          if(Config.isLogin(getActivity())){
             textView.setVisibility(View.GONE);
             tv_click_login.setVisibility(View.GONE);
             iv_head.setVisibility(View.VISIBLE);
              Glide.with(getActivity()).load(us.getAvatar()).into(iv_head);
              tv_nickname.setVisibility(View.VISIBLE);
              tv_nickname.setText(us.getAccountstr());
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.rl_person:
                     enterActivity(DataActivity.class);
                break;
            case R.id.iv_head:
                AlertDialog dialog=    new AlertDialog.Builder(getActivity()) //
//                        .setTitle("列表对话框") //
                        .setCancelable(false) //
                        .setItems(items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if(which==0){
                                      //拍照
                                     checkPermission();

                                }else if(which==1){


                                    checkablum();


                                }else if(which==2){
                                         dialog.dismiss();
                                }



                            }
                        }).create(); // listener 为OnClickListener 监听器对象, 监听列表项被选中
                 dialog.show();
                break;
        }
    }

    String[] items={"拍照","相册","取消"};



    /**
     * 检查权限
     */
    public  void  checkPermission(){
        //6.0以后
        if(Build.VERSION.SDK_INT>=23){
            //检查是否已经给了权限
            int checkpermission= ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.CAMERA);
            if(checkpermission!= PackageManager.PERMISSION_GRANTED){//没有给权限
                //参数分别是当前活动，权限字符串数组，requestcode
                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CAMERA}, 1);
            }else{
                //调到相机页面
                intentCarmera();
            }
        }else{
            //调到 相机页面
            intentCarmera();
        }
    }

    public void checkablum(){
        //6.0以后
        if(Build.VERSION.SDK_INT>=23){
            //检查是否已经给了权限
            int checkpermission= ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if(checkpermission!= PackageManager.PERMISSION_GRANTED){//没有给权限
                //参数分别是当前活动，权限字符串数组，requestcode
                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }else{
                //调到相机页面
               getAblum();
            }
        }else{
            //调到 相机页面
         getAblum();
        }
    }

@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    switch (requestCode) {
        case 1: {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission was granted, yay! Do the
                // contacts-related task you need to do.
                //用户授权   调到相机页面
                intentCarmera();


            } else {
               show("用户未授权");
            }
            return;
        }
        case 2:
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission was granted, yay! Do the
                // contacts-related task you need to do.
                //用户授权   调到相机页面
                getAblum();


            } else {
                show("用户未授权");
            }
            break;
    }
}


public  void intentCarmera(){
    /**
     * 最后一个参数是文件夹的名称，可以随便起
     */
    file = new File(Environment.getExternalStorageDirectory(),"photo");
    if(!file.exists()){
        file.mkdir();
    }
    /**
     * 这里将时间作为不同照片的名称
     */
    output = new File(file,System.currentTimeMillis()+".jpg");

    /**
     * 如果该文件夹已经存在，则删除它，否则创建一个
     */
    try {
        if (output.exists()) {
            output.delete();
        }
        output.createNewFile();
    } catch (Exception e) {
        e.printStackTrace();
    }
    /**
     * 隐式打开拍照的Activity，并且传入CROP_PHOTO常量作为拍照结束后回调的标志
     * 将文件转化为uri
     */
    imageUri = Uri.fromFile(output);
    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);  //不指定自己uri


    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//        Uri photoURI = FileProvider.getUriForFile(getActivity(),
//                getActivity().getApplicationContext().getPackageName() + ".fileProvider",
//                output);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //添加这一句表示对目标应用临时授权该Uri所代表的文件
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri); //拍照完毕保存指定的URI
    } else {
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri); //拍照完毕保存指定的URI
    }
      startActivityForResult(intent, CROP_PHOTO);
    }


    public  static  final  int CROP_PHOTO=101;
   String path="";
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==getActivity().RESULT_CANCELED){
            show("取消操作");
        }
        if(resultCode==getActivity().RESULT_OK){
            switch (requestCode){
                case CROP_PHOTO:
                       if(data==null){  //指定自己的uri
                        uoloadfile(output);
                       }



                    break;
                case 3:
                   if(data!=null){
                       Bundle extras = data.getExtras();
                       if(extras!=null){
                           Bitmap bm=extras.getParcelable("data");
                           ff_file = new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis()+".jpg");
                           FileOutputStream b = null;
                           try {
                               b = new FileOutputStream(ff_file);
                           } catch (FileNotFoundException e) {
                               e.printStackTrace();
                           }
                           bm.compress(Bitmap.CompressFormat.JPEG,100,b);
                       }



                      uoloadfile(ff_file);
                   }

                      //提交     服务器





                    break;

                case ALUM:
                   if( data!=null){
//                       Glide.with(getActivity()).load(data.getData()).into(iv_head);
                       startPhotoZoom(data.getData());
                   }

                    break;


            }

        }

    }
    //上传头像 url
    private void changHeadUrl(final String data) {
      String url=Config.getUrl("kbb","resetProperty");

        HashMap<String ,String > map=new HashMap<>();

        map.put("keyword","avatar");
        map.put("sign", MD5.md5s(Config.Sign + "wjk"));
        map.put("tag","wjk");
        map.put("userid",us.getUserid());
        map.put("value",data);

        OkHttpUitls.getInstance().post(url, map, new RequestCallBack<String>() {
            @Override
            public void sucess(Call call, String t) {
                LogUtils.log_i(getActivity(),"上传user信息返回"+t);

                JSONObject object= null;
                try {
                    object = new JSONObject(t);
                    int code=object.optInt("state");


                    if(code==200){
                        //
                        us.setAvatar(data);
                        Config.setUser(getActivity(),us);
                          handler.post(new Runnable() {
                                       @Override
                                       public void run() {
                                           Glide.with(getActivity()).load(data).into(iv_head);
                                       }
                                   });
                              show("修改头像成功");
                    }else{
                        show("修改头像失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fail(Call call, IOException e) {

            }
        });


    }

    File ff;


    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }



    public void  getAblum(){
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, ALUM);
    }

 public    static  final int ALUM=102;



    public  void   uoloadfile(File f){
        String url="http://api.wws.xywy.com/upload_avatar.php";
        OkHttpUitls.getInstance().updateFile(url, f, new RequestCallBack<String>() {

            @Override
            public void sucess(Call call, String t) {
                LogUtils.log_i(getActivity(),"提交头像的返回"+t);


                Gson gson=new Gson();
                UpdataHeadinfo info=gson.fromJson(t,UpdataHeadinfo.class);
                if(info.getCode()==10000){
                    changHeadUrl(info.getData() );
                }else{
                    show(info.getMsg());
                }




            }

            @Override
            public void fail(Call call, IOException e) {

            }
        });

    }



}

