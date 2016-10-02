package com.example.lvkaixue.appmeager.utils;

import com.example.lvkaixue.appmeager.single.SingleUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lvkaixue on 2016/9/2.
 */
public class StringUtils {
    public static Map<String,String> getMap(){
        Map<String,String> mMap = new HashMap<>();
        mMap.put(Constant.formatKey, Constant.formatValue);
        mMap.put(Constant.openIdKey,SingleUser.getSingleUser().getOpenId());
        mMap.put(Constant.accessTokenKey,SingleUser.getSingleUser().getAccessToken());
        mMap.put(Constant.oauthConsumerKeyKey,SingleUser.getSingleUser().getOauthConsumerKey());
        return mMap;
    }

    //将long类型转化为日期date类型
    public static String getDate(String time){
        Calendar c= Calendar.getInstance();
        c.setTimeInMillis(Long.parseLong(time));
        return new SimpleDateFormat("mm-dd hh:mm").format(c.getTime());
    }
}
