package com.example.lvkaixue.appmeager.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.adapter.PicItemAdapter;
import com.example.lvkaixue.appmeager.base.BaseActivity;
import com.example.lvkaixue.appmeager.base.BasePager;
import com.example.lvkaixue.appmeager.bean.PhotoItemBean;
import com.example.lvkaixue.appmeager.protocol.childprotcol.PhotoItemProtocol;
import com.example.lvkaixue.appmeager.utils.HandlerUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_photo_item)
public class PhotoItemActivity extends BaseActivity{
    @ViewInject(R.id.tl)
    private Toolbar mToolbar;

    @ViewInject(R.id.recyl_item)
    private RecyclerView recyclerView;

    @ViewInject(R.id.fram_id)
    private FrameLayout fral;

    private BasePager basePager;
    private View conentView;
    private String album;
    private PhotoItemBean bean;

    @Override
    public void initView() {
        x.view().inject(this);
        //回退按钮
        ((ImageView) findViewById(R.id.goback_iv)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        album = getIntent().getStringExtra("album");
        if(album !=null){
            //请求网络数据
            requestNetWork();
        }
    }


    private void requestNetWork() {
        basePager = new BasePager(this) {
            @Override
            public void OnRefrenshUI() {

            }

            @Override
            protected View initSuccessView() {
                conentView = View.inflate(PhotoItemActivity.this, R.layout.photo_item_view, null);
                x.view().inject(PhotoItemActivity.this, conentView);


                recyclerView.setLayoutManager(new GridLayoutManager(PhotoItemActivity.this,2));
                // 设置ItemAnimator
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                if(bean!=null){
                    recyclerView.setAdapter(PicItemAdapter.newInstanceRecyViewApdater(bean.list,PhotoItemActivity.this));
                }
                return conentView;
            }

            @Override
            public ResultState getResultState() {
                if(HandlerUtils.checkNetWork()){
                    bean = new PhotoItemProtocol(album).GetPost();
                    if(bean!=null){
                        return ResultState.SUCCESS;
                    }else {
                        return ResultState.EMPTY;
                    }
                }
                return ResultState.ERROR;
            }
        };

        /*if(fral.getChildCount()>0){
            fral.removeAllViews();
        }*/
        basePager.startThread();
        fral.addView(basePager);
    }

}
