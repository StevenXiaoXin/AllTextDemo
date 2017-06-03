package com.liuzhuang.alltextdemo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.liuzhuang.alltextdemo.BaseActivity;
import com.liuzhuang.alltextdemo.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity implements View.OnClickListener{

    @ViewInject(R.id.btn_recyclerview)
    private Button btn_recyclerview;
    @ViewInject(R.id.btn_defined_view)
    private Button btn_defined_view;


    private Activity act;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        act=this;
        context=this;
        initData();
    }

    private void initData() {
        btn_recyclerview.setOnClickListener(this);
        btn_defined_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_recyclerview:
                Intent intent1=new Intent(act,RecyclerViewActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_defined_view:
                Intent intent2=new Intent(act,DefinedViewActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
