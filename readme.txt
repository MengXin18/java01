1.volatile 轻量级同步机制
2.cas compare and set(swap) 比较并交换
   实现原理：unsafe类+自旋锁
3.cas 缺点
    cpu开销大，在高并发下，许多线程，更新一变量，多次更新不成功，循环反复，
    给cpu带来大量压力。
    只是一个变量的原子性操作，不能保证代码块的原子性。
    ABA问题
4.AtomicReference 原子引用
5.AtomicStampedReference 带时间戳（版本号）的原子引用
