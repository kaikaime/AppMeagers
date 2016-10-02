package com.example.lvkaixue.appmeager.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.base.BaseFragment;
import com.example.lvkaixue.appmeager.fragment.AboutPage;
import com.example.lvkaixue.appmeager.fragment.FansPage;
import com.example.lvkaixue.appmeager.fragment.MeagerPage;
import com.example.lvkaixue.appmeager.utils.AppBaseApplication;
import com.example.lvkaixue.appmeager.utils.BaseCallbackUtil;
import com.example.lvkaixue.appmeager.utils.Constant;

/**
 * Created by lvkaixue on 2016/8/28.
 */
public class PageAdapter extends FragmentPagerAdapter {
    public PageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        BaseFragment mBaseFragment = null;
        switch (position) {
            case 0:
                mBaseFragment = new MeagerPage();
                break;
            case 1:
                mBaseFragment = new AboutPage();
                break;
            case 2:
                mBaseFragment = new FansPage();
                break;
        }
        return mBaseFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        SpannableStringBuilder ssb = new SpannableStringBuilder(" "
                + Constant.pageTitle[position]); // space added before text for
        Context context =  AppBaseApplication.getmContext();

        ForegroundColorSpan fcs = new ForegroundColorSpan(Color.GREEN);//字体颜色设置为绿色
        ssb.setSpan(fcs, 1, ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);//设置字体颜色
        ssb.setSpan(new RelativeSizeSpan(1.2f), 1, ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ssb;
    }

    @Override
    public int getCount() {
        return Constant.pageCount;
    }
}
