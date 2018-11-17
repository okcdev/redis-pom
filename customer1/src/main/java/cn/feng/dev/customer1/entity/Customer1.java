/*
 * File: Customer1.java
 * Created By: fengtao.xue@gausscode.com
 * Date: 2018-11-15
 */

package cn.feng.dev.customer1.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;


/**
 * @author fengtao.xue
 */
public class Customer1{
    static Logger logger = LoggerFactory.getLogger(Customer1.class);

    private String userName;
    private String password;

    public Customer1() {
    }

    public Customer1(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
