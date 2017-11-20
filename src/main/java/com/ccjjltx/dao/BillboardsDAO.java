package com.ccjjltx.dao;

import com.ccjjltx.domain.Billboards;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/11/20.
 * 对Billboards表的相关操作
 *
 * @author ccj
 * @version 1.0
 */
@Service
@Transactional
public class BillboardsDAO {
    @Resource(name = "sessionFactory")
    private SessionFactory factory;

    /**
     * 得到全部的信息
     *
     * @param offset 起始行数
     * @param rows   返回行数
     * @return List集合数据
     */
    public List<Billboards> getAllInformation(int offset, int rows) {
        Session session = factory.getCurrentSession();
        String hql = "from Billboards billboards order by bid desc";
        Query query = session.createQuery(hql).setFirstResult(offset).setMaxResults(rows);
        return (List<Billboards>) query.list();
    }

    /**
     * 得到总数
     *
     * @return 整数
     */
    public int getAllInformationNumber() {
        Session session = factory.getCurrentSession();
        //得到总条数
        String hql = "select count(*) from Billboards billboards";
        Query query = session.createQuery(hql);
        long l = (long) query.uniqueResult();
        return (int) l;
    }
}
