package com.ccjjltx.dao;

import com.ccjjltx.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
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
public class UserDAOTest {
    @Resource(name = "userDAO")
    private UserDAO userDAO;

    /**
     * 验证：当错误用户名时候是否返回1，表示用户名错误
     */
    @Test
    @Transactional
    public void testCheckUser1() {
        int iResult = userDAO.checkUser("1234", "1234");//实例化一个User类
        Assert.assertEquals(1, iResult);
    }

    /**
     * 验证：当正确用户名错误密码时候是否返回2，表示密码错误
     */
    @Test
    @Transactional
    public void testCheckUser2() {
        int iResult = userDAO.checkUser("admin", "1234");//实例化一个User类
        Assert.assertEquals(2, iResult);
    }

    /**
     * 验证：当正确用户名和正确密码时候是否返回3，表验证通过
     */
    @Test
    @Transactional
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
    @Transactional
    public void testGetAllUser1() {
        int size = userDAO.getAllUserNumber(null);
        List<User> result = userDAO.getAllUser(0, 100, null);
        Assert.assertEquals(size, result.size());
    }

    /**
     * 验证：是否能返回查询的数据
     */
    @Test
    @Transactional
    public void testGetAllUser2() {
        int size = userDAO.getAllUserNumber("admi");
        List<User> result = userDAO.getAllUser(0, 100, "admi");
        Assert.assertEquals(size, result.size());
    }

    /**
     * 验证：是否返回正确的总条数
     */
    @Test
    @Transactional
    public void testGetAllUserNumber1() {
        int size = userDAO.getAllUser(0, 100, null).size();
        int total = userDAO.getAllUserNumber(null);//得到user表的总条数
        Assert.assertEquals(size, total);
    }

    /**
     * 验证：是否返回正确的总条数
     */
    @Test
    @Transactional
    public void testGetAllUserNumber2() {
        int size = userDAO.getAllUser(0, 100, "admi").size();
        int total = userDAO.getAllUserNumber("admi");
        Assert.assertEquals(1, total);
    }

    /**
     * 验证：当uType非1或2时候是否返回false
     */
    @Test
    @Transactional
    public void testAddUser1() {
        int result = userDAO.addUser("1234", "134", 3);
        Assert.assertEquals(1, result);
    }

    /**
     * 验证：当有相同用户名时候是否返回false
     */
    @Test
    @Transactional
    public void testAddUser2() {
        int result = userDAO.addUser("ccj2", "ccj2", 2);
        Assert.assertEquals(2, result);
    }

    /**
     * 验证：当唯一用户名，密码和正确utype时，数据是否插入成功
     */
    @Test
    @Transactional
    @Rollback
    public void testAddUser3() {
        int result = userDAO.addUser("ccj15", "ccj15", 2);
        Assert.assertEquals(3, result);
    }

    /**
     * 验证：全部信息错误是否是否返回false
     */
    @Test
    @Transactional
    @Rollback
    public void testUpdateUser1() {
        User user = userDAO.searchUser(1);
        user.setPassword("123");
        userDAO.updateUser(user);
        Assert.assertEquals("123", userDAO.searchUser(1).getPassword());
    }

    /**
     * 验证：删除操作
     */
    @Test
    @Transactional
    @Rollback
    public void testDeleteUser() {
        int result1 = userDAO.getAllUserNumber(null);//得到删除前的总数
        userDAO.deleteUser(1);
        int result2 = userDAO.getAllUserNumber(null);//得到删除后的总数
        Assert.assertEquals(result1 - 1, result2);
    }

    /**
     * 验证：输入合法用户名是否返回正确的User实例化
     */
    @Test
    @Transactional
    public void testSearchUser() {
        User db_User = userDAO.searchUser("admin");
        //admin数据的id号是1
        Assert.assertEquals(1, db_User.getId());
    }

    /**
     * 验证重载的getAllUser方法是否能返回全部数据
     */
    @Test
    @Transactional
    public void testGetAllUser3() {
        int size = userDAO.getAllUserNumber(null);
        Assert.assertEquals(size, userDAO.getAllUser().size());
    }


    /**
     * 验证重载的searchUser是否能根据用户得到正确的User
     */
    @Test
    @Transactional
    public void testSearchUser3() {
        User db_user = userDAO.searchUser(1);
        Assert.assertEquals(1, db_user.getId());
    }
}
