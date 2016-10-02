package com.example.lvkaixue.appmeager.fragment;

import android.view.View;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.base.BaseFragment;
import com.example.lvkaixue.appmeager.base.BasePager;

/**
 * Created by lvkaixue on 2016/9/8.
 */
public class ForwardPage extends BaseFragment {

    private View mContextView;

    @Override
    protected View initToolBarView() {
        return null;
    }

    @Override
    public BasePager.ResultState getResultState() {
        return BasePager.ResultState.SUCCESS;
    }

    @Override
    public View initSuccessView() {
        mContextView = View.inflate(mContext, R.layout.forword_item, null);
        TextView textView = (TextView) mContextView.findViewById(R.id.text_title);
        textView.setText("你暂时还没有任何人转发你的微薄!");
        return mContextView;
    }
}
