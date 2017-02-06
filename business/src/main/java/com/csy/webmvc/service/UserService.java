package com.csy.webmvc.service;

import com.alibaba.fastjson.JSONObject;
import com.csy.webmvc.bean.po.User;
import com.csy.webmvc.bean.vo.SuccessResult;
import com.csy.webmvc.constant.Code;
import com.csy.webmvc.dao.UserDaoImpl;
import com.csy.webmvc.exception.BusinessException;
import com.csy.webmvc.redis.RUserDao;
import com.csy.webmvc.util.SHA1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDaoImpl userDaoImpl;
	@Autowired
	private RUserDao rUserDao;

	public String register(String mobile, String password) {
		User user = new User();
		user.setMobile(mobile);
		user.setPassword(SHA1.INSTANCE.getSHA1(password));
		if (userDaoImpl.saveUser(user) != 1) {
			throw new BusinessException(Code._300, "注册失败");
		}
		String token = rUserDao.set(user.getUserId());
		JSONObject result = SuccessResult.getResult();
		result.put("user", user);
		result.put("token", token);
		return result.toJSONString();
	}

	public String login(String mobile, String password) {
		User repoUser = userDaoImpl.getByMobile(mobile);
		if (repoUser == null) {
			throw new BusinessException(Code._200, "手机号不存在");
		}
		if (!repoUser.getPassword().equals(SHA1.INSTANCE.getSHA1(password))) {
			throw new BusinessException(Code._200, "密码错误");
		}
		String token = rUserDao.set(repoUser.getUserId());
		JSONObject result = SuccessResult.getResult();
		result.put("user", repoUser);
		result.put("token", token);
		return result.toJSONString();
	}
}
