package service;

import java.util.List;

import org.springframework.stereotype.Repository;

import pojo.UserInfo;

@Repository
public interface IUserService {
	   List<UserInfo> selectUserInfoList();
}
