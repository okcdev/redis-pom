/*
 * File: Customer2Service.java
 * Created By: fengtao.xue@gausscode.com
 * Date: 2018-11-17
 */

package cn.feng.dev.customer2.service;

import cn.feng.dev.customer2.entity.Customer2;
import cn.feng.dev.customer2.utils.Customer2BasicRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author fengtao.xue
 */
@Service
public class Customer2Service {
    static Logger logger = LoggerFactory.getLogger(Customer2Service.class);

    @Autowired
    Customer2BasicRedisService customer2BasicRedisUtils;

    public Customer2 setCustomer2(String key, Customer2 customer2){
        customer2BasicRedisUtils.setValueWithExpire(key, customer2, 180);
        customer2.setKey(key);
        return customer2;
    }

    public Customer2 getCustomer2(String key){
        return customer2BasicRedisUtils.getValue(key);
    }
}
