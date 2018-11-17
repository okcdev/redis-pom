/*
 * File: Customer2.java
 * Created By: fengtao.xue@gausscode.com
 * Date: 2018-11-17
 */

package cn.feng.dev.customer2.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author fengtao.xue
 */
public class Customer2 {
    static Logger logger = LoggerFactory.getLogger(Customer2.class);

    private String key;

    private String id;

    private String score;

    public Customer2() {
    }

    public Customer2(String key, String id, String score) {
        this.key = key;
        this.id = id;
        this.score = score;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
