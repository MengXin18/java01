package com.mx.volatileDemo;

/**
 * volatile-轻量级同步锁
 * 1提供可见性
 * @author mengXin
 * @date ：2020-10-15 21:46:49
 *
 */
public class Volatile1 {
	
	
	public static void main(String[] args) throws InterruptedException {
		volatileDemo vd = new volatileDemo();
//		// 单线程，没问题
//		vd.addI();
//		System.out.println(vd.i);
		
		new Thread(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			vd.addI();
			System.out.println(vd.i);
		},"t1").start();
		while(vd.i == 0) {
			
		}
		System.out.println("volatile的可见性生效了");
	}
}

class volatileDemo {
	volatile int i=0;
	void addI(){
		this.i = 60;
	}
}	
