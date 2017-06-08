package com.liuzhuang.alltextdemo.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;


import com.liuzhuang.alltextdemo.BaseActivity;

import com.liuzhuang.alltextdemo.R;
import com.liuzhuang.alltextdemo.adapter.MyFragmentPagerAdapter;
import com.liuzhuang.alltextdemo.fragment.Fragment1;
import com.liuzhuang.alltextdemo.fragment.Fragment2;
import com.liuzhuang.alltextdemo.fragment.Fragment3;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/3 0003.
 */
public class RecyclerViewActivity extends BaseActivity {
    private List<Fragment> fragmentList=new ArrayList<Fragment>();
    private List<String> tabList=new ArrayList<String>();
    private ViewPager pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        pager = (ViewPager) findViewById(R.id.pager);
        initData();
    }

    private void initData() {
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        Fragment3 fragment3 = new Fragment3();

        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);
        tabList.add("LinearLayout");
        tabList.add("GridLayout");
        tabList.add("StaggeredGridLayout");

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, tabList);
        pager.setAdapter(adapter);

    }
}
