package com.csy.webmvc.redis;

import com.csy.webmvc.constant.ExpireUtil;
import com.csy.webmvc.constant.RedisPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisCluster;

@Repository
public class RRoomDao {
	@Autowired
	private JedisCluster cluster;
	private String roomPrefix = RedisPrefix.room_.toString();

	public int getAudNum(int userId) {
		String num = cluster.get(roomPrefix + userId);
		if (num != null) {
			return Integer.parseInt(num);
		}
		return 0;
	}

	public void creRoom(int userId) {
		cluster.setex(roomPrefix + userId, ExpireUtil.WEEK, "0");
	}

	public void delRoom(int userId) {
		cluster.del(roomPrefix + userId);
	}

	public void addAud(int userId) {
		cluster.incr(roomPrefix + userId);
	}

	public void minusAud(int userId) {
		cluster.decr(roomPrefix + userId);
	}
}
