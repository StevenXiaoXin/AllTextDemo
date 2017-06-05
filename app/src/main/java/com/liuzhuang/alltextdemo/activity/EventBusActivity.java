package com.liuzhuang.alltextdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.liuzhuang.alltextdemo.BaseActivity;
import com.liuzhuang.alltextdemo.R;
import com.liuzhuang.alltextdemo.enerty.FirstEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Administrator on 2017/6/5 0005.
 */
@ContentView(R.layout.activity_eventbus)
public class EventBusActivity extends BaseActivity {
    @ViewInject(R.id.jump_msg)
    private Button jump_msg;
    @ViewInject(R.id.eventbus_msg)
    private TextView eventbus_msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

        jump_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EventBusActivity.this,EventBusMessageActivity.class);
                startActivity(intent);
            }
        });

    }
    @Subscribe
    public void onEventMainThread(FirstEvent event) {

        String msg = "onEventMainThread收到了消息：" + event.getMsg();

        eventbus_msg.setText(msg);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }
}
