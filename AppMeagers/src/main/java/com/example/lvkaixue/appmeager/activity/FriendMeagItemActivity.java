package com.example.lvkaixue.appmeager.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.adapter.FriendItemFragAdapter;
import com.example.lvkaixue.appmeager.base.BaseActivity;
import com.example.lvkaixue.appmeager.bean.IdolBean;
import com.example.lvkaixue.appmeager.bean.MeagerMcountBean;
import com.example.lvkaixue.appmeager.utils.Constant;
import com.example.lvkaixue.appmeager.utils.LoadDrawableUtils;
import com.example.lvkaixue.appmeager.utils.StringUtils;
import com.thinkcool.circletextimageview.CircleTextImageView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_friend_meag_item)
public class FriendMeagItemActivity extends AppCompatActivity {
    private IdolBean.Data.Info info;
    private MeagerMcountBean mcountBean;

    @ViewInject(R.id.user_photo_item)
    private CircleTextImageView ci;

    @ViewInject(R.id.item_tv_name)
    private TextView nick;

    @ViewInject(R.id.item_tv_bir)
    private TextView bir;

    @ViewInject(R.id.tv_content)
    private TextView content;

    @ViewInject(R.id.item_tv_time)
    private TextView tvTime;

    @ViewInject(R.id.loaction)
    private TextView location;

    @ViewInject(R.id.tab)
    private TabLayout tabLayout;

    @ViewInject(R.id.view_pager)
    private ViewPager pager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    public void initView() {
        x.view().inject(this);

        info = (IdolBean.Data.Info) getIntent().getSerializableExtra("info");
        mcountBean = (MeagerMcountBean) getIntent().getSerializableExtra("mcountBean");

        System.out.println("我的数据"+mcountBean.msg+"   "+mcountBean.ret);
        //结束当前的activity
        ((ImageView) findViewById(R.id.goback_iv)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        pager.setAdapter(new FriendItemFragAdapter(getSupportFragmentManager(), Constant.strTitle));
        tabLayout.setupWithViewPager(pager);
        initViewData();
    }


    private void initViewData() {
        //加载页面数据
        if (info != null) {
            //设置用户图像
            if (info.head != null && !"".equals(info.head)) {
                LoadDrawableUtils.newInstanceDrawable(info.head + "/100", ci);
            } else {
                ci.setImageResource(R.mipmap.v5_bg_avatar);
            }

            //设置用户昵称
            nick.setText(info.nick);
            //个人简介
            bir.setText(info.tag != null && info.tag.size() > 0 ? info.tag.get(0).name : "");
            //微博内容
            content.setText(info.tweet != null && info.tweet.size() > 0 ? info.tweet.get(0).text : "");
            //设置发布时间
            tvTime.setText("发布时间: " + info.tweet != null && info.tweet.size() > 0 ? StringUtils.getDate(info.tweet.get(0).timestamp) : "");
            //设置内容发布地址
            location.setText("来自： " + info.tweet != null && info.tweet.size() > 0 ? info.tweet.get(0).from : "");

        }
    }

    public MeagerMcountBean getMcountBean(){
        return  mcountBean;
    }
}
