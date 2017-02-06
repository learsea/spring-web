package com.csy.webmvc.redis;

import com.csy.webmvc.constant.ExpireUtil;
import com.csy.webmvc.constant.RedisPrefix;
import com.csy.webmvc.util.SHA1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisCluster;

@Repository
public class RUserDao {
	@Autowired
	private JedisCluster cluster;
	private String sessionPrefix = RedisPrefix.session_.toString();

	public boolean check(String userId, String token) {
		if (userId == null || token == null) {
			return false;
		}
		String repoToken = cluster.get(sessionPrefix + userId);
		if (repoToken != null && repoToken.equals(token)) {
			expire(userId);
			return true;
		}
		return false;
	}

	public String set(int userId) {
		String token = SHA1.INSTANCE.getSHA1(userId + "" + System.currentTimeMillis() / 1000);
		cluster.setex(sessionPrefix + userId, ExpireUtil.WEEK, token);
		return token;
	}

	public void expire(String userId) {
		cluster.expire(userId, ExpireUtil.WEEK);
	}
}
