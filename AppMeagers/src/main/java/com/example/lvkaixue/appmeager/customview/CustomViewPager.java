package com.example.lvkaixue.appmeager.customview;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.activity.MyWeiBoItemActivity;
import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.fragment.AboutPage;
import com.example.lvkaixue.appmeager.fragment.FansPage;
import com.example.lvkaixue.appmeager.fragment.MeagerPage;

/**
 * Created by lvkaixue on 2016/8/23.
 */
public class CustomViewPager extends FrameLayout{
    //指针页面数量
    public final static int indicatorCount = 3;

    private Context context;
    private View mHeadNgView;
    private TextView weiboxTitle;
    private TextView mOriginal;
    private TextView mTvPhoto;
    private ImageView mNaIv;
    private ViewPager viewPager;

    public CustomViewPager(Context context) {
        super(context,null);
        initView();
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    //加载布局视图
    private void initView() {

        //获取顶部视图
        mHeadNgView = View.inflate(context, R.layout.navigation_main,this);

        //获取微博标题
        weiboxTitle = (TextView) mHeadNgView.findViewById(R.id.na_tv_weibo);

        //获取原创标题
        mOriginal = (TextView) mHeadNgView.findViewById(R.id.na_tv_original);

        //获取相册标题
        mTvPhoto = (TextView) mHeadNgView.findViewById(R.id.na_tv_photo);

        //导航线
        mNaIv = (ImageView) mHeadNgView.findViewById(R.id.na_iv_line);

        //viewpager
        viewPager = (ViewPager) mHeadNgView.findViewById(R.id.na_vp);

        //添加viewPager
        viewPager.setAdapter(new MyViewPagerFragment(((MyWeiBoItemActivity) context).getSupportFragmentManager()));

    }

    private class MyViewPagerFragment extends FragmentPagerAdapter{
        public MyViewPagerFragment(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position){
                case 0:
                    fragment =new MeagerPage();
                break;
                case 1:
                    fragment = new AboutPage();
                break;
                case 2:
                    fragment = new FansPage();
                break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return indicatorCount;
        }
    }
}
