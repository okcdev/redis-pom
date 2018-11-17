/*
 * File: test.java
 * Created By: fengtao.xue@gausscode.com
 * Date: 2018-11-17
 */

import cn.feng.dev.customer1.service.CustomerService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author fengtao.xue
 */
public class test {
    static Logger logger = LoggerFactory.getLogger(test.class);

    @Autowired
    CustomerService userService;

    @Test
    public void setUser(){
        logger.info("sysUser:{}", userService.set("1","aaaa", "12323"));
    }
}
