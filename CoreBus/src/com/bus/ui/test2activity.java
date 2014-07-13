package com.bus.ui;

import com.bus.asynchandler.ShareCoreAsyncWorkHandler;
import com.bus.querytask.CoreThread;
import com.bus.querytask.CoreBus;
import com.bus.querytask.QueryTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

public class test2activity extends Activity {

	ShareCoreAsyncWorkHandler worker;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// 开启工作线程
		worker = new ShareCoreAsyncWorkHandler() {
			@Override
			protected void onCompleteWork(int staskId) {
				switch (staskId) {
				case 1:
					// 初始化界面
					break;
				}
			}
		};
		worker.doWork(1);
	}

}
