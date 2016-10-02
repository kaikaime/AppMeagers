package com.example.lvkaixue.appmeager.utils;

import com.example.lvkaixue.appmeager.listeners.ListenerClass;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.Map;

/**
 * Created by ll on 2016/8/12.
 */
public class XUtil {
    public final static String POSTSYSC = "postSysc";
    public final static String GETSYSC = "postSysc";
    public final static String POST = "post";
    public final static String GET = "get";

    //发送get异步请求
    public static <T>Callback.Cancelable Get(String url,Map<String,String> mMap){
        return GetPost(url,mMap,GET, null);
    }

    //发送post异步请求
    public static <T>Callback.Cancelable post(String url, Map<String, String> mMap,ChildHandler.StaticChildHandler handler) {
        if (handler!=null) {
            return GetPost(url, mMap, POST,handler);
        }else{
            return GetPost(url, mMap, POST, null);
        }
    }


    //发送get同步请求
    public static  <T> T getSysc(String url,Map<String,String> mMap,Class<T> mClass) throws Throwable {
        return GetPostSysc(url, mMap, mClass, GETSYSC);
    }

    //发送post同步请求
    public static <T> T postSysc(String url,Map<String,String> mMap,Class<T> mClass) throws Throwable {
       return GetPostSysc(url,mMap,mClass,POSTSYSC);
    }

    //同步请求
    public static <T> T GetPostSysc(String url,Map<String,String> mMap,Class<T> mClass,String methodName) throws Throwable {
        if (url != null && !"".equals(url)&& mMap != null) {
            RequestParams requestParams = new RequestParams(url);
            for(Map.Entry<String,String>  mSet : mMap.entrySet()){
                String strName = mSet.getKey();
                String strValue = mSet.getValue();
                requestParams.addBodyParameter(strName, strValue);
            }
            if(GETSYSC.equals(methodName)){
                return x.http().getSync(requestParams,mClass);
            }else if(POSTSYSC.equals(methodName)){
                return x.http().postSync(requestParams,mClass);
            }
        }
        return null;
    }


    //get异步请求
    private static <T> Callback.Cancelable GetPost(String url, Map<String, String> mMap, String methName, ChildHandler.StaticChildHandler handler){
        if (url != null && !"".equals(url)&& mMap != null) {
            RequestParams requestParams = new RequestParams(url);
            for(Map.Entry<String,String>  mSet : mMap.entrySet()){
                String strName = mSet.getKey();
                String strValue = mSet.getValue();
                requestParams.addBodyParameter(strName, strValue);
            }
            if(GET.equals(methName)){
                return x.http().get(requestParams, BaseCallbackUtil.newInstanceBaseCallback());
            }else if(POST.equals(methName) && handler == null){
                return x.http().post(requestParams, BaseCallbackUtil.newInstanceBaseCallback());
            }else if (POST.equals(methName)&&handler !=null) {
                return x.http().post(requestParams, BaseCallbackUtil.newInstanceBaseCallback(handler));
            }
        }
        return null;
    }

    //枚举
    public  enum MethodName{
        POSTSYSC(XUtil.POSTSYSC),GETSYSC(XUtil.GETSYSC),GET(XUtil.GET),POST(XUtil.POST);
        private String method;
        MethodName(String method) {
            this.method = method;
        }
        public String getMethod(){
            return method;
        }
    }
}
