package com.xiaochen.middleware.controller;

import com.alibaba.fastjson.JSONObject;
import com.xiaochen.middleware.common.ResultT;
import com.xiaochen.middleware.dao.UserInfoDao;
import com.xiaochen.middleware.entity.UserInfo;
import com.xiaochen.middleware.enums.Stats;
import com.xiaochen.middleware.util.RedisUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserInfoController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value="查找用户", notes="根据userId查找用户")
    @GetMapping("/info")
    public ResultT getInfo(@RequestParam(name = "userId",required = true) Integer id){

        UserInfo info = redisUtil.getObj(id.toString());
        logger.info("info:{}",JSONObject.toJSONString(info));

        if (info != null)
            logger.info("find from cache.userInfo:{}", JSONObject.toJSONString(info));
        else {
            logger.info("no find from cache.userId:{}",id);
            info = userInfoDao.findById(id);
            if (info != null){
                redisUtil.setObj(id.toString(),info);
            }
        }

        if (info == null)
            return ResultT.custom(Stats.EMPTY_DATA);

        logger.info("info:{}",info.toString());
        return new ResultT().success(info);
    }

    @ApiOperation(value="查找用户", notes="根据id查找用户")
    @GetMapping("/info/{id}")
    public ResultT getById(@PathVariable Integer id){
        UserInfo info = userInfoDao.findById(id);
        if (info == null){
            return ResultT.custom(Stats.EMPTY_DATA);
        }
        logger.info("info:{}",info.toString());
        return new ResultT().success(info);
    }

    @GetMapping("/getAll")
    public ResultT getById(){
        List<UserInfo> info = userInfoDao.findAll();
        if (info == null){
            return ResultT.custom(Stats.EMPTY_DATA);
        }
        logger.info("info:{}",info.toString());
        return new ResultT().success(info);
    }
}
