package com.woneway.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.woneway.dao.UserDao;
import com.woneway.po.User;
import com.woneway.utils.JDBCUtils;

public class UserDaoImpl implements UserDao {

	@Override
	public void regist(User user) throws Exception {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "insert into user values(?,?,?,?,?,?,?)";
		Object[] params = {user.getUid(),user.getUsername(),user.getPassword(),user.getNickname(),user.getEmail(),
				user.getState(),user.getCode()};
		queryRunner.update(sql,params);
	}

	@Override
	public User findByCode(String code) throws Exception {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user where code=?";
		return queryRunner.query(sql, new BeanHandler<User>(User.class),code);
	}

	@Override
	public void update(User user) throws Exception {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "update user set username=?,password=?,nickname=?,email=?,state=?,code=? where uid=?";
		Object[] params = {user.getUsername(),user.getPassword(),user.getNickname(),user.getEmail(),
				user.getState(),user.getCode(),user.getUid()}; 
		queryRunner.update(sql,params);
	}

}
