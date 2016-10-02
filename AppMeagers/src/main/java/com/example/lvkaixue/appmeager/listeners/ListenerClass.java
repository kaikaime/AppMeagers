package com.example.lvkaixue.appmeager.listeners;


import android.os.Message;

/**
 * Created by lvkaixue on 2016/7/30.
 */
public class ListenerClass {
    public interface BaseProtocolListener<T>{
          void OnProtocol(T mT);
    }
    public interface ProtocolListener<T> extends BaseProtocolListener{}

    public interface HandlerFace{
        void sendInfor(Message msg);
    }

    public interface RefrenshUIHandler{
         void mUIHandler(Message message);

    }
}
