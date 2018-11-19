/*
 * File: BasicRedisUtils.java
 * Created By: fengtao.xue@gausscode.com
 * Date: 2018-11-16
 */

package cn.feng.dev.redis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * @author fengtao.xue
 */
@Component
public class BasicRedisService<T> {
    static Logger logger = LoggerFactory.getLogger(BasicRedisService.class);

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 指定缓存失效时间
     * @param key
     * @param time，时间（秒）
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key 获取失效时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     * 删除缓存
     * @param key
     */
    public void del(String ... key){
        if(key!=null&&key.length>0){
            if(key.length==1){
                redisTemplate.delete(key[0]);
            }else{
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 判断key是否存在
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key){
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 放入普通缓存
     * @param key
     * @param value
     * @return
     */
    public boolean setValue(String key, T value){
        try {
            redisTemplate.boundValueOps(key).set(value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 放入普通缓存，并设置失效时间
     * @param key
     * @param value
     * @param time
     */
    public  boolean setValueWithExpire(String key, T value, long time){
        try {
            if(time > 0){
                redisTemplate.boundValueOps(key).set(value, time, TimeUnit.SECONDS);
            }else{
                setValue(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取普通缓存
     * @param key
     * @return
     */
    public T getValue(String key){
        return key == null ? null: (T)redisTemplate.boundValueOps(key).get();
    }

    /**
     * 放入hashValue
     * @param key
     * @param map
     */
    public boolean putHashValue(String key, Map<String, T> map){
        try {
            redisTemplate.boundHashOps(key).putAll(map);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 放入hashValue，并设置失效时间
     * @param key
     * @param map
     * @param time
     * @return
     */
    public boolean putHashValue(String key, Map<String, T> map, long time){
        try {
            redisTemplate.boundHashOps(key).putAll(map);
            if (time > 0){
                expire(key, time);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 放入hashValue
     * @param key
     * @param mKey
     * @param mValue
     * @return
     */
    public boolean putHashValue(String key, String mKey, T mValue){
        try {
            redisTemplate.boundHashOps(key).put(mKey, mValue);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 放入hashValue，并且设置失效时间
     * @param key
     * @param mKey
     * @param mValue
     * @param time
     * @return
     */
    public boolean putHashValueWithExpire(String key, String mKey, T mValue, long time){
        try {
            redisTemplate.boundHashOps(key).put(mKey, mValue);
            if (time > 0){
                expire(key, time);
            }
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取hashValue缓存
     * @param key
     * @param mKey
     * @return
     */
    public T getHashValue(String key, String mKey){
        return key == null ? null : (T)redisTemplate.boundHashOps(key).get(mKey);
    }

    /**
     * 获取hashKey对应的所有键值
     * @param key
     * @return
     */
    public Map<Object, Object> getHashMap(String key){
        return redisTemplate.boundHashOps(key).entries();
    }

    /**
     * 删除HashValue中的值
     * @param key
     * @param mapKey
     */
    public void delHashValue(String key, Object... mapKey){
        redisTemplate.boundHashOps(key).delete(mapKey);
        if(redisTemplate.boundHashOps(key).keys().size() == 0){
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断hashValue是否含有该项的值
     * @param key
     * @param mapKey
     * @return
     */
    public boolean hasHashValue(String key, String mapKey){
        return redisTemplate.boundHashOps(key).hasKey(mapKey);
    }

    /**
     * 获取缓存的Map中的所有mapKey值
     * @param key 缓存数据的key
     * @return 缓存的Map中的所有mapKey
     */
    public Set<String> getHashKey(String key){
        return redisTemplate.boundHashOps(key).keys();
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     * @param key 键
     * @param mKey 项
     * @param by 要增加几(大于0)
     * @return
     */
    public double hashIncr(String key, String mKey,double by){
        return redisTemplate.boundHashOps(key).increment(mKey, by);
    }

    /**
     * hash递减
     * @param key 键
     * @param mKey 项
     * @param by 要减少记(小于0)
     * @return
     */
    public double hashDecr(String key, String mKey,double by){
        return redisTemplate.boundHashOps(key).increment(mKey, by);
    }

    /**
     * 添加数据到某个Set类型的缓存中
     * @param key 缓存数据的key
     * @param value 要缓存的数据
     */
    public boolean addSetValue(String key, T... value){
        try {
            redisTemplate.boundSetOps(key).add(value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将set数据放入缓存
     * @param key 键
     * @param time 时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long addSetValueWithExpire(String key, long time, T...values) {
        try {
            Long count = redisTemplate.boundSetOps(key).add(values);
            if(time>0) expire(key, time);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取set缓存的长度
     * @param key 键
     * @return
     */
    public long getSetSize(String key){
        try {
            return redisTemplate.boundSetOps(key).size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 判断缓存中是否有相应的数据
     * @param key 缓存数据的key
     * @param value 要判断的value
     */
    public boolean hasSetValue(String key, T value){
        return redisTemplate.boundSetOps(key).isMember(value);
    }

    /**
     * 删除缓存中Set中的数据
     * @param key 对应的key
     * @param value 要删除的数据
     */
    public void removeSetValue(String key, T... value){
        redisTemplate.boundSetOps(key).remove(value);
        if(redisTemplate.boundSetOps(key).size() == 0){
            redisTemplate.delete(key);
        }
    }

    /**
     * 获取所有的Set缓存数据
     * @param key 缓存的key
     * @return 缓存的所有set
     */
    public Set<T> getAllSetValues(String key){
        return redisTemplate.boundSetOps(key).members();
    }


    /**
     * 获取list缓存的内容
     * @param key 键
     * @param start 开始
     * @param end 结束  0 到 -1代表所有值
     * @return
     */
    public List<Object> getList(String key, long start, long end){
        try {
            return redisTemplate.boundListOps(key).range(start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取list缓存的长度
     * @param key 键
     * @return
     */
    public long getListSize(String key){
        try {
            return redisTemplate.boundListOps(key).size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     * @param key 键
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object getListByIndex(String key, long index){
        try {
            return redisTemplate.boundListOps(key).index(index);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @return
     */
    public boolean pushList(String key, Object value) {
        try {
            redisTemplate.boundListOps(key).rightPush(value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return
     */
    public boolean pushList(String key, Object value, long time) {
        try {
            redisTemplate.boundListOps(key).rightPush(value);
            if (time > 0){
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @return
     */
    public boolean pushList(String key, List<Object> value) {
        try {
            redisTemplate.boundListOps(key).rightPushAll(value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return
     */
    public boolean pushList(String key, List<Object> value, long time) {
        try {
            redisTemplate.boundListOps(key).rightPushAll(value);
            if (time > 0){
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     * @param key 键
     * @param index 索引
     * @param value 值
     * @return
     */
    public boolean updateListByIndex(String key, long index,Object value) {
        try {
            redisTemplate.boundListOps(key).set(index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除N个值为value
     * @param key 键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long removeList(String key,long count,Object value) {
        try {
            Long remove = redisTemplate.boundListOps(key).remove(count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}