package personlife.gst.com.myapplication1;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import personlife.gst.com.myapplication1.greendao.gen.DaoMaster;
import personlife.gst.com.myapplication1.greendao.gen.DaoSession;

/**
 * Created by smz on 2018/11/22.
 */

public class MyApplication extends Application {
    public static MyApplication instances;
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        instances = this;
        setDatabase();  //初始化
    }
    /* 通过 DaoMaster 的内部类 DevOpenHelper，
      你可以得到一个便利的 SQLiteOpenHelper 对象。
     可能你已经注意到了，
     你并不需要去编写「CREATE TABLE」这样的 SQL 语句，
    因为 greenDAO 已经帮你做了。*/
        private void setDatabase() {
            //初始化

            mHelper = new DaoMaster.DevOpenHelper(instances, "notes-db", null);
            db = mHelper.getWritableDatabase();
            mDaoMaster = new DaoMaster(db);
            mDaoSession = mDaoMaster.newSession();
        }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

        /**
         * 单例模式 * *
         */
     public static MyApplication getInstances() {
        return instances;
    }
}
