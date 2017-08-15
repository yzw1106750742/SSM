package dao;

import java.math.BigDecimal;
import java.util.List;


import pojo.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(BigDecimal userid);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(BigDecimal userid);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
   List<UserInfo> selectUserInfoList();
}