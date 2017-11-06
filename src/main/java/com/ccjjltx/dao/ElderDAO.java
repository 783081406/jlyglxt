package com.ccjjltx.dao;

import com.ccjjltx.domain.Elder;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/10/29.
 * 对老人总信息的增删查改操作
 *
 * @author ccj
 * @version 1.0
 */
@Service
@Transactional
public class ElderDAO {
    @Resource(name = "sessionFactory")
    private SessionFactory factory;

    /**
     * 得到所有的Elder
     *
     * @return 返回所有的Elder信息
     */
    public List<Elder> getAllElder() {
        Session session = factory.getCurrentSession();
        String hql = "from Elder elder";
        Query query = session.createQuery(hql);
        return (List<Elder>) query.list();
    }

    /**
     * 根据ename得到Elder实例化
     *
     * @param ename 老人省略表中的用户名
     * @return Elder的实例化或null
     */
    public Elder getSearchElder(String ename) {
        Session session = factory.getCurrentSession();
        String hql = "from Elder elder where ename=:ename";
        Query query = session.createQuery(hql).setParameter("ename", ename);
        return (Elder) query.uniqueResult();
    }

    /**
     * 根据主键的eId得到Elderly实例化
     *
     * @param eId 主键eId
     * @return Elderly的实例化
     */
    public Elder getSearchElder(int eId) {
        Session session = factory.getCurrentSession();
        return (Elder) session.get(Elder.class, eId);
    }

    /**
     * 增加Elder信息
     *
     * @param e Elder实例化
     */
    public void addInformation(Elder e) {
        Session session = factory.getCurrentSession();
        session.save(e);
    }

    /**
     * 更新操作
     *
     * @param e Elder实例化
     */
    public void updateInformation(Elder e) {
        Session session = factory.getCurrentSession();
        //更新操作
        session.update(e);
    }

    /**
     * 删除操作
     *
     * @param elder Elder实例化
     */
    public void deleteInformation(Elder elder) {
        Session session = factory.getCurrentSession();
        //删除操作
        session.delete(elder);
    }
}
