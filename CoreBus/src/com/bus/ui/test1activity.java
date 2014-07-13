package com.bus.ui;


import com.bus.querytask.CoreBus;
import com.bus.querytask.CoreThread;
import com.bus.querytask.QueryTask;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
/**
 * @descripton 单核单任务框架测试
 * @author lihongjiang
 * @version 1.0
 */
public class test1activity extends Activity {

	CoreBus qb;
	CoreBus qb2;
	CoreThread task;
	CoreThread task2;
	int i,j= 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		qb = new CoreBus("CPU1");
		qb2= new CoreBus("CPU2");
		task = new CoreThread(new QueryTask() {

			@Override
			public void onComplete() {
				setTitle("你好帅啊");
				Log.v("test", i+"你好帅啊==");
			}

			@Override
			public void doTask() {
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
				Log.v("test", i+"==");
			}
		}, 12);
		task2 = new CoreThread(new QueryTask() {

			@Override
			public void onComplete() {
				setTitle("你好帅啊");
				Log.v("test", j+"你好帅啊");
			}

			@Override
			public void doTask() {
				j++;
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.v("test", j+"");
			}
		}, 15);
		qb.postQueryTask(task);
		qb2.postQueryTask(task2);
		qb.postQueryTask(task);
		qb2.postQueryTask(task2);
		qb.postQueryTask(task);
		qb2.postQueryTask(task2);
		qb.postQueryTask(task);
		qb2.postQueryTask(task2);
		qb.postQueryTask(task);
		qb2.postQueryTask(task2);
		qb.postQueryTask(task);
		qb2.postQueryTask(task2);
		
	}

	@Override
	protected void onDestroy() {
		if (qb != null) {
			// 移除消息或者线程
			qb.removeQueryTask(task);
		}
		super.onDestroy();
	}
}
