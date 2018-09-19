package com.xiaochen.middleware.zk;

import com.google.gson.Gson;
import org.apache.zookeeper.*;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ZkConnManager implements Watcher {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    protected ZooKeeper zooKeeper;
    private static final int SESSION_TIME_OUT = 5000;
    /**
     * zk创建连接需要时间等待完成，所以countDownLatch.await。后置countDown完成计数
     */
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    private ZkConnManager(){
    }

    public ZkConnManager(String url) throws IOException, InterruptedException {
       connection(url);
    }

    public void connection(String url) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper(url,SESSION_TIME_OUT,this);
        countDownLatch.await();
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        KeeperState state = watchedEvent.getState();
        logger.info("[KeeperState]name:{},intVal:{}",state.name(),state.getIntValue());
        if (state == KeeperState.SyncConnected){
            countDownLatch.countDown();

            String path = watchedEvent.getPath();
            Event.EventType type = watchedEvent.getType();
            if (Event.EventType.NodeCreated == type) {
                logger.info("[WatchedEvent]NodeCreated:"+ convertStrByPath(path));
            } else if (Event.EventType.NodeDataChanged == type) {
                logger.info("[WatchedEvent]NodeDataChanged:"+ convertStrByPath(path));
            } else if (Event.EventType.NodeDeleted == type) {
                logger.info("[WatchedEvent]NodeDeleted:"+ convertStrByPath(path));
            } else if (Event.EventType.NodeChildrenChanged == type) {
                logger.info("[WatchedEvent]NodeChildrenChanged:"+ convertStrByPath(path));
            }
        }

    }

    public String createGroup(String groupName,int createModeType) throws KeeperException, InterruptedException {
        String path = zooKeeper.create("/" + groupName,null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.fromFlag(createModeType));
        logger.info("createGroup,return path:{}",path);
        return path;
    }

    public List<String> getChildren(String groupName) throws KeeperException, InterruptedException {
        List<String> clients = new ArrayList<String>();
        String path = "/" + groupName;
        clients = zooKeeper.getChildren(path,false);
        forEachList(clients);
        return clients;
}

    public void closeConn() {
        try {
            zooKeeper.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void forEachList(List<String> pathes) {
        if (pathes != null){
            pathes.forEach(path -> {
                convertStrByPath("/"+path);
            });
        }
    }

    private String convertStrByPath(String path) {

        String str = null;
        try {

            Stat stat = zooKeeper.exists(path, false);
            if (stat == null)
                return "";

            byte[] data = zooKeeper.getData( path, true, null);
            if (data != null)
                str = "path=" + path + "," + new String(data);
            else
                str = "path=" + path + " ,is null";
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("convertStrByPath,return str:{}",str);
        return str;
    }

    public List<String> allNodes() throws KeeperException, InterruptedException {
        String path = "/";
        List<String> clients = zooKeeper.getChildren(path,false);
        forEachList(clients);
        return clients;
    }

    public boolean del(String key) throws KeeperException, InterruptedException {
        zooKeeper.delete("/"+key,-1);
        return true;
    }

    public static void main(String[] args){
        byte[] data = null;
        System.out.println("" + new String(data));//npe
    }

    public boolean setVal(String path, String val) throws KeeperException, InterruptedException {
        path = "/"+path;
        zooKeeper.exists(path,true);//register watch

        Stat stat = zooKeeper.setData(path, val.getBytes(), -1);
        if (stat == null)
            return false;
        System.out.println(new Gson().toJson(stat));
        return true;
    }

    public String getDataByKey(String key) {
        String path = "/" + key;
        return convertStrByPath(path);
    }
}
