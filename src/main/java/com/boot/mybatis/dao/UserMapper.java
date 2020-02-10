package com.boot.mybatis.dao;

import com.boot.mybatis.po.User;
import com.boot.mybatis.po.UserExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    /**
     * 根据example查询符合条件的记录数
     */
    long countByExample(UserExample example);

    /**
     * 根据example删除符合条件的记录
     */
    int deleteByExample(UserExample example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入
     */
    int insert(User record);

    /**
     * 选择性插入
     */
    int insertSelective(User record);

    /**
     * 根据example查询符合条件的记录
     */
    List<User> selectByExample(UserExample example);

    /**
     * 根据主键查询记录
     */
    User selectByPrimaryKey(Integer id);

    /**
     * 根据example选择性更新记录
     */
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    /**
     * 根据example更新记录
     */
    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    /**
     * 根据主键选择性更新记录
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(User record);
}