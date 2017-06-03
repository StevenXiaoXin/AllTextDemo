package com.liuzhuang.alltextdemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liuzhuang.alltextdemo.R;


/**
 * Created by liuzhuang on 2016/12/20.
 */

public class Fragment1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);
        TextView tv_fragment1 = (TextView) view.findViewById(R.id.tv_fragment1);
        tv_fragment1.setText("Fragment1");
        return view;
    }
}
