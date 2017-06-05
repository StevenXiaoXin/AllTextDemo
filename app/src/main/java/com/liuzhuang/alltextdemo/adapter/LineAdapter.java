package com.liuzhuang.alltextdemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liuzhuang.alltextdemo.R;
import com.liuzhuang.alltextdemo.model.Meizi;
import com.liuzhuang.alltextdemo.utils.SnackbarUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2017/6/5 0005.
 */

public class LineAdapter extends RecyclerView.Adapter<LineAdapter.MyViewHolder>{
            private Activity act;
            private List<Meizi> meizis;
            private CoordinatorLayout coordinatorLayout;
            private RecyclerView recyclerview;
            private int screenwidth;
        public LineAdapter(RecyclerView recyclerview,Activity act, List<Meizi> meizis,CoordinatorLayout coordinatorLayout) {
        this.act=act;
        this.meizis=meizis;
        this.coordinatorLayout=coordinatorLayout;
            this.recyclerview=recyclerview;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(
                act).inflate(R.layout.line_meizi_item, parent,
                false);
        MyViewHolder holder = new MyViewHolder(view);

        WindowManager wm = (WindowManager) act
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        screenwidth =outMetrics.widthPixels;
        return holder;

    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if(holder.itemView.getScrollX()!=0){
            ((HorizontalScrollView)holder.itemView).fullScroll(View.FOCUS_UP);//如果item的HorizontalScrollView没在初始位置，则滚动回顶部
        }
        holder.ll.setMinimumWidth(screenwidth);//设置LinearLayout宽度为屏幕宽度
        holder.tv.setText("图"+position);
        Picasso.with(act).load(meizis.get(position).getUrl()).into(holder.iv);

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SnackbarUtil.ShortSnackbar(coordinatorLayout,"点击第"+position+"个",SnackbarUtil.Info).show();
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return meizis.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView iv;
        private TextView tv;
        private LinearLayout ll;
        public MyViewHolder(View view)
        {
            super(view);
            iv = (ImageView) view.findViewById(R.id.line_item_iv);
            tv=(TextView) view.findViewById(R.id.line_item_tv);
            ll=(LinearLayout) view.findViewById(R.id.ll);
        }
    }

    public void addItem(Meizi meizi, int position) {
        meizis.add(position, meizi);
        notifyItemInserted(position);
        recyclerview.scrollToPosition(position);
    }

    public void removeItem(final int position) {
        final Meizi removed=meizis.get(position);
        meizis.remove(position);
        notifyItemRemoved(position);
        SnackbarUtil.ShortSnackbar(coordinatorLayout,"你删除了第"+position+"个item",SnackbarUtil.Warning).setAction("撤销", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem(removed,position);
                SnackbarUtil.ShortSnackbar(coordinatorLayout,"撤销了删除第"+position+"个item",SnackbarUtil.Confirm).show();
            }
        }).setActionTextColor(Color.WHITE).show();
    }

    public void removeItem(Meizi meizi) {
        int position = meizis.indexOf(meizi);
        meizis.remove(position);
        notifyItemRemoved(position);
    }


}

