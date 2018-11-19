/*
 * File: UserBasicRedisUtils.java
 * Created By: fengtao.xue@gausscode.com
 * Date: 2018-11-17
 */

package cn.feng.dev.customer1.utils;

import cn.feng.dev.customer1.entity.Customer1;
import cn.feng.dev.redis.service.BasicRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @author fengtao.xue
 */
@Component
public class Customer1BasicRedisService extends BasicRedisService<Customer1> {
    static Logger logger = LoggerFactory.getLogger(Customer1BasicRedisService.class);
}
