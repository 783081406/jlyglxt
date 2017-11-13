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


}
