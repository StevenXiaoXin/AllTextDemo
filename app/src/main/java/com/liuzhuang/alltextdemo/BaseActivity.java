package com.liuzhuang.alltextdemo;

import android.app.Activity;
import android.os.Bundle;

import com.liuzhuang.alltextdemo.dialog.ProDialog;

import org.xutils.x;


/**
 * @author Liu
 * @description 基本Activity
 */
public  class BaseActivity extends Activity {

	public static final String BATG = "BaseActivity";
	private ProDialog proDialog;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		x.view().inject(this);

	}
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}


	public void showDialog(){
		if (proDialog==null){
			proDialog=new ProDialog(this);
		}
		if (proDialog.isShowing()==false){
			proDialog.show();
		}
	}

	public void dissDialog(){
		if (proDialog!=null && proDialog.isShowing()){
			proDialog.dismiss();
		}
	}


}
