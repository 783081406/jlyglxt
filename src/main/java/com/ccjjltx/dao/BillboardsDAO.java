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

    /**
     * 重选，批量更新，将所有isSelect都改为Ox00;
     */
    public void reelectInformation() {
        Session session = factory.getCurrentSession();
        List<Billboards> list = getAllInformation(0, getAllInformationNumber());
        int i = 0;
        for (Billboards bb : list) {
            bb.setIsSelect(0);
            session.update(bb);
            if (i % 10 == 0) {
                session.flush();
                session.clear();
            }
            i++;
        }
    }

    /**
     * 根据billboards主键得到实例化
     *
     * @param bid 主键的值
     * @return 实例化
     */
    public Billboards getSearchInformation(int bid) {
        Session session = factory.getCurrentSession();
        return (Billboards) session.get(Billboards.class, bid);
    }

    /**
     * 批量更新isSelect的值
     *
     * @param bids 需要修改的isSelect的值
     */
    public void updateIsSelect(int[] bids) {
        Session session = factory.getCurrentSession();
        Billboards bb;
        int i = 0;
        for (int bid : bids) {
            bb = getSearchInformation(bid);
            bb.setIsSelect(1);
            session.update(bb);
            if (i % 10 == 0) {
                session.flush();
                session.clear();
            }
            i++;
        }
    }
}
