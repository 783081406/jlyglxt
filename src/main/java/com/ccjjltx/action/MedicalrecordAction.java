package com.ccjjltx.action;

import com.ccjjltx.dao.MedicalrecordDAO;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by ccjjltx on 2017/11/8.
 * 就医记录增删查改
 *
 * @author ccj
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class MedicalrecordAction extends ActionSupport {
    @Resource(name = "medicalrecordDAO")
    private MedicalrecordDAO medicalrecordDAO;
}
