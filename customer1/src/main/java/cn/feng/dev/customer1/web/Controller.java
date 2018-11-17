/*
 * File: Controller.java
 * Created By: fengtao.xue@gausscode.com
 * Date: 2018-11-17
 */

package cn.feng.dev.customer1.web;

import cn.feng.dev.customer1.entity.SysUser;
import cn.feng.dev.customer1.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author fengtao.xue
 */
@RestController
public class Controller {
    static Logger logger = LoggerFactory.getLogger(Controller.class);
    @Autowired
    UserService userService;

    @RequestMapping("/set/{key}")
    public SysUser setKey(@PathVariable("key") String key){
        return userService.set(key,"goufen", "dddddd");
    }
}