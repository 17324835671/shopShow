package com.tx.study;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;

/**
 * @Author tx_li
 * @Date 2020/5/15 21:50
 * @Version 1.0
 */
public class ZkclientCrud {

    ZkClient zkClient;

    private String connectString = "192.168.0.60:2181,192.168.0.60:2183";

    public ZkclientCrud() {
        zkClient = new ZkClient(connectString);
    }

    public void createPer(String path, Object data) {
        zkClient.createPersistent(path, data);
    }
}
