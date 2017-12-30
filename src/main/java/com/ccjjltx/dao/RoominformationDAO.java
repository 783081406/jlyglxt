package com.ccjjltx.dao;

import com.ccjjltx.domain.Elder;
import com.ccjjltx.domain.Roomcost;
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
    @Resource(name = "roomcostDAO")
    private RoomcostDAO roomcostDAO;

    /**
     * 根据rId得到Roominformation实例化
     *
     * @param rId 主键rId
     * @return Roominformation实例化
     */
    public Roominformation getSearchRoominformation(int rId) {
        Session session = factory.getCurrentSession();
        //根据rId得到Roominformation实例化
        return (Roominformation) session.get(Roominformation.class, rId);
    }

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
     * 增加房间信息
     *
     * @param floor      楼号
     * @param roomNumber 房间号
     * @param rType      房间类型
     * @param rCost      房间价格
     * @param eId        老人信息表的eId
     * @return int类型（1表示已经存在该楼号与房间号码，2表示该名老人已经入住，3表示插入成功）
     */
    public int addInformation(String floor, int roomNumber, String rType, int rCost, int eId) {
        Session session = factory.getCurrentSession();
        //首先得出数据库中是否有相应的数据信息（楼号与房间号）
        if (!isFloorAndRoomNumber(floor, roomNumber)) {//表示确定是一个新房间
            ///////////////////////////////////////////////////////
            //验证是否有相同的房间类型
            Roomcost db_roomcost = roomcostDAO.getSearchRoomcost(rType);
            if (db_roomcost != null) {//如果不为空，表示已经存在，如果费用一样则不变否则，更新
                if (db_roomcost.getRCost() != rCost) {//费用不同，选择更新
                    db_roomcost.setRCost(rCost);
                    roomcostDAO.updateRoomcost(db_roomcost);
                }
            } else {//为空，表示不存在，则应该增加数据
                roomcostDAO.addRoomcost(rType, rCost);
                //更新db_roomcost
                db_roomcost = roomcostDAO.getSearchRoomcost(rType);
            }
            /////////////////////////////////////////////////////////
            //验证该老人是否已经房间了
            Elder db_elder;
            if (eId != 0) {
                //验证该老人是否已有房间
                if (isLive(eId)) {//表示已经有房间//返回2插入失败
                    return 2;
                } else {
                    //表示还没有房间则实例化
                    db_elder = elderDAO.getSearchElder(eId);
                }
            } else {
                //表示为eId为空
                db_elder = null;
            }
            ///////////////////通过所有的判断则插入数据///////////////////////
            Roominformation ri = new Roominformation(floor, roomNumber, db_roomcost, db_elder);
            //输入数据
            session.save(ri);
            return 3;
        } else {
            //表示已经有相同的楼号和房间号了，添加失败
            return 1;
        }
    }


    /**
     * 更新信息
     *
     * @param roominformation Roominformation实例化
     * @param eId             老人缩略表中的主键eId
     * @return int类型，1表示该老人已经入住，2表示已经存在该楼号与房间号，3表示更新成功
     */
    public int updateInformation(Roominformation roominformation, int eId) {
        Session session = factory.getCurrentSession();
        if (eId != 0) {
            //首先查询该老人是否入住
            if (isLive(eId, roominformation.getRId())) {
                //表示已入住
                return 1;
            } else {
                //无入住
                roominformation.setElder(elderDAO.getSearchElder(eId));
            }
        } else {
            //表示为空
            roominformation.setElder(null);
        }
        //判断是否已经有相同的楼号与房间号
        if (isFloorAndRoomNumber(roominformation.getRId(), roominformation.getFloor(), roominformation.getRoomNumber())) {
            return 2;
        }
        //更新Roomcost
        session.update(roominformation.getRoomcost());
        //更新roominformation
        session.update(roominformation);
        return 3;
    }

    /**
     * 是否存在该楼号与房间号
     * 用于新增操作
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
     * 重载isFloorAndRoomNumber，用于判断是否存在相同的楼号与房间号
     * 主要用于更新
     *
     * @param rId        RoomInformation主键rId
     * @param floor      楼号
     * @param roomNumber 房间号
     * @return true表示存在，false表示不存在
     */
    public boolean isFloorAndRoomNumber(int rId, String floor, int roomNumber) {
        Session session = factory.getCurrentSession();
        String hql = "from Roominformation roominformation where rId!=:rId and floor=:floor and roomNumber=:roomNumber";
        Query query = session.createQuery(hql).setParameter("rId", rId).setParameter("floor", floor).setParameter("roomNumber", roomNumber);
        Roominformation r = (Roominformation) query.uniqueResult();
        if (r != null) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 根据老人的eid得到是否该老人已经入住某个房间了
     * 主要用于：新增
     *
     * @param eId 老人缩略表中的eId主键
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

    /**
     * 根据老人的eid得到是否该老人已经入住某个房间了
     * 主要作用：更新
     *
     * @param eId 老人缩略表中的eId主键
     * @param rId 房间信息中的主键rId
     * @return true表示已经入住，false表示还没入住
     */
    public boolean isLive(int eId, int rId) {
        Session session = factory.getCurrentSession();
        Elder db_elder = elderDAO.getSearchElder(eId);
        String hql = "from Roominformation roominformation where rId!=:rId and elder=:elder";
        Query query = session.createQuery(hql).setParameter("rId", rId).setParameter("elder", db_elder);
        Roominformation ri = (Roominformation) query.uniqueResult();
        if (ri != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 删除数据
     * @param rId 主键
     */
    public void deleteInformation(int rId) {
        Session session = factory.getCurrentSession();
        //根据rId得到Roominformation
        //删除操作
        session.delete(getSearchRoominformation(rId));
    }
}
