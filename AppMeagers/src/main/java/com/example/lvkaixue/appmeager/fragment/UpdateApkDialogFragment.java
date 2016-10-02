package com.example.lvkaixue.appmeager.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.activity.SplashActivity;
import com.example.lvkaixue.appmeager.bean.NewApkBean;
import com.example.lvkaixue.appmeager.utils.BaseCallbackUtil;
import com.example.lvkaixue.appmeager.utils.StringUtils;
import com.example.lvkaixue.appmeager.utils.ThreadUtils;
import com.example.lvkaixue.appmeager.utils.ToastUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpRequest;
import org.xutils.x;

import java.io.File;

public class UpdateApkDialogFragment extends DialogFragment {
    private LinearLayout mDialogView;
    private String  newApkDeic;
    private ProgressDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newApkDeic = getArguments().getString("newApkDeic");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        mDialogView = (LinearLayout) inflater.inflate(R.layout.fragment_update_apk_diolag, container, false);
        ((TextView) mDialogView.findViewById(R.id.tv_message)).setText(newApkDeic);
        ((TextView) mDialogView.findViewById(R.id.tv_cancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        ((TextView) mDialogView.findViewById(R.id.tv_agreen)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SplashActivity) getActivity()).downLoadApkFinish();
                dismiss();
            }
        });
        return mDialogView;
    }
}
