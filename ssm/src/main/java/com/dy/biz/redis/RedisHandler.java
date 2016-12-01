package com.dy.biz.redis;

import java.io.Serializable;

public interface RedisHandler {
	public void setKeyValue(final String key, final Serializable value);
	
	public <T> T get(final String key);

}
