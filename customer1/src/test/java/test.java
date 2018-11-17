/*
 * File: test.java
 * Created By: fengtao.xue@gausscode.com
 * Date: 2018-11-17
 */

import cn.feng.dev.customer1.entity.SysUser;
import cn.feng.dev.customer1.service.UserService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;


/**
 * @author fengtao.xue
 */
public class test {
    static Logger logger = LoggerFactory.getLogger(test.class);

    @Autowired
    UserService userService;

    @Test
    public void setUser(){
        logger.info("sysUser:{}", userService.set("1","aaaa", "12323"));
    }
}
