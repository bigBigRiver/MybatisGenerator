package com.boot.mybatis.dao;

import com.boot.mybatis.po.User;
import com.boot.mybatis.po.UserExample;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author river
 * 2020/2/10
 */
@SpringBootTest
class UserMapperTest {

    @Resource
    UserMapper userMapper;

    @Test
    void countByExample() {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserNameLike("river%");
        long count = userMapper.countByExample(userExample);
        assertNotEquals(count, 0);
    }

    @Test
    void deleteByExample() {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserNameEqualTo("river4");
        int influenceLine = userMapper.deleteByExample(userExample);
        assertNotEquals(influenceLine, 0);
    }

    @Test
    void deleteByPrimaryKey() {
        int influenceLineNum = userMapper.deleteByPrimaryKey(4);
        assertNotEquals(influenceLineNum, 0);
    }

    @Test
    void insert() {
        User user = new User();
        user.setUserName("river6");
        user.setPassword("121212");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        int influenceLineNum = userMapper.insert(user);//不能为null的值，必须设置值，数据库默认值不起作用
        assertNotEquals(influenceLineNum, 0);
    }

    @Test
    void insertSelective() {
        User user = new User();
        user.setUserName("river66");
        user.setPassword("123123");
        int influenceLineNum = userMapper.insertSelective(user);//为null值的字段，数据库会该字段设为默认值
        assertNotEquals(influenceLineNum, 0);
    }

    @Test
    void selectByExample() {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserNameLike("river%");
        List<User> users = userMapper.selectByExample(userExample);
        assertNotEquals(users.size(),0);
    }

    @Test
    void selectByPrimaryKey() {
        User user = userMapper.selectByPrimaryKey(1);
        assertEquals(user.getId(),1);
    }

    @Test
    void updateByExampleSelective() {
        User user = new User();
        user.setPassword("666");
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserNameEqualTo("river66");
        int influenceLineNum = userMapper.updateByExampleSelective(user, userExample);//选择性更新，将river66的记录对应的password更新为666
        assertNotEquals(influenceLineNum, 0);
    }

    @Test
    void updateByExample() {
        User user = userMapper.selectByPrimaryKey(1);
        user.setPassword("666666");
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andIdEqualTo(user.getId());
        int influenceLineNum = userMapper.updateByExample(user, userExample);//user中的所有信息所有更新到数据库，包括值为null的字段
        assertNotEquals(influenceLineNum, 0);
    }

    @Test
    void updateByPrimaryKeySelective() {
        User user = new User();
        user.setId(1);
        user.setPassword("168168");
        int influenceLineNum = userMapper.updateByPrimaryKeySelective(user);//根据主键选择性更新，null值字段不会更新到数据库
        assertNotEquals(influenceLineNum, 0);
    }

    @Test
    void updateByPrimaryKey() {
        User user = userMapper.selectByPrimaryKey(1);
        user.setPassword("168666");
        int influenceLineNum = userMapper.updateByPrimaryKey(user);//根据主键选择性更新，null值字段不会更新到数据库
        assertNotEquals(influenceLineNum, 0);
    }
}