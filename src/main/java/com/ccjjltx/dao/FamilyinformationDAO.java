package com.ccjjltx.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by ccjjltx on 2017/11/5.
 * 对Familyinformation表的增删改查
 *
 * @author ccj
 * @version 1.0
 */
@Service
@Transactional
public class FamilyinformationDAO {
    @Resource(name = "sessionFactory")
    private SessionFactory factory;
}
