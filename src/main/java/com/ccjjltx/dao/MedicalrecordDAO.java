package com.ccjjltx.dao;

import com.ccjjltx.domain.Casehistory;
import com.ccjjltx.domain.Medicalrecord;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/11/8.
 * 对Medicalrecord就医记录表进行增删改查操作
 *
 * @author ccj
 * @version 1.0
 */
@Service
@Transactional
public class MedicalrecordDAO {
    @Resource(name = "sessionFactory")
    private SessionFactory factory;
    @Resource(name = "casehistoryDAO")
    private CasehistoryDAO casehistoryDAO;

    /**
     * 根据casehistory病历的主键得到相关的就医记录实例化
     *
     * @param chId Medicalrecord主键
     * @return list集合
     */
    public List<Medicalrecord> getAllInformation(int chId) {
        Session session = factory.getCurrentSession();
        //得到casehistory实例化
        Casehistory casehistory = casehistoryDAO.getSearchInformation(chId);
        String hql = "from Medicalrecord medicalrecord where casehistory=:casehistory order by mrId desc";
        Query query = session.createQuery(hql).setParameter("casehistory", casehistory);
        return (List<Medicalrecord>) query.list();
    }

    /**
     * 增加就医记录
     *
     * @param mc Medicalrecord实例化
     */
    public void addInformation(Medicalrecord mc) {
        Session session = factory.getCurrentSession();
        session.save(mc);
    }

    /**
     * 根据主键得到实例化
     *
     * @param mrId 主键
     * @return 实例化
     */
    public Medicalrecord getSearchEinformation(int mrId) {
        Session session = factory.getCurrentSession();
        return (Medicalrecord) session.get(Medicalrecord.class, mrId);
    }

    /**
     * 更新操作
     *
     * @param mc 更新的实例化
     */
    public void updateInformation(Medicalrecord mc) {
        Session session = factory.getCurrentSession();
        session.update(mc);
    }

    /**
     * 删除操作
     *
     * @param mrId 主键
     */
    public void deleteInformation(int mrId) {
        Session session = factory.getCurrentSession();
        //删除操作
        session.delete(getSearchEinformation(mrId));
    }
}
