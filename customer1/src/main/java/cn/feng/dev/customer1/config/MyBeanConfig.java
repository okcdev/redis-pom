/*
 * File: MyBeanConfig.java
 * Created By: fengtao.xue@gausscode.com
 * Date: 2018-11-17
 */

package cn.feng.dev.customer1.config;

import cn.feng.dev.customer1.entity.Customer1;
import cn.feng.dev.customer1.service.MyFactoryBeans;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author fengtao.xue
 */
@Configuration
public class MyBeanConfig {
    static Logger logger = LoggerFactory.getLogger(MyBeanConfig.class);

    @Bean
    public MyFactoryBeans getFactoryBeans(){
        return new MyFactoryBeans();
    }
}
