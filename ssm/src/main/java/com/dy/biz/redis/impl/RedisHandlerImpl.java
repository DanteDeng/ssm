package com.dy.biz.redis.impl;

import java.io.IOException;
import java.io.NotSerializableException;
import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.dy.biz.redis.RedisHandler;
import com.mchange.v2.ser.SerializableUtils;
@Component("redisHandler")
public class RedisHandlerImpl implements RedisHandler{

	@Autowired
	protected RedisTemplate<Serializable, Serializable> redisTemplate;

	protected Log log = LogFactory.getLog(this.getClass());

	public void setKeyValue(final String key, final Serializable value) {
		redisTemplate.execute(new RedisCallback<Integer>() {
			@Override
			public Integer doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] valueBytes = null;
				try {
					valueBytes = SerializableUtils.toByteArray(value);
				} catch (NotSerializableException e) {
					log.error("RedisUtil save NotSerializableException", e);
				}
				connection.set(key.getBytes(), valueBytes);
				return 1;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public <T> T get(final String key) {
		Object result = redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] keyBytes = key.getBytes();
				if (connection.exists(keyBytes)) {
					byte[] value = connection.get(keyBytes);
					Object object = null;
					try {
						object = SerializableUtils.fromByteArray(value);
					} catch (ClassNotFoundException e) {
						log.error("RedisUtil get ClassNotFoundException", e);
					} catch (IOException e) {
						log.error("RedisUtil get IOException", e);
					}
					return object;
				}
				return null;
			}
		});
		return (T) result;
	}

}