package com.ccjjltx.action;

import com.ccjjltx.dao.EinformationDAO;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by ccjjltx on 2017/10/19.
 * 员工信息增删改查
 *
 * @author ccj
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class EinformationAction extends ActionSupport {

    @Resource(name = "einformationDAO")
    private EinformationDAO einformationDAO;

    public String getAllInformation() {

        return SUCCESS;
    }
}
