package com.furesky.base.threadpool;

import java.util.concurrent.BlockingQueue;

/**
 * 任务线程
 */
public class TaskThread extends Thread {
	/**
	 * 线程状态：true正在执行，false已经关闭
	 */
	private boolean flag;
	/**
	 * 线程任务
	 */
	private Task task;
	/**
	 * 线程任务队列
	 */
	private BlockingQueue<Task> taskQueue;

	/**
	 * 初始化任务线程，并运行
	 */
	public TaskThread(BlockingQueue<Task> taskQueue) {
		this.flag = true;
		this.taskQueue = taskQueue;
		super.start();
	}

	@Override
	public void run() {
		// 循环队列，执行任务
		while (flag && taskQueue != null) {
			synchronized (taskQueue) {
				// 任务队列为空，则等待
				while (taskQueue.isEmpty() && flag) {
					try {
						taskQueue.wait();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				// 任务队列不为空，则从队列中取出一个任务
				try {
					// 取出并移除队列的头元素
					task = taskQueue.poll();
				} catch (Exception e) {
					e.printStackTrace();
					task = null;
				}
				if (task == null) {
					continue;
				}
			}

			// 执行任务
			try {
				task.setEnd(false);
				task.startTask();
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 判断是否已经完成任务
			try {
				if (!task.isEnd()) {
					task.setEnd(true);
					task.endTask();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭线程
	 */
	public void closeThread() {
		flag = false;
		try {
			if (task != null) {
				task.endTask();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 释放锁（唤醒在此对象监视器上等待的所有线程）
		synchronized (taskQueue) {
			taskQueue.notifyAll();
		}
	}
}
