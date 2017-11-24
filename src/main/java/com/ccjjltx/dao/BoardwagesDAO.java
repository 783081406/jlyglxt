package com.ccjjltx.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by ccjjltx on 2017/11/24.
 * 对伙食费数据的相关操作
 */
@Service
@Transactional
public class BoardwagesDAO {
    @Resource(name = "sessionFactory")
    private SessionFactory factory;
}
