package com.boot.mybatis.cache;

import com.boot.mybatis.dao.UserMapper;
import com.boot.mybatis.po.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


/**
 * @author river
 * 2020/2/11
 */
@SpringBootTest
class MybatisCacheTest {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Test
    void theSameSqlSession() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //查询结果会缓存起来，如果不希望缓存这个查询结果，可以在<select>中加上flushCache="true"，或者任何的update, insert, delete语句都会清空此缓存。
        User user1 = userMapper.selectByPrimaryKey(1);
        //下面不会查询数据库，第一次查询的结果缓存在sqlSession一级缓存中
        User user2 = userMapper.selectByPrimaryKey(1);
        assertEquals(user1, user2);//同一个User对象，地址相同
    }

    @Test
    void notTheSameSqlSession(){
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        //查询结果会缓存到sqlSession1中
        User user1 = userMapper1.selectByPrimaryKey(1);
        sqlSession1.commit();//一定要提交，不然不会进入二级缓存
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        //sqlSession2中没有缓存此查询，所以会查询数据库（没有配置二级缓存的时候）
        User user2 = userMapper2.selectByPrimaryKey(1);
        assertNotEquals(user1, user2);
//        assertEquals(user1, user2);//如果配置了readOnly="true"，则获取到的是同一个User对象，地址相同
    }


}
