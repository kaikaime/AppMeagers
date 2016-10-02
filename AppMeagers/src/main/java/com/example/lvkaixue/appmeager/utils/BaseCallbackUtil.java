package com.example.lvkaixue.appmeager.utils;

import android.os.Message;

import com.example.lvkaixue.appmeager.listeners.ListenerClass;

import org.xutils.common.Callback;

import java.sql.SQLOutput;

/**
 * Created by ll on 2016/8/12.
 */
public class BaseCallbackUtil {
    public  static BaseCallback newInstanceBaseCallback(){
        return new BaseCallback();
    }

    public  static BaseCallback newInstanceBaseCallback(ChildHandler.StaticChildHandler handler){
        return new BaseCallback(handler);
    }
    private  static class BaseCallback implements Callback.CommonCallback<String>{
        private ChildHandler.StaticChildHandler handler;
        public BaseCallback(ChildHandler.StaticChildHandler handler){
            this.handler = handler;
        }
        public BaseCallback(){}
        @Override
        public void onSuccess(String t) {
            System.out.println("网络数据是"+t);
            Message message = new Message();
            message.obj = t;
            message.what = Constant.netConnSucess;
            handler.handleMessage(message);
        }

        @Override
        public void onError(Throwable throwable, boolean b) {

        }

        @Override
        public void onCancelled(CancelledException e) {

        }

        @Override
        public void onFinished() {

        }
    }
}
