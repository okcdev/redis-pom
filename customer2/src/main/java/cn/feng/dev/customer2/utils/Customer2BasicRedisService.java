/*
 * File: Customer2BasicRedisUtils.java
 * Created By: fengtao.xue@gausscode.com
 * Date: 2018-11-17
 */

package cn.feng.dev.customer2.utils;

import cn.feng.dev.customer2.entity.Customer2;
import cn.feng.dev.redis.service.BasicRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * @author fengtao.xue
 */
@Component
public class Customer2BasicRedisService extends BasicRedisService<Customer2> {
    static Logger logger = LoggerFactory.getLogger(Customer2BasicRedisService.class);
}
