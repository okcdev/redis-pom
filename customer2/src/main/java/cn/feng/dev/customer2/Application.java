/*
 * File: Application.java
 * Created By: fengtao.xue@gausscode.com
 * Date: 2018-11-17
 */

package cn.feng.dev.customer2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @author fengtao.xue
 */
@SpringBootApplication(scanBasePackages ="cn.feng.dev")
public class Application {
    static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
