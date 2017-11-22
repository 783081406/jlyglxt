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
}
