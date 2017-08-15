package dao;

import pojo.EMP;

public interface EMPMapper {
    int deleteByPrimaryKey(Short empno);

    int insert(EMP record);

    int insertSelective(EMP record);

    EMP selectByPrimaryKey(Short empno);

    int updateByPrimaryKeySelective(EMP record);

    int updateByPrimaryKey(EMP record);
}