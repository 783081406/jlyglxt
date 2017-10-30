package com.ccjjltx.dao;

import com.ccjjltx.domain.Elder;
import com.ccjjltx.domain.Roominformation;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/10/29.
 * 对房间信息的增删查改
 *
 * @author ccj
 * @version 1.0
 */
@Service
@Transactional
public class RoominformationDAO {
    @Resource(name = "sessionFactory")
    private SessionFactory factory;
    @Resource(name = "elderDAO")
    private ElderDAO elderDAO;

    /**
     * 得到所有楼层房间的数据
     *
     * @param offset 起始的行数
     * @param rows   显示的行数
     * @param ename  查询提交过来的老人的名字
     * @return 得到所有的数据返回List
     */
    public List<Roominformation> getAllInformation(int offset, int rows, String ename) {
        Session session = factory.getCurrentSession();
        //总查询语句
        String hql = "from Roominformation roominformation";
        //拼接的查询语句
        String hqlSuffix = " order by floor asc,roomNumber asc";
        Query query;
        Elder db_elder;
        //搜索引擎有提交数据过来触发
        if (ename != null) {
            db_elder = elderDAO.getSearchElder(ename);
            if (db_elder == null) {
                return null;
            } else {
                hql += " where elder=:elder" + hqlSuffix;
                query = session.createQuery(hql).setParameter("elder", db_elder).setFirstResult(offset).setMaxResults(rows);
                return (List<Roominformation>) query.list();
            }
        } else {
            hql += hqlSuffix;
            query = session.createQuery(hql).setFirstResult(offset).setMaxResults(rows);
            return (List<Roominformation>) query.list();
        }
    }

    /**
     * 得到全部或者特定的总条数
     *
     * @param ename 老人的姓名
     * @return 总条数
     */
    public int getAllInformationNumber(String ename) {
        Session session = factory.getCurrentSession();
        //总查询语句
        String hql = "select count(*) from Roominformation roominformation";
        Query query;
        Elder db_elder;
        if (ename != null) {
            db_elder = elderDAO.getSearchElder(ename);
            if (db_elder == null) {
                return 0;
            } else {
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
     * 是否存在该楼号与房间号
     *
     * @param floor      楼号
     * @param roomNumber 房间号
     * @return true表示存在，false表示不存在
     */
    public boolean isFloorAndRoomNumber(String floor, int roomNumber) {
        Session session = factory.getCurrentSession();
        String hql = "from Roominformation roominformation where floor=:floor and roomNumber=:roomNumber";
        Query query = session.createQuery(hql).setParameter("floor", floor).setParameter("roomNumber", roomNumber);
        Roominformation r = (Roominformation) query.uniqueResult();
        if (r != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据老人的eid得到是否该老人已经入住某个房间了
     *
     * @param eId 老人缩略表中的eid主键
     * @return true表示已经入住了，false表示还没入住
     */
    public boolean isLive(int eId) {
        Session session = factory.getCurrentSession();
        Elder db_elder = elderDAO.getSearchElder(eId);
        String hql = "from Roominformation roominformation where elder=:elder";
        Query query = session.createQuery(hql).setParameter("elder", db_elder);
        Roominformation ri = (Roominformation) query.uniqueResult();
        if (ri != null) {
            return true;
        } else {
            return false;
        }
    }
}
