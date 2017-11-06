package com.ccjjltx.dao;

import com.ccjjltx.domain.Elder;
import com.ccjjltx.domain.Elderlyinformation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/11/5.
 * 对Elderlyinformation数据的增删查改
 *
 * @author ccj
 * @version 1.0
 */
@Service
@Transactional
public class ElderlyinformationDAO {
    @Resource(name = "sessionFactory")
    private SessionFactory factory;
    @Resource(name = "elderDAO")
    private ElderDAO elderDAO;

    /**
     * 得到所有老人的详细信息缩略版本
     *
     * @param offset 起始的函数
     * @param rows   显示的行数
     * @param ename  查询提交过来的老人的名字
     * @return 得到所有的数据返回List
     */
    public List<Elderlyinformation> getAllInformation(int offset, int rows, String ename) {
        Session session = factory.getCurrentSession();
        //总查询语句
        String hql = "from Elderlyinformation elderlyinformation";
        Query query;
        if (ename != null) {//如果是通过搜索框提交过来的就会带一个ename
            Elder db_elder = elderDAO.getSearchElder(ename);
            //如果为空的话表示没有数据直接返回null
            if (db_elder == null) {
                return null;
            } else {
                hql += " where elder=:elder";
                query = session.createQuery(hql).setParameter("elder", db_elder);
            }
        } else {//并非通过搜索框提交过来的
            query = session.createQuery(hql);
        }
        return (List<Elderlyinformation>) query.setFirstResult(offset).setMaxResults(rows).list();
    }

    /**
     * 得到总条数
     *
     * @param ename 搜索框触发的名字
     * @return 整数，总条数
     */
    public int getAllInformationNumber(String ename) {
        Session session = factory.getCurrentSession();
        String hql = "select count(*) from Elderlyinformation elderlyinformation";
        Query query;
        if (ename != null) {
            Elder db_elder = elderDAO.getSearchElder(ename);
            if (db_elder == null) {//如果为空表示没有此数据，返回0
                return 0;
            } else {
                //表示非空
                hql += " where elder=:elder";
                query = session.createQuery(hql).setParameter("elder", db_elder);
            }
        } else {
            query = session.createQuery(hql);
        }
        long l = (long) query.uniqueResult();
        return (int) l;
    }

    /**
     * 根据主键得到实例化
     *
     * @param eiId 主键
     * @return 实例化
     */
    public Elderlyinformation getSearchInformation(int eiId) {
        Session session = factory.getCurrentSession();
        return (Elderlyinformation) session.get(Elderlyinformation.class, eiId);
    }

    /**
     * 增加老人信息
     *
     * @param ei  Elderlyinformation实例化
     * @param eId 老人缩略表的主键
     */
    public void addInformation(Elderlyinformation ei, int eId) {
        Session session = factory.getCurrentSession();
        ei.setElder(elderDAO.getSearchElder(eId));
        session.save(ei);
    }
}
