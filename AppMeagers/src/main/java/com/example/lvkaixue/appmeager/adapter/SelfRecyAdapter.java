package com.example.lvkaixue.appmeager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.bean.MeaterBean;
import com.example.lvkaixue.appmeager.utils.LoadDrawableUtils;
import com.thinkcool.circletextimageview.CircleTextImageView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by lvkaixue on 2016/8/28.
 */
public class SelfRecyAdapter {
    private static MyRecyViewAdapter myRecyViewAdapter;
    public static MyRecyViewAdapter newInstanceRecyViewApdater(List<String> list,Context context){
        if(myRecyViewAdapter == null){
            myRecyViewAdapter = new MyRecyViewAdapter(list,context);
        }else {
            myRecyViewAdapter.setNotifyData(list);
        }
        return myRecyViewAdapter;
    }

    private static class MyRecyViewAdapter extends RecyclerView.Adapter<MyViewHolder>{
        private List<String> list;
        private MyViewHolder myViewHolder;
        private Context context;

        private MyRecyViewAdapter(List<String> list,Context context){
            this.list = list;
            this.context = context;
        }
        private void setNotifyData(List<String> list){
            this.list = list;
            notifyDataSetChanged();
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View viewHolder = View.inflate(context, R.layout.list_item_persion,null);
            TextView textView =new TextView(context);

            myViewHolder = new MyViewHolder(textView);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder viewHodler, int position) {
            viewHodler.textView.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
    private static class MyViewHolder extends RecyclerView.ViewHolder{
       TextView textView;
        public MyViewHolder(View convertView ) {
            super(convertView);
            this.textView = (TextView) convertView;
        }
    }
}