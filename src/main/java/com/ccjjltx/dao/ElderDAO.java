package com.ccjjltx.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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


}
