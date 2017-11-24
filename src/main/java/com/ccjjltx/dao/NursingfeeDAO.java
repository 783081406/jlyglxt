package com.ccjjltx.dao;

import com.ccjjltx.domain.Nursingfee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/11/25.
 * 护理费相关操作
 *
 * @author ccj
 * @version 1.0
 */
@Service
@Transactional
public class NursingfeeDAO {
    @Resource(name = "sessionFactory")
    private SessionFactory factory;

    /**
     * 得到所有的数据
     *
     * @return list集合
     */
    public List<Nursingfee> getAllInformation() {
        Session session = factory.getCurrentSession();
        String hql = "from Nursingfee nursingfee";
        Query query = session.createQuery(hql);
        return (List<Nursingfee>) query.list();
    }

    /**
     * 根据主键得到实例化
     *
     * @param nid 主键
     * @return 实例化
     */
    public Nursingfee getSearchInformation(int nid) {
        Session session = factory.getCurrentSession();
        return (Nursingfee) session.get(Nursingfee.class, nid);
    }

}
