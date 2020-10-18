package com.mx.volatileDemo;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 
 * @author MengXin
 * @author 2020-10-18 12:13:30
 *
 * 1.stamp 版本号
 * 2.AtomicReference 原子引用
 * 3.AtomicStampedReference 带版本号的原子引用 解决ABA问题
 *
 */

public class CAS {
	public static void main(String[] args) {
		AtomicReference<Integer> a = new AtomicReference<Integer>(100);
		AtomicStampedReference<Integer> b = new AtomicStampedReference<Integer>(100, 1);
		
		new Thread(() -> {
			try {
				Thread.sleep(2000);
				a.compareAndSet(100, 105);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "a1").start();
		
		new Thread(() -> {
			a.compareAndSet(100, 101);
		}, "a2").start();
		
		new Thread(() -> {
			try {
				Thread.sleep(1000);
				a.compareAndSet(101, 100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "a3").start();
		
		new Thread(() -> {
			try {
				int stamp = b.getStamp();
				Thread.sleep(2000);
				System.out.println("b1->stamp:" + stamp);
				b.compareAndSet(100, 105, stamp, stamp+1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "b1").start();
		
		new Thread(() -> {
			int stamp = b.getStamp();
			System.out.println("b2->stamp:" + stamp);
			b.compareAndSet(100, 101, stamp, stamp+1);
		}, "b2").start();
		
		new Thread(() -> {
			try {
				int stamp = b.getStamp();
				Thread.sleep(1000);
				System.out.println("b3->stamp:" + stamp);
				b.compareAndSet(101, 100, stamp, stamp+1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "b3").start();
		
		while(Thread.currentThread().activeCount() > 2) {
			
		}
		
		System.out.println("a:" + a.get());
		System.out.println("b:" + b.getReference());
		
	}
	
}
