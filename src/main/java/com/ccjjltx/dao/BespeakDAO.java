package com.ccjjltx.dao;

import com.ccjjltx.domain.Bespeak;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/11/22.
 * 对Bespeak表的相关操作
 *
 * @author ccj
 * @version 1.0
 */
@Service
@Transactional
public class BespeakDAO {
    @Resource(name = "sessionFactory")
    private SessionFactory factory;

    /**
     * 得到全部已处理的信息
     *
     * @param offset 起始行数
     * @param rows   返回行数
     * @return List集合数据
     */
    public List<Bespeak> getAllHandleInformation(int offset, int rows) {
        Session session = factory.getCurrentSession();
        String hql = "from Bespeak bespeak where ishandle=1 order by submitDate desc";
        Query query = session.createQuery(hql).setFirstResult(offset).setMaxResults(rows);
        return (List<Bespeak>) query.list();
    }

    /**
     * 得到全部已经处理的信息的总量
     *
     * @return 整数
     */
    public int getAllHandleInformationNumber() {
        Session session = factory.getCurrentSession();
        String hql = "select count(*) from Bespeak bespeak where ishandle=1";
        Query query = session.createQuery(hql);
        long l = (long) query.uniqueResult();
        return (int) l;
    }

    /**
     * 得到全部未处理的信息
     *
     * @param offset 起始行数
     * @param rows   返回行数
     * @return List集合数据
     */
    public List<Bespeak> getAllUnhandleInformation(int offset, int rows) {
        Session session = factory.getCurrentSession();
        String hql = "from Bespeak bespeak where ishandle=0 order by submitDate desc";
        Query query = session.createQuery(hql).setFirstResult(offset).setMaxResults(rows);
        return (List<Bespeak>) query.list();
    }

    /**
     * 得到全部已经处理的信息的总量
     *
     * @return 整数
     */
    public int getAllUnhandleInformationNumber() {
        Session session = factory.getCurrentSession();
        String hql = "select count(*) from Bespeak bespeak where ishandle=0";
        long l = (long) session.createQuery(hql).uniqueResult();
        return (int) l;
    }

    /**
     * 根据主键得到实例化
     *
     * @param bid 主键
     * @return 实例化
     */
    public Bespeak getSearchInformation(int bid) {
        Session session = factory.getCurrentSession();
        return (Bespeak) session.get(Bespeak.class, bid);
    }
}
