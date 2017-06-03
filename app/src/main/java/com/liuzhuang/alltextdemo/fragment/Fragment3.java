package com.liuzhuang.alltextdemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liuzhuang.alltextdemo.R;


/**
 * Created by liuzhuang on 2016/12/20.
 */

public class Fragment3 extends Fragment {

//    private MyListener listener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);
//        Log.e("main", "onCreateView");
//        String name = getArguments().getString("text");
//        Toast.makeText(getActivity().getApplicationContext(), name, Toast.LENGTH_SHORT).show();
//        listener.thank("thank your data");
        TextView tv_fragment1 = (TextView) view.findViewById(R.id.tv_fragment3);
        tv_fragment1.setText("Fragment3");
        return view;
    }

    @Override
    public void onAttach(Context context) {
        Log.e("main", "onAttach");
//        listener = (MyListener) context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.e("main", "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.e("main", "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        Log.e("main", "onResume");
        super.onResume();
    }

    @Override
    public void onStart() {
        Log.e("main", "onStart");
        super.onStart();
    }

    @Override
    public void onPause() {
        Log.e("main", "onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.e("main", "onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.e("main", "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.e("main", "onDestroy");
        super.onDestroy();
    }


    @Override
    public void onDetach() {
        Log.e("main", "onDetach");

        super.onDetach();
    }





}
