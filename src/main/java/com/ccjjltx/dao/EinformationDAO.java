package com.ccjjltx.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by ccjjltx on 2017/10/19.
 * 对Einformation表的相关操作
 *
 * @author ccj
 * @version 1.0
 */
@Service
@Transactional
public class EinformationDAO {
    @Resource(name = "sessionFactory")
    private SessionFactory factory;

}
