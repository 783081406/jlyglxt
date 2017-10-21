package com.ccjjltx.dao;

import com.ccjjltx.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
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
public class UserDAOTest {
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
    public void testGetAllUser1() {
        //得到一个list，该list只有一条数据，首条数据，id为1，是admin的账户
        List<User> list = userDAO.getAllUser(0, 1, null);
        int id = 0;
        for (User user : list) {
            //如果正确返回，这个时候id变成1
            id = user.getId();
        }
        Assert.assertEquals(1, id);
    }

    /**
     * 验证：是否能返回查询的数据
     */
    @Test
    public void testGetAllUser2() {
        //得到一个list，该list只有一条数据，首条数据，id为1，是admin的账户
        List<User> list = userDAO.getAllUser(0, 1, "admi");
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
    public void testGetAllUserNumber1() {
        //得到user表的总条数
        int total = userDAO.getAllUserNumber(null);
        Assert.assertEquals(15, total);
    }

    /**
     * 验证：是否返回正确的总条数
     */
    @Test
    public void testGetAllUserNumber2() {
        //得到user表的总条数
        int total = userDAO.getAllUserNumber("admi");
        Assert.assertEquals(1, total);
    }

    /**
     * 验证：当uType非1或2时候是否返回false
     */
    @Test
    public void testAddUser1() {
        int result = userDAO.addUser("1234", "134", 3);
        Assert.assertEquals(1, result);
    }

    /**
     * 验证：当有相同用户名时候是否返回false
     */
    @Test
    public void testAddUser2() {
        int result = userDAO.addUser("ccj2", "ccj2", 2);
        Assert.assertEquals(2, result);
    }

    /**
     * 验证：当唯一用户名，密码和正确utype时，数据是否插入成功
     */
    @Test
    //标明此方法需使用事务
    @Transactional
    //标明使用完此方法后事务回滚
    @Rollback(true)
    public void testAddUser3() {
        int result = userDAO.addUser("ccj15", "ccj15", 2);
        Assert.assertEquals(3, result);
    }

    /**
     * 验证：全部信息错误是否是否返回false
     */
    @Test
    //标明此方法需使用事务
    @Transactional
    //标明使用完此方法后事务回滚
    @Rollback(true)
    public void testUpdateUser1() {
        User user = new User();
        user.setId(100);
        user.setUserName("ccj100");
        user.setPassword("ccj100");
        user.setUType(2);
        //预期应该是插入失败，返回false
        boolean result = userDAO.updateUser(user);
        //Assert.assertFalse(result);
    }

    /**
     * 验证：全部信息正确是否是否能更新
     */
    @Test
    //标明此方法需使用事务
    @Transactional
    //标明使用完此方法后事务回滚
    @Rollback(true)
    public void testUpdateUser2() {
        User user = new User();
        user.setId(15);
        user.setUserName("ccj1515");
        user.setPassword("ccj1515");
        user.setUType(2);
        //预期应该是插入成功返回true
        boolean result = userDAO.updateUser(user);
        Assert.assertTrue(result);
    }

    /**
     * 验证：删除操作
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testDeleteUser() {
        //应该返回true
        boolean result = userDAO.deleteUser(15);
        Assert.assertTrue(result);
    }

    /**
     * 验证：输入合法用户名是否返回正确的User实例化
     */
    @Test
    @Transactional
    public void testSearchUser1() {
        User db_User = userDAO.searchUser("admin");
        //admin数据的id号是1
        Assert.assertEquals(1, db_User.getId());
    }

    /**
     * 验证：输入不合法用户名是否返回null
     */
    @Test
    @Transactional
    public void testSearchUser2() {
        User db_User = userDAO.searchUser("123456789");
        //数据库无此用户名，所以应该返回null
        Assert.assertNull(db_User);
    }
}
