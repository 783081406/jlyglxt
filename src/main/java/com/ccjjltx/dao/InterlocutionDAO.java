package com.ccjjltx.dao;

import com.ccjjltx.domain.Interlocution;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/11/23.
 * 对Interlocution表的相关操作
 *
 * @author ccj
 * @version 1.0
 */
@Service
@Transactional
public class InterlocutionDAO {
    @Resource(name = "sessionFactory")
    private SessionFactory factory;

    /**
     * 得到所有的数据
     *
     * @param offset 起始的行数
     * @param rows   页数
     * @return List数据集合
     */
    public List<Interlocution> getAllInformation(int offset, int rows) {
        Session session = factory.getCurrentSession();
        String hql = "from Interlocution interlocution order by qaid desc";
        Query query = session.createQuery(hql).setFirstResult(offset).setMaxResults(rows);
        return (List<Interlocution>) query.list();
    }

    /**
     * 得到总数
     *
     * @return 整数
     */
    public int getAllInformationNumber() {
        Session session = factory.getCurrentSession();
        String hql = "select count(*) from Interlocution interlocution";
        long l = (long) session.createQuery(hql).uniqueResult();
        return (int) l;
    }
}

