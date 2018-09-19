package com.xiaochen.middleware.controller;

import com.xiaochen.middleware.common.ResultT;
import com.xiaochen.middleware.config.ZooKeeperConfig;
import com.xiaochen.middleware.enums.Stats;
import com.xiaochen.middleware.zk.ZkConnManager;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.KeeperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/zookeeper")
public class ZooKeeperController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ZooKeeperConfig zooKeeperConfig;

    @ApiOperation(value="查询节点", notes="根据key查找节点")
    @GetMapping("/info")
    public ResultT getInfo(@RequestParam(name = "key",required = true) String key){
        logger.info("getInfo-key:{}",key);
        ZkConnManager manager = getZkConnManager();
        String str = manager.getDataByKey(key);
        manager.closeConn();
        if (StringUtils.isBlank(str)){
            return new ResultT().fail(str);
        }
        return new ResultT().success(str);
    }

    @ApiOperation(value="创建节点组", notes="根据name创建节点组")
    @GetMapping("/createNode")
    public ResultT createNode(@RequestParam(name = "name",required = true) String key){
        logger.info("createNode-key:{}",key);
        String rtnPath = null;
        ZkConnManager manager = null;
        try {
            manager = new ZkConnManager(zooKeeperConfig.getUrl());
            try {
                rtnPath = manager.createGroup(key,0);
                manager.createGroup(key+"_persistent",0);
                manager.createGroup(key+"_ephemeral",1);
                manager.createGroup(key+"_persistent_sequential",2);
                manager.createGroup(key+"_ephemeral_sequential",3);
            } catch (KeeperException e) {
                e.printStackTrace();
            }
            manager.closeConn();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (StringUtils.isBlank(rtnPath)){
            return  new ResultT().fail(key);
        }
        return new ResultT().success(rtnPath);
    }

    @ApiOperation(value="节点列表查询", notes="查找节点列表")
    @GetMapping("/list")
    public ResultT getList(){
        List<String> clients = null;
        try {
            ZkConnManager manager = new ZkConnManager(zooKeeperConfig.getUrl());
            try {
                clients = manager.allNodes();
            } catch (KeeperException e) {
                e.printStackTrace();
            }
            manager.closeConn();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getResultT(clients);
    }

    @ApiOperation(value="查询子节点列表查询", notes="查找子节点列表")
    @GetMapping("/getChildrens")
    public ResultT getChildren(@RequestParam(name = "name",required = true) String key){
        ZkConnManager manager = getZkConnManager();
        List<String> clients = null;
        try {
            clients = manager.getChildren(key);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        manager.closeConn();
        return getResultT(clients);
    }

    private ZkConnManager getZkConnManager() {
        ZkConnManager manager = null;
        try {
            manager = new ZkConnManager(zooKeeperConfig.getUrl());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return manager;
    }

    @ApiOperation(value="节点删除", notes="Node节点删除")
    @GetMapping("/del")
    public ResultT del(@RequestParam(name = "name",required = true) String key){
        ZkConnManager manager = getZkConnManager();
        boolean flag = false;
        try {
            flag = manager.del(key);
            manager.closeConn();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ResultT(flag);
    }
    @ApiOperation(value="节点设置", notes="Node节点设置值")
    @GetMapping("/setVal")
    public ResultT setVal(@RequestParam(name = "path",required = true) String path,
                          @RequestParam(name = "val",required = true) String val){
        ZkConnManager manager = getZkConnManager();
        boolean flag = false;
        try {
            flag = manager.setVal(path,val);
            manager.closeConn();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ResultT(flag);
    }

    private ResultT getResultT(List<String> clients) {
        if (clients == null || clients.isEmpty()){
            return new ResultT().fail(Stats.EMPTY_DATA);
        }
        return new ResultT().success(clients);
    }
}
