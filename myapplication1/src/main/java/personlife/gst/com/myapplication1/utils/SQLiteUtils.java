package personlife.gst.com.myapplication1.utils;

import java.util.ArrayList;
import java.util.List;

import personlife.gst.com.myapplication1.MyApplication;
import personlife.gst.com.myapplication1.ben.User;
import personlife.gst.com.myapplication1.greendao.gen.DaoSession;
import personlife.gst.com.myapplication1.greendao.gen.UserDao;

/**
 * Created by smz on 2018/11/22.
 */

public class SQLiteUtils {

    private static SQLiteUtils instance;
    UserDao userDao;
    DaoSession daoSession;

    private SQLiteUtils() {
        daoSession = MyApplication.getInstances().getDaoSession();
        userDao = daoSession.getUserDao();
    }
    //饿汉式  懒汉式

    // 高级 懒汉式
     public static SQLiteUtils getInstance() {
        if (instance == null) {
            synchronized (SQLiteUtils.class) {
                      if (instance == null) {
                             instance = new SQLiteUtils();
                        }
              }
        }
             return instance;
     }
//      //饿汉式
//    public SQLiteUtils  getInstance1(){
//          return  instance;
//    }
//    //懒汉式
//    public SQLiteUtils  getInstance2(){
//
//        if(instance==null){
//            instance=new SQLiteUtils();
//        }
//        return  instance;
//    }

    //增加
    public void addContacts(User testBean) {
        userDao.insert(testBean);
    }

//修改
    public void updateContacts(User testBean) {
         userDao.update(testBean);
 }
// 条件查询
    public List selectAllContacts(){
          userDao.detachAll();// 清除缓存
           List list1 = userDao.loadAll();
          return list1 == null ? new ArrayList() : list1;
 }
//   删除表中内容
    public void deleteAllContact() {
        userDao.deleteAll();

 }








}
