package com.example.lvkaixue.appmeager.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.adapter.PageAdapter;
import com.example.lvkaixue.appmeager.base.BaseActivity;
import com.example.lvkaixue.appmeager.bean.MeaterBean;
import com.example.lvkaixue.appmeager.utils.LoadDrawableUtils;
import com.thinkcool.circletextimageview.CircleTextImageView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_my_wei_bo_item)
public class MyWeiBoItemActivity extends BaseActivity{
    @ViewInject(R.id.tabs)
    PagerSlidingTabStrip pagerSlidingTabStrip ;

    @ViewInject(R.id.pager)
    private ViewPager viewPager;

    //用户图像
    @ViewInject(R.id.user_photo)
    private CircleTextImageView circleTextImageView;

    //用户名称
    @ViewInject(R.id.tv_nick)
    private TextView mUserNick;

    //关注数量
    @ViewInject(R.id.tv_about)
    private TextView mAboutCount;

    //粉丝数量
    @ViewInject(R.id.tv_fans)
    private TextView mFansCount;

    //简介
    @ViewInject(R.id.tv_biref)
    private TextView mBiref;

    private MeaterBean meaterBean;

    @Override
    public void initView() {
        x.view().inject(this);

        //获取上一个activity传送过来的数据
        meaterBean = (MeaterBean) getIntent().getSerializableExtra("MeaterBean");
        //显示数据到ui界面上
        showDataUI();
        //viewpager设置适配器
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        pagerSlidingTabStrip.setViewPager(viewPager);
    }

    private void showDataUI() {
        if(meaterBean.data != null){
            //设置用户图像
            LoadDrawableUtils.newInstanceDrawable(meaterBean.data.head+"/100",circleTextImageView);
            mUserNick.setText(meaterBean.data.nick);//设置用户昵称
            mAboutCount.setText("关注 "+meaterBean.data.idolnum);//设置关注数量
            mFansCount.setText("粉丝 "+meaterBean.data.fansnum);//设置粉丝数量
            mBiref.setText("简介 "+meaterBean.data.introduction);
        }
    }

    @Event(value = {R.id.goback_iv},type = View.OnClickListener.class)
    private void onclick(View v){
        switch (v.getId()){
            case R.id.goback_iv:
                finish();
            break;
        }
    }
}
