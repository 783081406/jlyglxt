package com.ccjjltx.dao;

import com.ccjjltx.domain.Einformation;
import com.ccjjltx.domain.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
    @Resource(name = "einformationDAO")
    private EinformationDAO einformationDAO;


    /**
     * 验证用户名密码正确与否
     *
     * @param userName User实例
     * @param password User实例
     * @return 返回1表示用户名错误，2表示密码错误，3表示正确
     */
    public int checkUser(String userName, String password) {
        //得到Session
        User db_user = searchUser(userName);
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
     * @param offset   起始Number
     * @param rows     行数
     * @param userName 获得的userName
     * @return 所有的User
     */
    public List<User> getAllUser(int offset, int rows, String userName) {
        //得到Session
        Session session = factory.getCurrentSession();
        //总查询语句
        String hql = "from User user";
        Query query;
        //如果搜索引擎触发，则userName不为空
        if (userName != null) {
            hql += " where user.userName like :userName order by id desc";
            query = session.createQuery(hql).setParameter("userName", "%" + userName + "%");
        } else {
            query = session.createQuery(hql + " order by id desc");
        }
        query = query.setFirstResult(offset).setMaxResults(rows);
        return (List<User>) query.list();
    }

    /**
     * 得到所有的User
     *
     * @return 返回所有的User
     */
    public List<User> getAllUser() {
        Session session = factory.getCurrentSession();
        String hql = "from User user order by id desc";
        Query query = session.createQuery(hql);
        return (List<User>) query.list();
    }

    /**
     * 得到所有User的总条数
     *
     * @param userName 提交的userName
     * @return 总条数
     */
    public int getAllUserNumber(String userName) {
        //得到Session
        Session session = factory.getCurrentSession();
        //得到总条数
        String hql = "select count(*) from User user";
        Query query;
        if (userName != null) {
            //表示由搜索功能触发
            hql += " where user.userName like :userName";
            query = session.createQuery(hql).setParameter("userName", "%" + userName + "%");
        } else {
            query = session.createQuery(hql);
        }
        long l = (long) query.uniqueResult();
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
        //得到唯一结果，如果结果是null，表示没有相同的用户名，否则表示有相同的用户名返回false
        User db_user = searchUser(userName);
        if (db_user == null) {
            //将得到的数据形成一个新的User实例化
            User user1 = new User();
            user1.setUserName(userName);
            user1.setPassword(password);
            user1.setUType(uType);
            //持久化user1实例
            session.save(user1);
            return 3;
        } else {
            return 2;
        }
    }

    /**
     * 更新信息
     *
     * @param user User类实例化
     */
    public void updateUser(User user) {
        Session session = factory.getCurrentSession();
        session.update(user);
    }

    /**
     * 根据id，得到User，进而删除User
     *
     * @param id User的id号，使用get方法根据主键得到User
     */
    public void deleteUser(int id) {
        Session session = factory.getCurrentSession();
        User db_user = (User) session.get(User.class, id);//根据id得到User
        Einformation db_ef = einformationDAO.getSearchEinformation(db_user);//先得到员工信息
        if (db_ef != null) {
            einformationDAO.deleteInformation(db_ef.getPid());//删除Einformation表中的信息
        }
        session.delete(db_user);//删除操作
    }

    /**
     * 根据用户名得到User
     *
     * @param userName 要查询的用户名
     * @return User实例化
     */
    public User searchUser(String userName) {
        Session session = factory.getCurrentSession();
        String hql = "from User user where userName=:userName";
        Query query = session.createQuery(hql).setParameter("userName", userName);
        return (User) query.uniqueResult();
    }

    /**
     * 根据id得到User
     *
     * @param id 要查询的id号
     * @return User实例化
     */
    public User searchUser(int id) {
        Session session = factory.getCurrentSession();
        return (User) session.get(User.class, id);
    }
}
