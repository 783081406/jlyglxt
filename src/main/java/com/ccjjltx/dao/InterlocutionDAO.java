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

    /**
     * 重选，批量更新，见所有isSelect都改为0
     */
    public void reelectInformation() {
        Session session = factory.getCurrentSession();
        List<Interlocution> list = getAllInformation(0, getAllInformationNumber());
        int i = 0;
        for (Interlocution il : list) {
            il.setIsSelect(0);
            session.update(il);
            if (i % 10 == 0) {
                session.flush();
                session.clear();
            }
            i++;
        }
    }

    /**
     * 根据主键得到实例化
     *
     * @param paid 主键
     * @return 实例化
     */
    public Interlocution getSearchInformation(int paid) {
        Session session = factory.getCurrentSession();
        return (Interlocution) session.get(Interlocution.class, paid);
    }

    /**
     * 批量更新isSelect的值
     *
     * @param qaids 被选中qaid值
     */
    public void updateIsSelect(int[] qaids) {
        Session session = factory.getCurrentSession();
        Interlocution il;
        int i = 0;
        for (int qaid : qaids) {
            il = getSearchInformation(qaid);
            il.setIsSelect(1);
            session.update(il);
            if (i % 8 == 0) {
                session.flush();
                session.clear();
            }
            i++;
        }
    }

    /**
     * 增加新数据
     *
     * @param il Interlocution类的实例化
     */
    public void addInformation(Interlocution il) {
        Session session = factory.getCurrentSession();
        session.save(il);
    }

    /**
     * 更新数据
     *
     * @param il 需要修改的数据的实例化
     */
    public void updateInformation(Interlocution il) {
        Session session = factory.getCurrentSession();
        session.update(il);
    }
}

