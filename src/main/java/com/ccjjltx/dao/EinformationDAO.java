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
 * Created by ccjjltx on 2017/10/19.
 * 对Einformation表的相关操作
 *
 * @author ccj
 * @version 1.0
 */
@Service
@Transactional
public class EinformationDAO {
    @Resource(name = "sessionFactory")
    private SessionFactory factory;
    @Resource(name = "userDAO")
    private UserDAO userDAO;

    /**
     * 得到所有员工数据
     *
     * @param offset   起始的行数
     * @param rows     显示的行数
     * @param name     查询提交过来name数据
     * @param userName 查询提交过来的userName数据
     * @return 得到所有的数据返回List
     */
    public List<Einformation> getAllInformation(int offset, int rows, String name, String userName) {
        Session session = factory.getCurrentSession();
        //总查询语句
        String hql = "from Einformation einformation";
        Query query;
//        User db_user;
        //搜索引擎有提交数据过来触发
        if (name != null && userName != null) {//表示同时有name和userName
/*            db_user = userDAO.searchUser(userName);
            if (db_user == null) {
                //如果用户账户错误直接返回null
                return null;
            } else {
                //搜索框的两个值都有开启精确查询查询
                hql += " where einformation.name=:name and einformation.user=:user and einformation.name!='管理员'";
                query = session.createQuery(hql).setParameter("name", name).setParameter("user", db_user);
            }*/
            hql += " where einformation.name like :name and einformation.user.userName like :userName and einformation.name!='管理员' order by einformation.user.id desc";
            query = session.createQuery(hql).setParameter("name", "%" + name + "%").setParameter("userName", "%" + userName + "%");
        } else {
            //进入该判断表示搜索框只填入一个或者什么都没填
            if (name != null) {
                //搜索框的name有值开启模糊查询
                hql += " where name like :name and einformation.name!='管理员' order by einformation.user.id desc";
                query = session.createQuery(hql).setParameter("name", "%" + name + "%");
            } else if (userName != null) {
                //搜索框的userName值不为null，开启精确查询
/*                db_user = userDAO.searchUser(userName);
                if (db_user == null) {
                    //如果没有该用户账户直接返回0
                    return null;
                } else {
                    //查询不为空开启精确查询
                    hql += " where einformation.user=:user and einformation.name!='管理员'";
                    query = session.createQuery(hql).setParameter("user", db_user);
                }*/
                hql += " where einformation.user.userName like :userName and einformation.name!='管理员' order by einformation.user.id desc";
                query = session.createQuery(hql).setParameter("userName", "%" + userName + "%");
            } else {
                //搜索框什么都没有输入
                query = session.createQuery(hql + " where einformation.name!='管理员' order by einformation.user.id desc");
            }
        }
        query = query.setFirstResult(offset).setMaxResults(rows);
        return (List<Einformation>) query.list();
    }

    /**
     * 得到总条数，全部的或者是特定数据的
     *
     * @param name     姓名
     * @param userName 用户账号
     * @return 总条数
     */
    public int getAllInformationNumber(String name, String userName) {
        Session session = factory.getCurrentSession();
        //得到总条数
        String hql = "select count(*) from Einformation einformation";
//        User db_user;
        Query query;
        if (name != null && userName != null) {
           /* //通过UserDAO查询得到是否有该User账户
            db_user = userDAO.searchUser(userName);
            if (db_user == null) {
                //如果没有该用户账户直接返回0
                return 0;
            } else {
                //如果有该用户账户使用and进行精准查询
                hql += " where einformation.name=:name and einformation.user=:user and einformation.name!='管理员'";
                query = session.createQuery(hql).setParameter("name", name).setParameter("user", db_user);
            }*/
            hql += " where einformation.name like :name and einformation.user.userName like :userName and einformation.name!='管理员'";
            query = session.createQuery(hql).setParameter("name", "%" + name + "%").setParameter("userName", "%" + userName + "%");
        } else {
            //只有name或usrName或什么值都没有的情况下
            if (name != null) {
                //搜索框的name有值开启模糊查询
                hql += " where name like :name and einformation.name!='管理员'";
                query = session.createQuery(hql).setParameter("name", "%" + name + "%");
            } else if (userName != null) {
                /*//搜索框的userName值不为null，开启精确查询
                db_user = userDAO.searchUser(userName);
                if (db_user == null) {
                    //如果没有该用户账户直接返回0
                    return 0;
                } else {
                    //查询不为空开启精确查询
                    hql += " where einformation.user=:user and einformation.name!='管理员'";
                    query = session.createQuery(hql).setParameter("user", db_user);
                }*/
                hql += " where einformation.user.userName like :userName and einformation.name!='管理员'";
                query = session.createQuery(hql).setParameter("userName", "%" + userName + "%");
            } else {
                //搜索框什么都没有输入
                query = session.createQuery(hql + " where einformation.name!='管理员'");
            }
        }
        long l = (long) query.uniqueResult();
        return (int) l;
    }

    /**
     * 增加员工信息
     *
     * @param einformation Einformation实例化
     * @param id           User类的id号码
     */
    public void addInformation(Einformation einformation, int id) {
        Session session = factory.getCurrentSession();
        einformation.setUser(userDAO.searchUser(id));
        session.save(einformation);
    }

    /**
     * 更新操作
     *
     * @param einformation Einformation实例化（数据库有的）
     */
    public void updateInformation(Einformation einformation, int id) {
        Session session = factory.getCurrentSession();
        einformation.setUser(userDAO.searchUser(id));
        session.update(einformation);
    }

    /**
     * 删除操作
     *
     * @param pid id号(主键)
     */
    public void deleteInformation(int pid) {
        Session session = factory.getCurrentSession();
        //根据pid得到Einformation
        //删除操作
        session.delete(getSearchEinformation(pid));
    }

    /**
     * 根据pid得到Einformation
     *
     * @param pid 主键pid
     * @return Einformation实例化
     */
    public Einformation getSearchEinformation(int pid) {
        Session session = factory.getCurrentSession();
        return (Einformation) session.get(Einformation.class, pid);
    }

    /**
     * 根据User实例化得到Einformation实例化
     *
     * @param user User实例化
     * @return Einformation实例化
     */
    public Einformation getSearchEinformation(User user) {
        Session session = factory.getCurrentSession();
        String hql = "from Einformation einformation where einformation.user=:user";
        Query query = session.createQuery(hql).setParameter("user", user);
        return (Einformation) query.uniqueResult();
    }
}
