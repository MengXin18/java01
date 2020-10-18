package com.mx.volatileDemo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @author mengXin
 * @date ：2020-10-16 21:46:49
 * 1.volatile不保证原子性
 *
 */

// 常规操作 不保证原子性
class AtomicTest1{
	int i = 0;
	void setI() {
		i++;
	}
}

// volatile轻量级同步锁 不保证原子性
class AtomicTest2{
	volatile int i = 0;
	void setI() {
		i++;
	}
}

// synchronized保证原子性，效率低，杀鸡焉用牛刀
class AtomicTest3{
	int i = 0;
	synchronized void setI() {
		i++;
	}
}

// 原子型整形，保证原子性
class AtomicTest4{
	AtomicInteger i = new AtomicInteger();
	void setI() {
		this.i.getAndAdd(1);
	}
}

public class Volatile2 {
	public static void main(String[] args) {
		AtomicTest1 a = new AtomicTest1();
		AtomicTest2 s = new AtomicTest2();
		AtomicTest3 d = new AtomicTest3();
		AtomicTest4 f = new AtomicTest4();
		for(int i = 0; i < 5; i++) {
			new Thread(() -> { 
				for(int j=0; j<1000; j++) {
					a.setI();
				}
			}, String.valueOf(i)).start();
		}
		
		for(int i = 0; i < 5; i++) {
			new Thread(() -> { 
				for(int j=0; j<1000; j++) {
					s.setI();
				}
			}, String.valueOf(i)).start();
		}
		
		for(int i = 0; i < 5; i++) {
			new Thread(() -> { 
				for(int j=0; j<1000; j++) {
					d.setI();
				}
			}, String.valueOf(i)).start();
		}
		
		for(int i = 0; i < 5; i++) {
			new Thread(() -> { 
				for(int j=0; j<1000; j++) {
					f.setI();
				}
			}, String.valueOf(i)).start();
		}
		
		while (Thread.activeCount() > 2) {
			
		}
		
		System.out.println("a.i=:" + a.i);
		System.out.println("s.i=:" + s.i);
		System.out.println("d.i=:" + d.i);
		System.out.println("f.i=:" + f.i.get());
	}
	
	
}








