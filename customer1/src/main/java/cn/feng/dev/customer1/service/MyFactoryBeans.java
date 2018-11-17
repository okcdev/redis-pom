/*
 * File: MyFactoryBeans.java
 * Created By: fengtao.xue@gausscode.com
 * Date: 2018-11-17
 */

package cn.feng.dev.customer1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author fengtao.xue
 */
public class MyFactoryBeans {
    static Logger logger = LoggerFactory.getLogger(MyFactoryBeans.class);

    public String getMyFactoryBean(){
        return "注入MyFactoryBean成功";
    }
}
