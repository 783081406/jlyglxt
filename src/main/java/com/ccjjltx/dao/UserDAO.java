package com.ccjjltx.dao;

import com.ccjjltx.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by ccjjltx on 2017/10/13.
 * 对User表的相关操作
 *
 * @author ccj
 * @version 1.0
 */
@Service
@Transactional
public class UserDAO {
    @Resource(name = "sessionFactory")
    private SessionFactory factory;

    /**
     * 验证用户名密码正确与否
     *
     * @param user User实例
     * @return 返回1表示用户名错误，2表示密码错误，3表示正确
     */
    public int checkUser(User user) {
        //得到Session
        Session s = factory.getCurrentSession();
        User db_user = (User) s.get(User.class, user.getUserName());
        if (db_user == null) {
            return 1;
        } else if (!db_user.getPassword().equals(user.getPassword())) {
            return 2;
        }
        return 3;
    }


}
