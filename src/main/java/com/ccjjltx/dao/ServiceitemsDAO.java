package com.ccjjltx.dao;

import com.ccjjltx.domain.Serviceitems;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/11/22.
 * Serviceitems服务项目相关增删查改操作
 *
 * @author ccj
 * @version 1.0
 */
@Service
@Transactional
public class ServiceitemsDAO {
    @Resource(name = "sessionFactory")
    private SessionFactory factory;

    /**
     * 得到全部的信息
     *
     * @param offset 起始行数
     * @param rows   返回行数
     * @return List集合数据
     */
    public List<Serviceitems> getAllInformation(int offset, int rows) {
        Session session = factory.getCurrentSession();
        String hql = "from Serviceitems serviceitems order by sid desc";
        Query query = session.createQuery(hql).setFirstResult(offset).setMaxResults(rows);
        return (List<Serviceitems>) query.list();
    }

    /**
     * 得到总数
     *
     * @return 整数
     */
    public int getAllInformationNumber() {
        Session session = factory.getCurrentSession();
        String hql = "select count(*) from Serviceitems serviceitems";
        long l = (long) session.createQuery(hql).uniqueResult();
        return (int) l;
    }

    /**
     * 重选，批量更新，将所有isSelect都改为0
     */
    public void reelectInformation() {
        Session session = factory.getCurrentSession();
        List<Serviceitems> list = getAllInformation(0, getAllInformationNumber());
        int i = 0;
        for (Serviceitems si : list) {
            si.setIsSelect(0);
            session.update(si);
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
     * @param sid 主键的值
     * @return 实例化
     */
    public Serviceitems getSearchInformation(int sid) {
        Session session = factory.getCurrentSession();
        return (Serviceitems) session.get(Serviceitems.class, sid);
    }

    /**
     * 批量更新isSelect的值
     *
     * @param sids 需要修改的isSelect的值
     */
    public void updateIsSelect(int[] sids) {
        Session session = factory.getCurrentSession();
        Serviceitems si;
        int i = 0;
        for (int sid : sids) {
            si = getSearchInformation(sid);
            si.setIsSelect(1);
            session.update(si);
            if (i % 8 == 0) {
                session.flush();
                session.clear();
            }
            i++;
        }
    }

    /**
     * 添加数据
     *
     * @param si Serviceitems类的实例化
     */
    public void addInformation(Serviceitems si) {
        Session session = factory.getCurrentSession();
        session.save(si);
    }

    /**
     * 更新数据
     *
     * @param si Serviceitems修改的实例化
     */
    public void updateInformation(Serviceitems si) {
        Session session = factory.getCurrentSession();
        session.update(si);
    }

    /**
     * 删除数据
     *
     * @param sid 主键
     */
    public void removeInformation(int sid) {
        Session session = factory.getCurrentSession();
        session.delete(getSearchInformation(sid));
    }

    /**
     * 得到选中的值返回给前端
     *
     * @return List集合
     */
    public List<Serviceitems> getSelectInformation() {
        Session session = factory.getCurrentSession();
        String hql = "from Serviceitems serviceitems where isSelect=1 order by sid desc";
        Query query = session.createQuery(hql);
        return (List<Serviceitems>) query.list();
    }
}
