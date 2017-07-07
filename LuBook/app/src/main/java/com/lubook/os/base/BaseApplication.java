package com.lubook.os.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wowo on 2017/6/23.
 */

public class BaseApplication extends Application {
    private final static String TAG = "main::MeApplication";
    private Context applicationContext;
    public static BaseApplication mInstance;
    public List<BaseActivity> listActivity = null;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        applicationContext = getApplicationContext();
        listActivity = new ArrayList<BaseActivity>();
        initlog();
    }

    private void initlog() {
        Utils.init(applicationContext);
        LogUtils.Builder builder = new LogUtils.Builder().setBorderSwitch(false).setLog2FileSwitch(false);
    }

    public Context getContext() {
        return applicationContext;
    }

    public static BaseApplication getInstance() {
        return mInstance;
    }

    public void AddActivity(BaseActivity a) {
        if (a != null) {
            LogUtils.v(TAG, "addActivity: " + a.getLocalClassName());
            listActivity.add(a);
        }
    }


    public void removeActivity(BaseActivity b) {
        if (b != null) {
            LogUtils.v(TAG, "removeActivity: " + b.getLocalClassName());
            listActivity.remove(b);
            b.finish();
        }
    }

    public void exit() {
        if (listActivity != null && listActivity.size() > 0) {
            for (BaseActivity b : listActivity) {
                listActivity.remove(b);
                b.finish();
            }
        }
    }
}