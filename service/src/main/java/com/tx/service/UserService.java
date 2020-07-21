package com.tx.service;

import com.tx.pojo.Bo.UserBO;
import com.tx.pojo.Users;
import org.springframework.stereotype.Service;

/**
 * @Author tx_li
 * @Date 2020/4/19 14:14
 * @Version 1.0
 */
public interface UserService {
     /**
      * 判断用户名是否存在
      */
     public boolean queryUsernameIsExist(String username);

     /**
      * 判断用户名是否存在
      */
     public Users createUser(UserBO userBO);

     /**
      * 检索用户名和密码是否匹配，用于登录
      */
     public Users queryUserForLogin(String username, String password);
}
