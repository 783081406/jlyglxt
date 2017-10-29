package com.ccjjltx.dao;

import com.ccjjltx.domain.Elder;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by ccjjltx on 2017/10/29.
 * 对老人总信息的增删查改操作
 *
 * @author ccj
 * @version 1.0
 */
@Service
@Transactional
public class ElderDAO {
    @Resource(name = "sessionFactory")
    private SessionFactory factory;

    /**
     * 根据ename得到Elder实例化
     *
     * @param ename 老人省略表中的用户名
     * @return Elder的实例化或null
     */
    public Elder getSearchElder(String ename) {
        Session session = factory.getCurrentSession();
        String hql = "from Elder elder where ename=:ename";
        Query query = session.createQuery(hql).setParameter("ename", ename);
        return (Elder) query.uniqueResult();
    }
}
