/*
 * File: UserService.java
 * Created By: fengtao.xue@gausscode.com
 * Date: 2018-11-17
 */

package cn.feng.dev.customer1.service;

import cn.feng.dev.customer1.entity.SysUser;
import cn.feng.dev.customer1.utils.UserBasicRedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author fengtao.xue
 */
@Service
@Transactional(readOnly = true)
public class UserService {
    static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserBasicRedisUtils userBasicRedisUtils;

    @Transactional(readOnly = false)
    public SysUser set(String key, String name, String password){
        SysUser sysUser = new SysUser();
        sysUser.setUserName(name);
        sysUser.setPassword(password);
        userBasicRedisUtils.setValueWithExpire(key, sysUser, 300);
        return sysUser;
    }
}
