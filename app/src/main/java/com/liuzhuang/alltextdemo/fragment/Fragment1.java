package com.liuzhuang.alltextdemo.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liuzhuang.alltextdemo.R;
import com.liuzhuang.alltextdemo.adapter.LineAdapter;
import com.liuzhuang.alltextdemo.model.Meizi;
import com.liuzhuang.alltextdemo.utils.MyOkhttp;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;


/**
 * Created by liuzhuang on 2016/12/20.
 */

public class Fragment1 extends Fragment {
    private static RecyclerView recyclerview;
    private CoordinatorLayout coordinatorLayout;
    private LineAdapter mAdapter;
    private List<Meizi> meizis;
    private LinearLayoutManager mLayoutManager;
    private int lastVisibleItem ;
    private int page=1;
    private ItemTouchHelper itemTouchHelper;
    private int screenwidth;
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean remove;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);

        initView(view);
        setListener();

        new GetData().execute("http://gank.io/api/data/福利/10/1");

        //获取屏幕宽度
        WindowManager wm = (WindowManager) getActivity()
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        screenwidth =outMetrics.widthPixels;
        return view;
    }

    private void initView(View view){
        coordinatorLayout=(CoordinatorLayout)view.findViewById(R.id.line_coordinatorLayout);
        recyclerview=(RecyclerView)view.findViewById(R.id.line_recycler);
        mLayoutManager=new LinearLayoutManager(getActivity());

        recyclerview.setLayoutManager(mLayoutManager);

        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.line_swipe_refresh) ;
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorPrimaryDark,R.color.colorAccent);
        swipeRefreshLayout.setProgressViewOffset(false, 0,  (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
    }

    private void setListener(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                new GetData().execute("http://gank.io/api/data/福利/10/1");
            }
        });

        itemTouchHelper=new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlags=0,swipeFlags=0;
                if(recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager){
                    dragFlags=ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
                }else if(recyclerView.getLayoutManager() instanceof LinearLayoutManager){
                    dragFlags=ItemTouchHelper.UP|ItemTouchHelper.DOWN;
                    //设置侧滑方向为从左到右和从右到左都可以
                    swipeFlags = ItemTouchHelper.END;
                }
                return makeMovementFlags(dragFlags,swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from=viewHolder.getAdapterPosition();
                int to=target.getAdapterPosition();
                Collections.swap(meizis,from,to);
                mAdapter.notifyItemMoved(from,to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                mAdapter.removeItem(viewHolder.getAdapterPosition());

            }

            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                super.onSelectedChanged(viewHolder, actionState);
                if(actionState==ItemTouchHelper.ACTION_STATE_DRAG){
                    viewHolder.itemView.setBackgroundColor(Color.LTGRAY);
                }
            }

            @Override
            public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                super.clearView(recyclerView, viewHolder);
                viewHolder.itemView.setBackgroundColor(Color.WHITE);
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                viewHolder.itemView.scrollTo(-(int)dX,-(int)dY);//根据item的滑动偏移修改HorizontalScrollView的滚动
                if(Math.abs(dX)>screenwidth/2&&!remove&&isCurrentlyActive){
                    //用户收滑动item超过屏幕5分之1，标记为要删除
                    remove=true;
                }else if(Math.abs(dX)<screenwidth/2&&remove&&!isCurrentlyActive){
                    //用户收滑动item没有超过屏幕5分之1，标记为不删除
                    remove=false;
                }
                if(actionState==ItemTouchHelper.ACTION_STATE_SWIPE&&remove==true&&!isCurrentlyActive){
                    //当用户滑动tem超过屏幕5分之1，并且松手时，执行删除item
                    if(viewHolder!=null&&viewHolder.getAdapterPosition()>=0){
                        mAdapter.removeItem(viewHolder.getAdapterPosition());
                        remove=false;
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        });
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
//                0：当前屏幕停止滚动；1时：屏幕在滚动 且 用户仍在触碰或手指还在屏幕上；2时：随用户的操作，屏幕上产生的惯性滑动；
                //               滑动状态停止并且剩余两个item时自动加载
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem +2>=mLayoutManager.getItemCount()) {
                    new GetData().execute("http://gank.io/api/data/福利/10/"+(++page));
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                //                获取加载的最后一个可见视图在适配器的位置。
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();

            }

        });
    }

    private class GetData extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            swipeRefreshLayout.setRefreshing(true);
        }

        @Override
        protected String doInBackground(String... params) {

            return MyOkhttp.get(params[0]);
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(!TextUtils.isEmpty(result)){

                JSONObject jsonObject;
                Gson gson=new Gson();
                String jsonData=null;

                try {
                    jsonObject = new JSONObject(result);
                    jsonData = jsonObject.getString("results");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(meizis==null||meizis.size()==0){
                    meizis= gson.fromJson(jsonData, new TypeToken<List<Meizi>>() {}.getType());
                    Meizi pages=new Meizi();
                    pages.setPage(page);
                    meizis.add(pages);
                }else{
                    List<Meizi> more= gson.fromJson(jsonData, new TypeToken<List<Meizi>>() {}.getType());
                    meizis.addAll(more);
                    Meizi pages=new Meizi();
                    pages.setPage(page);
                    meizis.add(pages);
                }

                if(mAdapter==null){
                    recyclerview.setAdapter(mAdapter = new LineAdapter(recyclerview,getActivity(),meizis,coordinatorLayout));
                    itemTouchHelper.attachToRecyclerView(recyclerview);
                }else{
                    mAdapter.notifyDataSetChanged();
                }
            }
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}
