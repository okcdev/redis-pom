/*
 * File: Controller.java
 * Created By: fengtao.xue@gausscode.com
 * Date: 2018-11-17
 */

package cn.feng.dev.customer2.web;

import cn.feng.dev.customer2.entity.Customer2;
import cn.feng.dev.customer2.service.Customer2Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author fengtao.xue
 */
@RestController
public class Controller {
    static Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    Customer2Service customer2Service;

    @RequestMapping("/set/{key}")
    public Customer2 cacheCustomer2(@PathVariable("key") String key){
        Customer2 customer2 = new Customer2();
        customer2.setId("123");
        customer2.setScore("35");
        return customer2Service.setCustomer2(key, customer2);
    }
}
