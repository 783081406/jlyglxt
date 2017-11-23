package com.ccjjltx.action;

import com.ccjjltx.dao.InterlocutionDAO;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * Created by ccjjltx on 2017/11/23.
 * 对常见问题数据的增删改查
 *
 * @author ccj
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class InterlocutionAction {
    @Resource(name = "interlocutionDAO")
    private InterlocutionDAO interlocutionDAO;
    private int page;
    private int rows;
    private JSONObject result;
    ///////////////提交过来Interlocution表中的数据////////////
    private int qaid;
    private String question;
    private String answer;
    private int isSelect;
    private int[] qaids;

    ///////////////////////////////////////////////////////////
    public InterlocutionDAO getInterlocutionDAO() {
        return interlocutionDAO;
    }

    public void setInterlocutionDAO(InterlocutionDAO interlocutionDAO) {
        this.interlocutionDAO = interlocutionDAO;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public JSONObject getResult() {
        return result;
    }

    public void setResult(JSONObject result) {
        this.result = result;
    }

    public int getQaid() {
        return qaid;
    }

    public void setQaid(int qaid) {
        this.qaid = qaid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(int isSelect) {
        this.isSelect = isSelect;
    }

    public int[] getQaids() {
        return qaids;
    }

    public void setQaids(int[] qaids) {
        this.qaids = qaids;
    }
}
