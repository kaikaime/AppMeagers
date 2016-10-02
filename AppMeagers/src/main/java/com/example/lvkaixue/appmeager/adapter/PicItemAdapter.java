package com.example.lvkaixue.appmeager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lvkaixue.appmeager.R;
import com.example.lvkaixue.appmeager.bean.PhotoItemBean;
import com.example.lvkaixue.appmeager.utils.LoadDrawableUtils;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * Created by lvkaixue on 2016/8/28.
 */
public class PicItemAdapter {
    private static MyRecyViewAdapter myRecyViewAdapter;
    public static MyRecyViewAdapter newInstanceRecyViewApdater(List<PhotoItemBean.PItem> list,Context context){
        if(myRecyViewAdapter == null){
            myRecyViewAdapter = new MyRecyViewAdapter(list,context);
        }else {
            myRecyViewAdapter.setNotifyData(list);
        }
        return myRecyViewAdapter;
    }

    private static class MyRecyViewAdapter extends RecyclerView.Adapter<MyViewHolder>{
        private List<PhotoItemBean.PItem> list;
        private Context context;

        private MyRecyViewAdapter(List<PhotoItemBean.PItem> list,Context context){
            this.list = list;
            this.context = context;
        }
        private void setNotifyData(List<PhotoItemBean.PItem> list){
            this.list = list;
            notifyDataSetChanged();
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View viewItem = View.inflate(context,R.layout.photo_item_card,null);
            MyViewHolder myViewHolder = new MyViewHolder(viewItem);
           return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder viewHodler, int position) {
            PhotoItemBean.PItem item = list.get(position);
            if(item.url!=null){
                System.out.println("url: "+item.url);
                x.image().bind(viewHodler.imageView,item.url,ImageOptions.DEFAULT);
                viewHodler.picNick.setText(item.picName);
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }
    private static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView picNick;
        public MyViewHolder(View convertView ) {
            super(convertView);
            imageView = (ImageView) convertView.findViewById(R.id.per_pic);
            picNick = (TextView) convertView.findViewById(R.id.pic_nick);
        }
    }
}