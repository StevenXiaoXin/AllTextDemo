package com.liuzhuang.alltextdemo.activity;

import android.os.Bundle;

import com.liuzhuang.alltextdemo.BaseActivity;
import com.liuzhuang.alltextdemo.R;
import com.liuzhuang.alltextdemo.view.WuziqiPanel;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Administrator on 2017/6/3 0003.
 */
@ContentView(R.layout.activity_difinedview)
public class DefinedViewActivity extends BaseActivity {
    @ViewInject(R.id.wuziqi)
    private WuziqiPanel wuziqiPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

}
