package com.ccjjltx.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by ccjjltx on 2017/11/8.
 * 对EinformationDAO心电信息表进行增删改查操作
 *
 * @author ccj
 * @version 1.0
 */
@Service
@Transactional
public class EcginformationDAO {
    @Resource(name = "sessionFactory")
    private SessionFactory factory;

}
