package com.example.lvkaixue.appmeager.bean;

import com.example.lvkaixue.appmeager.utils.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

import java.util.List;

/**
 * Created by lvkaixue on 2016/9/11.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class PhotoItemBean {
    public String ret;
    public String messg;
    public List<PItem> list;
        public class PItem{
            public String picName;
            public String id;
            public String url;
        }

}
