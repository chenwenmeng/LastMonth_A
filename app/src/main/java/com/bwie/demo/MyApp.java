package com.bwie.demo;

import android.app.Application;



import com.baidu.mapapi.SDKInitializer;

/**
 * 类描述:
 * 作者：陈文梦
 * 时间:2017/2/21 20:16
 * 邮箱:18310832074@163.com
 */

public class MyApp extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderUtils.initConfiguration(this);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
    }
}
