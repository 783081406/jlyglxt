package com.ccjjltx.dao;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by ccjjltx on 2017/11/8.
 * 测试MedicalrecordDAO相关功能
 *
 * @author ccj
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans.xml")
public class MedicalrecordDAOTest {
    @Resource(name = "medicalrecordDAO")
    private MedicalrecordDAO medicalrecordDAO;
}
