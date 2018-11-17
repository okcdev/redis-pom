/*
 * File: UserBasicRedisUtils.java
 * Created By: fengtao.xue@gausscode.com
 * Date: 2018-11-17
 */

package cn.feng.dev.customer1.utils;

import cn.feng.dev.customer1.entity.SysUser;
import cn.feng.dev.redis.utils.BasicRedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/**
 * @author fengtao.xue
 */
@Component
public class UserBasicRedisUtils extends BasicRedisUtils<SysUser> {
    static Logger logger = LoggerFactory.getLogger(UserBasicRedisUtils.class);
}
