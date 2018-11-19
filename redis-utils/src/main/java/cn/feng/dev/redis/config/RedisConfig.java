/*
 * File: RedisConfig3.java
 * Created By: fengtao.xue@gausscode.com
 * Date: 2018-11-17
 */

package cn.feng.dev.redis.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * @author fengtao.xue
 */
@Configuration
public class RedisConfig {
    static Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    /**
     * redisTemplate 默认使用jdkSerializeable进行序列化,存储二进制字节码
     * @param jedisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        //RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        //这里使用Jackson2JsonRedisSerializer 替代默认的序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        //xml序列化,速度慢,占用空间大
//        OxmSerializer oxmSerializer = new OxmSerializer();
        //jdk序列化,速度快,但是暂用空间大,并且序列化对象必须实现java.io.Serializable接口,redis默认采用jdk序列化
//        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());//设置value的序列化类型为json类型
        redisTemplate.setKeySerializer(new StringRedisSerializer());//设置key的序列化类型为String类型,key类型只能为String类型
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 开启事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
}
