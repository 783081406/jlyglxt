package com.ccjjltx.dao;

import com.ccjjltx.domain.Serviceitems;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/11/22.
 * Serviceitems服务项目相关增删查改操作
 *
 * @author ccj
 * @version 1.0
 */
@Service
@Transactional
public class ServiceitemsDAO {
    @Resource(name = "sessionFactory")
    private SessionFactory factory;

    /**
     * 得到全部的信息
     *
     * @param offset 起始行数
     * @param rows   返回行数
     * @return List集合数据
     */
    public List<Serviceitems> getAllInformation(int offset, int rows) {
        Session session = factory.getCurrentSession();
        String hql = "from Serviceitems serviceitems order by sid desc";
        Query query = session.createQuery(hql).setFirstResult(offset).setMaxResults(rows);
        return (List<Serviceitems>) query.list();
    }

    /**
     * 得到总数
     *
     * @return 整数
     */
    public int getAllInformationNumber() {
        Session session = factory.getCurrentSession();
        String hql = "select count(*) from Serviceitems serviceitems";
        long l = (long) session.createQuery(hql).uniqueResult();
        return (int) l;
    }


}
