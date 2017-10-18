package com.ccjjltx.dao;

import com.ccjjltx.domain.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * 得到所有User数据
     *
     * @param offset 起始Number
     * @param rows   行数
     * @return 所有的User
     */
    public List<User> getAllUser(int offset, int rows) {
        //得到Session
        Session session = factory.getCurrentSession();
        //总查询语句
        String hql = "from User user";
        Query query = session.createQuery(hql).setFirstResult(offset).setMaxResults(rows);
        List<User> list = (List<User>) query.list();
        return list;
    }

    /**
     * 得到所有User的总条数
     *
     * @return 总条数
     */
    public int getAllUserNumber() {
        //得到Session
        Session session = factory.getCurrentSession();
        //得到总条数
        String hql = "select count(*) from User user";
        long l = (long) session.createQuery(hql).uniqueResult();
        return (int) l;
    }

    /**
     * 增加用户
     *
     * @param userName 用户名
     * @param password 密码
     * @param uType    类型，只能是1和2,
     * @return 1表示uType不符合，2表示有相同用户名，3表示插入成功
     */
    public int addUser(String userName, String password, int uType) {
        //如果uType不是1或者2，直接返回插入失败
        if (!((uType == 1) || (uType == 2))) {
            return 1;
        }
        Session session = factory.getCurrentSession();
        String hql = "from User user where userName=:userName";
        Query query = session.createQuery(hql).setParameter("userName", userName);
        //得到唯一结果，如果结果是null，表示没有相同的用户名，否则表示有相同的用户名返回false
        User db_user = (User) query.uniqueResult();
        if (db_user == null) {
            //将得到的数据形成一个新的User实例化
            User user1 = new User();
            user1.setUserName(userName);
            user1.setPassword(password);
            user1.setuType(uType);
            //持久化user1实例
            session.save(user1);
            return 3;
        } else {
            return 2;
        }
    }
}
