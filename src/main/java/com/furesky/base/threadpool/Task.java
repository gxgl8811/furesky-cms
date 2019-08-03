package com.furesky.base.threadpool;

/**
 * 线程任务
 */
public class Task {
	/**
	 * 任务实体对象
	 */
	private TaskEntity taskEntity;
	/**
	 * 线程是否结束
	 */
	private boolean isEnd = true;
	/**
	 * 线程是否在运行
	 */
	private boolean isRuning = false;
	/**
	 * 同步锁
	 */
	private Object endlock = new Object();

	/**
	 * 传入任务实体对象
	 */
	public Task(TaskEntity taskEntity) {
		this.taskEntity = taskEntity;
	}

	/**
	 * 获取线程状态
	 */
	public boolean isEnd() {
		return isEnd;
	}

	/**
	 * 设置线程状态
	 */
	public void setEnd(boolean isEnd) {
		this.isEnd = isEnd;
	}

	/**
	 * 开始线程任务
	 */
	public void startTask() throws Exception {
		isRuning = true;
		taskEntity.startTask();
		isRuning = false;
	}

	/**
	 * 结束线程任务
	 */
	public void endTask() throws Exception {
		synchronized (endlock) {
			while (isRuning) {
				try {
					endlock.wait();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
