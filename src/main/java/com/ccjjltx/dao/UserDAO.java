package com.ccjjltx.dao;

import com.ccjjltx.domain.User;
import org.hibernate.Query;
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
     * @param userName User实例
     * @param password User实例
     * @return 返回1表示用户名错误，2表示密码错误，3表示正确
     */
    public int checkUser(String userName, String password) {
        //得到Session
        Session session = factory.getCurrentSession();
        String hql = "from User user where userName=:userName";
        Query query = session.createQuery(hql).setParameter("userName", userName);
        User db_user = (User) query.uniqueResult();
        if (db_user == null) {
            return 1;
        } else if (!db_user.getPassword().equals(password)) {
            return 2;
        }
        return 3;
    }


}
