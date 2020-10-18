package com.mx.volatileDemo;

/**
 * 
 * @author mengXin
 * @date ：2020-10-17 10:25:58
 * 3禁止指令重排  写在读之前
 *
 */


class Demo{
	int x = 1;
	boolean flag = false;
	
	void method1() {
		x = 5;
		flag = true;
	}
	
	void method2() {
		while(flag) {
			x += 5;
			flag = false;
		}
	}
}

public class Volatile3 {
	
	
	
	public static void main(String[] args) {
		Demo d = new Demo();
		
//		多线程示例 存在，多线程考虑性能问题，会对指令重排，提高效率
//		for(int i=0;i<10000;i++) {
//			new Thread(() -> {
//				d.method1();
//				d.method2();
//				System.out.println("第"+Thread.currentThread().getName()+"线程d.x:" + d.x );
//			}, String.valueOf(i)).start();
//		}
		
//		单线程示例  不存在，单线程指令自上而下执行
		
		while(d.x != 5) {
			d.method1();
			d.method2();
			System.out.println(d.x);
		}
		System.out.println(d.x);
	}
}
