package com.ccjjltx.dao;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by ccjjltx on 2017/11/25.
 * 测试NursingfeeDAO相关的方法
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class NursingfeeDAOTest {
    @Resource(name = "nursingfeeDAO")
    private NursingfeeDAO nursingfeeDAO;
}
