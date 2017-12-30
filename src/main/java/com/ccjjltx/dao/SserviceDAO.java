package com.ccjjltx.dao;

import com.ccjjltx.domain.Elder;
import com.ccjjltx.domain.Sservice;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/12/30.
 * 对表Sservice（特殊服务）的相关操作
 *
 * @author ccj
 * @version 1.0
 */
@Service
@Transactional
public class SserviceDAO {
    @Resource(name = "sessionFactory")
    private SessionFactory factory;
    @Resource(name = "elderDAO")
    private ElderDAO elderDAO;

    /**
     * 得到特殊服务的数据
     *
     * @param offset 起始的行数
     * @param rows   显示的行数
     * @param ename  查询提交过来的用户名查询
     * @return 得到所有的数据返回List
     */
    public List<Sservice> getAllInformation(int offset, int rows, String ename) {
        Session session = factory.getCurrentSession();
        //总查询语句
        String hql = "from Sservice Sservice";
        Query query;
        if (ename != null) {//搜索框带过来的数据
            Elder elder;
            elder = elderDAO.getSearchElder(ename);
            //如果为空的话表示没有数据直接返回null
            if (elder == null) {
                return null;
            } else {
                hql += " where elder=:elder";
                query = session.createQuery(hql).setParameter("elder", elder);
            }
        } else {//并非通过搜索框提交过来的
            query = session.createQuery(hql);
        }
        return (List<Sservice>) query.setFirstResult(offset).setMaxResults(rows).list();
    }

    /**
     * 得到总条数
     *
     * @param ename 搜索框触发的名字
     * @return 整数，总条数
     */
    public int getAllInformationNumber(String ename) {
        Session session = factory.getCurrentSession();
        String hql = "select count(*) from Sservice sservice";
        Query query;
        if (ename != null) {
            Elder db_elder;
            db_elder = elderDAO.getSearchElder(ename);
            if (db_elder == null) {//如果为空表示没有此数据，返回0
                return 0;
            } else {//表示非空
                hql += " where elder=:elder";
                query = session.createQuery(hql).setParameter("elder", db_elder);
            }
        } else {
            query = session.createQuery(hql);
        }
        long l = (long) query.uniqueResult();
        return (int) l;
    }

}
