package com.ccjjltx.dao;

import com.ccjjltx.domain.Casehistory;
import com.ccjjltx.domain.Elder;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/11/8.
 * 对Casehistory病例表的增删查改操作
 *
 * @author ccj
 * @version 1.0
 */
@Service
@Transactional
public class CasehistoryDAO {
    @Resource(name = "sessionFactory")
    private SessionFactory factory;
    @Resource(name = "elderDAO")
    private ElderDAO elderDAO;

    /**
     * 得到全部或特定的信息
     *
     * @param offset 起始数
     * @param rows   行数
     * @param ename  ename值
     * @return List集合
     */
    public List<Casehistory> getAllInformation(int offset, int rows, String ename) {
        Session session = factory.getCurrentSession();
        //总查询语句
        String hql = "from Casehistory casehistory";
        Query query;
        if (ename != null) {//如果是通过搜索框提交过来的就会带一个ename
            Elder elder = elderDAO.getSearchElder(ename);
            //如果为空的话表示没有数据直接返回null
            if (elder == null) {
                return null;
            }
            hql += " where elder=:elder";
            query = session.createQuery(hql).setParameter("elder", elder);
        } else {//并非通过搜索框提交过来的
            query = session.createQuery(hql);
        }
        return (List<Casehistory>) query.setFirstResult(offset).setMaxResults(rows).list();
    }

    /**
     * 得到总条数
     *
     * @param ename 搜索框触发的名字
     * @return 整数，总条数
     */
    public int getAllInformationNumber(String ename) {
        Session session = factory.getCurrentSession();
        String hql = "select count(*) from Casehistory casehistory";
        Query query;
        if (ename != null) {
            Elder elder = elderDAO.getSearchElder(ename);
            if (elder == null) {
                return 0;
            }
            hql += " where elder=:elder";
            query = session.createQuery(hql).setParameter("elder", elder);
        } else {
            query = session.createQuery(hql);
        }
        long l = (long) query.uniqueResult();
        return (int) l;
    }

    /**
     * 根据主键得到实例化
     *
     * @param chId 主键
     * @return 实例化
     */
    public Casehistory getSearchInformation(int chId) {
        Session session = factory.getCurrentSession();
        return (Casehistory) session.get(Casehistory.class, chId);
    }

    /**
     * 更新操作
     *
     * @param ch Casehistory实例化
     */
    public void updateInformation(Casehistory ch) {
        Session session = factory.getCurrentSession();
        session.update(ch);
    }

    /**
     * 根据Elder的主键eId得到数据库中是否已经有该名老人的病历
     *
     * @param eId Elder主键
     * @return true表示已经存在，false表示不存在
     */
    public boolean isElder(int eId) {
        Session session = factory.getCurrentSession();
        //得到Elder实例化
        Elder elder = elderDAO.getSearchElder(eId);
        String hql = "from Casehistory casehistory where elder=:elder";
        Query query = session.createQuery(hql).setParameter("elder", elder);
        Casehistory casehistory = (Casehistory) query.uniqueResult();
        if (casehistory == null) {//表示没有相关的病历
            return false;
        } else {
            return true;
        }
    }

    /**
     * 新增操作
     *
     * @param ch  Casehistory实例化
     * @param eId Elder主键
     * @return false表示插入失败，Elder已经存在，true表示插入成功
     */
    public boolean addInformation(Casehistory ch, int eId) {
        Session session = factory.getCurrentSession();
        //判断是否已经存在该名老人的病历了，有则插入失败
        if (!isElder(eId)) {//表示没有存在相关的病历，插入失败
            Elder elder = elderDAO.getSearchElder(eId);
            ch.setElder(elder);
            //执行增加操作
            session.save(ch);
            return true;
        } else {//表示存在相关的病历，插入失败
            return false;
        }
    }
}
