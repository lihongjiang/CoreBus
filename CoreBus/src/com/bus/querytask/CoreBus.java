package com.bus.querytask;

import java.util.LinkedHashSet;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * @descripton 单核单任务模型
 * @author lihongjiang
 * @version 1.0
 */
public class CoreBus {

	// 统一处理UI总线
	// private Handler mainHandler = new Handler();

	// 内部消息处理总线,各自处理自己的界面更新
	private Handler handler;
	// 任务托管
	private LinkedHashSet<CoreThread> sets = new LinkedHashSet<CoreThread>();

	// 总线创建
	public CoreBus(String name) {
		sets.clear();
		CoreManager workhandler = new CoreManager(name) {

			@Override
			public boolean handleMessage(Message msg) {
				Log.v("test", "task end");
				for (final CoreThread hr : sets) {
					if (hr != null && hr.taskId == msg.what) {
						handler.post(new Runnable() {
							@Override
							public void run() {
								Log.v("test", "task callback");
								hr.task.onComplete();
							}
						});
						return true;// 表示消息已经消息
					}
				}
				return super.handleMessage(msg);
			}
		};
		workhandler.setPriority(Thread.MIN_PRIORITY);
		workhandler.start();
		Log.v("test", workhandler.getName());
		// 工作hander在非UI线程中自己给自己发消息处理,可以实现单核单任务模型
		handler = new Handler(workhandler.getLooper(), workhandler);
	}

	/**
	 * 启动任务,是异步方法
	 * 
	 * @param task
	 */
	public void postQueryTask(CoreThread task) {
		task.handler = handler;
		sets.add(task);
		handler.post(task);
	}

	/**
	 * 取消存在任务实例
	 * 
	 * @param task
	 */
	public void removeQueryTask(CoreThread task) {
		if (handler != null) {
			for (CoreThread t : sets) {
				if (t != null && t == task) {
					handler.removeCallbacks(task);
					Log.v("test", "task cancel");
					return;
				}
			}
		}
	}

	/**
	 * 按任务Id取消任务,主要场合用于删除匿名任务.
	 * 
	 * @param task
	 */
	public void removeQueryTask(int taskId) {
		if (handler != null) {
			for (CoreThread t : sets) {
				if (t != null && t.taskId == taskId) {
					handler.removeCallbacks(t);
					Log.v("test", "taskId cancel");
					return;
				}
			}
		}
	}

}
