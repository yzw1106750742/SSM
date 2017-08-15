package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojo.UserInfo;

import dao.UserInfoMapper;

@Service
public class UserService implements IUserService{
	@Autowired
	private UserInfoMapper userDao;

	
	public List<UserInfo> selectUserInfoList() {
	
		return userDao.selectUserInfoList();
	}
}
