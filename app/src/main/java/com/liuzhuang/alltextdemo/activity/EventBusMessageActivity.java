package com.liuzhuang.alltextdemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.liuzhuang.alltextdemo.BaseActivity;
import com.liuzhuang.alltextdemo.R;
import com.liuzhuang.alltextdemo.enerty.FirstEvent;

import org.greenrobot.eventbus.EventBus;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by Administrator on 2017/6/5 0005.
 */
@ContentView(R.layout.activity_eventbus_msg)
public class EventBusMessageActivity extends BaseActivity {
    @ViewInject(R.id.send_msg)
    private Button send_msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        send_msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(
                        new FirstEvent("FirstEvent btn clicked"));
            }
        });
    }
}
