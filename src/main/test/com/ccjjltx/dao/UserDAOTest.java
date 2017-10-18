package com.ccjjltx.dao;

import com.ccjjltx.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/10/16.
 * 测试UserDAO相关的功能
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class UserDAOTest extends AbstractJUnit4SpringContextTests {
    @Resource(name = "userDAO")
    private UserDAO userDAO;

    /**
     * 验证：当错误用户名时候是否返回1，表示用户名错误
     */
    @Test
    public void testCheckUser1() {
        //实例化一个User类
        int iResult = userDAO.checkUser("1234", "1234");
        //断言
        Assert.assertEquals(1, iResult);
    }

    /**
     * 验证：当正确用户名错误密码时候是否返回2，表示密码错误
     */
    @Test
    public void testCheckUser2() {
        //实例化一个User类
        int iResult = userDAO.checkUser("admin", "1234");
        //断言
        Assert.assertEquals(2, iResult);
    }

    /**
     * 验证：当正确用户名和正确密码时候是否返回3，表验证通过
     */
    @Test
    public void testCheckUser3() {
        //实例化一个User类
        int iResult = userDAO.checkUser("admin", "admin");
        //断言
        Assert.assertEquals(3, iResult);
    }

    /**
     * 验证：是否能返回全部的User
     */
    @Test
    public void testGetAllUser() {
        //得到一个list，该list只有一条数据，首条数据，id为1，是admin的账户
        List<User> list = userDAO.getAllUser(0, 1);
        int id = 0;
        for (User user : list) {
            //如果正确返回，这个时候id变成1
            id = user.getId();
        }
        Assert.assertEquals(1, id);
    }

    /**
     * 验证：是否返回正确的总条数
     */
    @Test
    public void testGetAllUserNumber() {
        //得到user表的总条数
        int total = userDAO.getAllUserNumber();
        Assert.assertEquals(1, total);
    }
}
