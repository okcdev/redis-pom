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

import java.util.ArrayList;
import java.util.List;


/**
 * @author fengtao.xue
 */
@Service
public class Customer2Service {
    static Logger logger = LoggerFactory.getLogger(Customer2Service.class);

    @Autowired
    Customer2BasicRedisService customer2BasicRedisUtils;

    public Integer setCustomer2(String key){
        //customer2BasicRedisUtils.setValueWithExpire(key, customer2, 180);
        Customer2 customer20 = new Customer2();
        customer20.setId("20");
        customer20.setScore("35");
        Customer2 customer21 = new Customer2();
        customer21.setId("21");
        customer21.setScore("54");
        List<Customer2> list = new ArrayList<>();
        list.add(customer20);
        list.add(customer21);
        //customer2BasicRedisUtils.pushList(key, customer20, 180);
        customer2BasicRedisUtils.pushListAll(key, list, 300);
        return 1;
    }

    public List<Customer2> getCustomer2(String key){
        return customer2BasicRedisUtils.getList(key, 0, -1);
    }
}
