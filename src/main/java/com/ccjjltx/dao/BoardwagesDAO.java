package com.ccjjltx.dao;

import com.ccjjltx.domain.Boardwages;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/11/24.
 * 对伙食费数据的相关操作
 */
@Service
@Transactional
public class BoardwagesDAO {
    @Resource(name = "sessionFactory")
    private SessionFactory factory;

    /**
     * 获得所有的数据
     *
     * @return list集合
     */
    public List<Boardwages> getAllInformation() {
        Session session = factory.getCurrentSession();
        String hql = "from Boardwages boardwages";
        Query query = session.createQuery(hql);
        return (List<Boardwages>) query.list();
    }


}
