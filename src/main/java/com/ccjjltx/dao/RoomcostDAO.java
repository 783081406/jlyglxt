package com.ccjjltx.dao;

import com.ccjjltx.domain.Roomcost;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by ccjjltx on 2017/10/30.
 * 对房间类型&价格数据的增删改查
 *
 * @author ccj
 * @version 1.0
 */
@Service
@Transactional
public class RoomcostDAO {
    @Resource(name = "sessionFactory")
    private SessionFactory factory;

    /**
     * 根据rcId得到Roomcost实例化
     *
     * @param rcId 主键rcId
     * @return Roomcost实例化
     */
    public Roomcost getSearchRoomcost(int rcId) {
        Session session = factory.getCurrentSession();
        return (Roomcost) session.load(Roomcost.class, rcId);
    }

    /**
     * 根据rType房间类型得到Roomcost实例化
     *
     * @param rType 房间类型
     * @return Roomcost实例化
     */
    public Roomcost getSearchRoomcost(String rType) {
        Session session = factory.getCurrentSession();
        String hql = "from Roomcost roomcost where rType=:rType";
        Query query = session.createQuery(hql).setParameter("rType", rType);
        return (Roomcost) query.uniqueResult();
    }

}
