package com.csy.webmvc.dao.iface;

import com.csy.webmvc.bean.po.User;
import org.springframework.stereotype.Repository;

/**
 * 为规范化dao层，禁止在service层直接调用该层。应该调用daoImpl
 */
@Repository
public interface UserDao {

	int saveUser(User user);

	User getByMobile(String mobile);

}
