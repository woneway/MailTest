package com.woneway.service;

import com.woneway.po.User;

public interface UserService {
	public void regist(User user)throws Exception;

	public User findByCode(String code) throws Exception;

	public void update(User user)throws Exception;
}
