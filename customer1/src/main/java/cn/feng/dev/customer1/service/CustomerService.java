/*
 * File: UserService.java
 * Created By: fengtao.xue@gausscode.com
 * Date: 2018-11-17
 */

package cn.feng.dev.customer1.service;

import cn.feng.dev.customer1.entity.Customer1;
import cn.feng.dev.customer1.utils.Customer1BasicRedisUtils;
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
public class CustomerService {
    static Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    Customer1BasicRedisUtils customer1BasicRedisUtils;

    @Transactional(readOnly = false)
    public Customer1 set(String key, String name, String password){
        Customer1 sysUser = new Customer1();
        sysUser.setUserName(name);
        sysUser.setPassword(password);
        customer1BasicRedisUtils.setValueWithExpire(key, sysUser, 300);
        return sysUser;
    }
}
