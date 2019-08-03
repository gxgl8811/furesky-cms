package com.furesky.base.threadpool;
class ITask1 implements TaskEntity{
	public void startTask() throws Exception {
		for (;;) {
			System.out.println("111");
			Thread.sleep(500);
		}
	}
}
class ITask2 implements TaskEntity{
	public void startTask() throws Exception {
		for (;;) {
			System.out.println("222222");
			Thread.sleep(500);
		}
	}
}
class ITask3 implements TaskEntity{
	public void startTask() throws Exception {
		for (;;) {
			System.out.println("333333333");
			Thread.sleep(500);
		}
	}
}
public class Demo {
	public static void main(String[] args) {
		ThreadPoolManager.getInstance().addTask(new Task(new ITask1()));
		ThreadPoolManager.getInstance().addTask(new Task(new ITask2()));
		ThreadPoolManager.getInstance().addTask(new Task(new ITask3()));
	}
}
