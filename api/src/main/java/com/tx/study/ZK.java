package com.tx.study;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @Author tx_li
 * @Date 2020/5/13 23:25
 * @Version 1.0
 */
public class ZK implements Watcher {

    private String connectString="192.168.0.60:2181,192.168.0.60:2183";
    private ZooKeeper zooKeeper;

    public ZK(){
        try {
            zooKeeper=new ZooKeeper(connectString,5000,this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String createPer(String path,String data){
        try {
            return zooKeeper.create(path,data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent);
    }
}
