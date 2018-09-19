package com.xiaochen.middleware.dao;

import com.xiaochen.middleware.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoDao extends JpaRepository<UserInfo,Integer> {
    UserInfo findById(Integer id);
}
