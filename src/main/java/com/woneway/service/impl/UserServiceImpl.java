package com.woneway.service.impl;

import com.woneway.dao.UserDao;
import com.woneway.dao.impl.UserDaoImpl;
import com.woneway.po.User;
import com.woneway.service.UserService;
import com.woneway.utils.MailUtil;

public class UserServiceImpl implements UserService {

	@Override
	//业务层用户注册
	public void regist(User user) throws Exception{
		//将数据存入到数据库
		UserDao userDao = new UserDaoImpl();
		userDao.regist(user);
		MailUtil.send_mail(user.getEmail(), user.getCode());
	}

	@Override
	public User findByCode(String code)  throws Exception{
		UserDao userDao = new UserDaoImpl();
		return userDao.findByCode(code);
	}

	@Override
	public void update(User user) throws Exception{
		UserDao userDao = new UserDaoImpl();	
		userDao.update(user);
	}
}
