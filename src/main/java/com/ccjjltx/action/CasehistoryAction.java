package com.ccjjltx.action;

import com.ccjjltx.dao.CasehistoryDAO;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by ccjjltx on 2017/11/8.
 * 病例增删改查
 *
 * @author ccj
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class CasehistoryAction extends ActionSupport {
    @Resource(name = "casehistoryDAO")
    private CasehistoryDAO casehistoryDAO;

}
