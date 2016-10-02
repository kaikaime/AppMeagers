package com.example.lvkaixue.appmeager.fragment;

import android.graphics.Color;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.adapter.RecyAdapterIndex;
import com.example.lvkaixue.appmeager.base.BaseFragment;
import com.example.lvkaixue.appmeager.base.BasePager;
import com.example.lvkaixue.appmeager.bean.IdolBean;
import com.example.lvkaixue.appmeager.customview.DividerItemDecoration;
import com.example.lvkaixue.appmeager.customview.RecycleViewDivider;
import com.example.lvkaixue.appmeager.listeners.ListenerClass;
import com.example.lvkaixue.appmeager.protocol.childprotcol.IdolDataProtocol;
import com.example.lvkaixue.appmeager.utils.AppBaseApplication;
import com.example.lvkaixue.appmeager.utils.HandlerUtils;
import com.example.lvkaixue.appmeager.utils.ThreadUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by Administrator on 2016/8/19 0019.
 */
public class MeagerPage extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,ListenerClass.HandlerFace {
    private View mViewFoot;

    @ViewInject(R.id.page_rl)
    private RecyclerView mRecyclerView;

    @ViewInject(R.id.swipe)
    private SwipeRefreshLayout swipeRefreshLayout;

    //主线程中的handler
    private AppBaseApplication.MainHandler handler;
    private List<IdolBean.Data.Info> list;
    private IdolBean idolBean;


    @Override
    protected View initToolBarView() {
        return null;
    }

    @Override
    public BasePager.ResultState getResultState() {
        //先检查网络，如果网络不存在首先提醒用户联网
        if (HandlerUtils.checkNetWork()) {

            idolBean = new IdolDataProtocol("30","0").GetPost();
            if(idolBean !=null){
                return BasePager.ResultState.SUCCESS;
            }else {
                return BasePager.ResultState.EMPTY;
            }

        }
        return BasePager.ResultState.ERROR;
    }

    @Override
    public View initSuccessView() {
        mViewFoot = View.inflate(mContext, R.layout.meager_page,null);
        x.view().inject(this, mViewFoot);

        //设置适配器
        mRecyclerView.addItemDecoration(new RecycleViewDivider(mContext,LinearLayoutManager.VERTICAL,50,Color.BLACK));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        if(idolBean.data!= null ){
            list = idolBean.data.info;
            mRecyclerView.setAdapter(RecyAdapterIndex.newInstanceRecyViewApdater(list, mContext));
        }
        //设置刷新控件的背景颜色
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.YELLOW);
        //设置刷新进度的动画
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.BLACK);
        //单位Px   向下滑动多少单位  出现这个刷新
        swipeRefreshLayout.setDistanceToTriggerSync(50);
        swipeRefreshLayout.setOnRefreshListener(this);

        //主线程中handler
        handler = HandlerUtils.getHandler();
        handler.setmInterfaces(MeagerPage.this);
        return mViewFoot;
    }


    /**
     * 刷新加载数据
     */
    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        //请求网络数据
        ThreadUtils.mThreadUI(new Runnable() {
            @Override
            public void run() {
              int resultState =  getResultState().getLoadEmpty();
                if( getResultState().getLoadEmpty() == BasePager.LOAD_SUCCESS){
                    Message message  = handler.obtainMessage();
                    handler.sendMessage(message);
                }
            }
        });
    }

    @Override
    public void sendInfor(Message msg) {
        //关闭下拉刷新
        swipeRefreshLayout.setRefreshing(false);
       mRecyclerView.setAdapter(RecyAdapterIndex.newInstanceRecyViewApdater(list, mContext));
    }
}
