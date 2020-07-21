package com.tx.study;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * @Author tx_li
 * @Date 2020/5/1 10:12
 * @Version 1.0
 */
public class TxAqs implements Lock {
    Map map=new ConcurrentHashMap();
    volatile AtomicReference<Thread> thread = new AtomicReference<>();
    volatile LinkedBlockingQueue queue = new LinkedBlockingQueue();

    @Override
    public void lock() {
        boolean add = true;
        while (!tryLock()) {
            if (add) {
                queue.offer(Thread.currentThread());
                add = false;
            } else {
                LockSupport.park();
            }
        }
        queue.remove(Thread.currentThread());

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return thread.compareAndSet(null, Thread.currentThread());
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        if (thread.compareAndSet(Thread.currentThread(), null)) {
            Iterator<Thread> iterator = queue.iterator();
            while (iterator.hasNext()) {
                Thread thread = iterator.next();
                LockSupport.unpark(thread);
            }
        }
        queue.remove(Thread.currentThread());
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
