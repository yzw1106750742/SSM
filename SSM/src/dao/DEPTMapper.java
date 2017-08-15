package dao;

import pojo.DEPT;

public interface DEPTMapper {
    int deleteByPrimaryKey(Short deptno);

    int insert(DEPT record);

    int insertSelective(DEPT record);

    DEPT selectByPrimaryKey(Short deptno);

    int updateByPrimaryKeySelective(DEPT record);

    int updateByPrimaryKey(DEPT record);
}