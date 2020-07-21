package com.tx.study;

import com.sun.xml.internal.ws.server.provider.SyncProviderInvokerTube;
import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.locks.Lock;

/**
 * @Author tx_li
 * @Date 2020/5/1 11:51
 * @Version 1.0
 */
public class Test {
    int i;

    public static void main(String[] args) throws Exception {
       /* Test test = new Test();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                for (int k = 0; k < 10000; k++) {
                    test.add();
                }
            }).start();

        }
        Thread.sleep(1000);
        System.out.println(test.i);*/

        ZkclientCrud zkclientCrud=new ZkclientCrud();
        zkclientCrud.createPer("/test4/test5","666");
    }

    Lock lock = new TxAqs();

    public void add() {
        lock.lock();
        i++;
        lock.unlock();
    }

}

