package com.ccjjltx.dao;

import com.ccjjltx.domain.Elderlyinformation;
import com.ccjjltx.domain.Familyinformation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/11/5.
 * 对Familyinformation表的增删改查
 *
 * @author ccj
 * @version 1.0
 */
@Service
@Transactional
public class FamilyinformationDAO {
    @Resource(name = "sessionFactory")
    private SessionFactory factory;
    @Resource(name = "elderlyinformationDAO")
    private ElderlyinformationDAO elderlyinformationDAO;

    public List<Familyinformation> getAllInformation(int eiId) {
        Session session = factory.getCurrentSession();
        String hql = "from Familyinformation familyinformation where elderlyinformation=:elderlyinformation";
        Elderlyinformation elderlyinformation = elderlyinformationDAO.getSearchInformation(eiId);
        Query query = session.createQuery(hql).setParameter("elderlyinformation", elderlyinformation);
        return (List<Familyinformation>) query.list();
    }

    /**
     * 删除操作
     *
     * @param eiId Elderlyinformation主键
     */
    public void deleteInformation(int eiId) {
        Session session = factory.getCurrentSession();
        List<Familyinformation> list = getAllInformation(eiId);
        //遍历  删除
        for (Familyinformation db_fi : list) {
            session.delete(db_fi);
        }
    }
}
