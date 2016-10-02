package com.example.lvkaixue.appmeager.utils;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.example.lvkaixue.appmeager.listeners.ListenerClass;

import org.xutils.x;

/**
 * Created by lvkaixue on 2016/7/31.
 */
public class AppBaseApplication extends Application {
    private static Context mContext;
    private static MainHandler mHandler;
    private static Looper mLooper;
    private static Thread mThread;

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        mLooper = Looper.myLooper();
        mContext = getApplicationContext();
        mHandler = new MainHandler();
        mThread = new Thread();
    }

    public static Context getmContext(){
        return  mContext;
    }
    public static MainHandler getmHandler(){
        return mHandler;
    }
    public static Looper getmLooper(){
        return mLooper;
    }

    public static Thread getThread() {
        return mThread;
    }

    public class MainHandler extends Handler{
        ListenerClass.HandlerFace mInterfaces ;
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(mInterfaces!=null){
                mInterfaces.sendInfor(msg);
            }
        }
        public void setmInterfaces( ListenerClass.HandlerFace mInterfaces){
            this.mInterfaces = mInterfaces;
        }
    }

}
