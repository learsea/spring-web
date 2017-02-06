package com.csy.webmvc.dao;

import com.csy.webmvc.bean.po.User;
import com.csy.webmvc.dao.iface.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl {
	@Autowired
	private UserDao userDao;

	public int saveUser(User user) {
		return userDao.saveUser(user);
	}

	public User getByMobile(String mobile) {
		return userDao.getByMobile(mobile);
	}
}
