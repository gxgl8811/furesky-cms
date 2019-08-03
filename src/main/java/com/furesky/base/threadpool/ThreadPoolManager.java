package com.furesky.base.threadpool;

import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 线程池管理器
 */
public class ThreadPoolManager {
	/**
	 * 线程数量
	 */
	private int threadNum;
	/**
	 * 默认线程数量
	 */
	private int defaultThreadNum;
	/**
	 * 线程容器：即线程池
	 */
	private Vector<TaskThread> threadPool;
	/**
	 * 线程任务队列
	 */
	private BlockingQueue<Task> taskQueue;
	/**
	 * 线程池管理器实例
	 */
	private static ThreadPoolManager instance;

	/**
	 * 获取线程池管理器实例，单例
	 */
	public static ThreadPoolManager getInstance() {
		if (instance == null) {
			synchronized (ThreadPoolManager.class) {
				if (instance == null) {
					instance = new ThreadPoolManager(4);
				}
			}

		}
		return instance;
	}

	/**
	 * 构造管理器实例
	 */
	private ThreadPoolManager(int i) {
		defaultThreadNum = 10;
		taskQueue = new LinkedBlockingQueue<>();
		if (i <= 0) {
			i = defaultThreadNum;
		}
		createThreadPool(i);
	}

	private ThreadPoolManager() {
		this(10);
	}

	/**
	 * 创建线程池
	 */
	private void createThreadPool(int i) {
		// 创建容器
		if (threadPool == null) {
			threadPool = new Vector<>();
		}
		// 创建线程
		synchronized (threadPool) {
			TaskThread taskThread;
			for (int j = 0; j < i; j++) {
				threadNum++;
				taskThread = new TaskThread(taskQueue);
				threadPool.addElement(taskThread);
			}
		}
	}

	/**
	 * 是否已经执行完所有任务
	 */
	public boolean isAllTaskFinish() {
		return taskQueue.isEmpty();
	}

	/**
	 * 获取线程数量
	 */
	public int getThreadNum() {
		return threadNum;
	}

	/**
	 * 新增线程任务
	 */
	public void addTask(Task task) {
		if (task == null) {
			return;
		}
		synchronized (taskQueue) {
			taskQueue.offer(task);
			taskQueue.notifyAll();
		}
	}

	/**
	 * 关闭线程池所有线程
	 */
	public void closeThreads() {
		TaskThread taskThread;
		while (!threadPool.isEmpty()) {
			try {
				taskThread = threadPool.remove(0);
				taskThread.closeThread();
				continue;
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}
}
