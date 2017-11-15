package com.ccjjltx.dao;

import com.ccjjltx.domain.Casehistory;
import com.ccjjltx.domain.Ecginformation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/11/8.
 * 对EinformationDAO心电信息表进行增删改查操作
 *
 * @author ccj
 * @version 1.0
 */
@Service
@Transactional
public class EcginformationDAO {
    @Resource(name = "sessionFactory")
    private SessionFactory factory;
    @Resource(name = "casehistoryDAO")
    private CasehistoryDAO casehistoryDAO;

    /**
     * 根据casehistory病历的主键得到相关的心电信息实例化
     *
     * @param chId casehistory主键
     * @return list集合
     */
    public List<Ecginformation> getAllInformation(int chId) {
        Session session = factory.getCurrentSession();
        //得到casehistory实例化
        Casehistory db_casehistory = casehistoryDAO.getSearchInformation(chId);
        String hql = "from Ecginformation ecginformation where casehistory=:casehistory";
       Query query = session.createQuery(hql).setParameter("casehistory", db_casehistory);
        return (List<Ecginformation>) query.list();
    }
}
