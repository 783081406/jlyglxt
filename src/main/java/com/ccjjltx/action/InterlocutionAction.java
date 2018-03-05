package com.ccjjltx.action;

import com.ccjjltx.dao.InterlocutionDAO;
import com.ccjjltx.domain.Interlocution;
import com.ccjjltx.utils.JsonMessage;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ccjjltx on 2017/11/23.
 * 对常见问题数据的增删改查
 *
 * @author ccj
 * @version 1.0
 */
@Controller
@Scope("prototype")
public class InterlocutionAction extends ActionSupport {
    @Resource(name = "interlocutionDAO")
    private InterlocutionDAO interlocutionDAO;
    private int page;
    private int rows;
    private JSONObject result;
    ///////////////提交过来Interlocution表中的数据////////////
    private int qaid;
    private String question;
    private String answer;
    private int[] qaids;
    ///////////////////////////////////////////////////////////
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

    public int[] getQaids() {
        return qaids;
    }

    public void setQaids(int[] qaids) {
        this.qaids = qaids;
    }

    /**
     * 得到所有的信息数据
     *
     * @return list集合
     */
    public String getAllInformation() {
        int offset = (getPage() - 1) * getRows();
        List<Interlocution> list = interlocutionDAO.getAllInformation(offset, getRows());
        int total = interlocutionDAO.getAllInformationNumber();
        result = new JSONObject();
        result.put("total", total);
        JSONArray jsonArray = new JSONArray();
        for (Interlocution il : list) {
            JSONObject js = new JSONObject();
            js.put("qaid", il.getQaid());
            js.put("question", il.getQuestion());
            js.put("answer", il.getAnswer());
            js.put("checked", il.getIsSelect() == 0 ? false : true);
            jsonArray.add(js);
        }
        result.put("rows", jsonArray);
        return SUCCESS;
    }

    /**
     * 重选功能
     *
     * @return 成功或失败的提示信息
     */
    public String reelectInformation() {
        //执行重选功能
        interlocutionDAO.reelectInformation();
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }

    /**
     * 提交功能，选择要展示的数据
     *
     * @return Json，成功或失败的信息
     */
    public String selectInformation() {
        interlocutionDAO.updateIsSelect(getQaids());
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }

    /**
     * 增加新数据
     *
     * @return Json成功或失败的信息
     */
    public String saveInformation() {
        Interlocution il = new Interlocution(getQuestion(), getAnswer());
        interlocutionDAO.addInformation(il);
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }

    /**
     * 更新数据
     *
     * @return Json成功或失败的信息
     */
    public String updateInformation() {
        //根据提交过来的主键得到实例化
        Interlocution il = interlocutionDAO.getSearchInformation(getQaid());
        //更新数据
        il.setQuestion(getQuestion());
        il.setAnswer(getAnswer());
        //更新数据
        interlocutionDAO.updateInformation(il);
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }

    /**
     * 删除数据
     *
     * @return 成功或失败的json信息
     */
    public String removeInformation() {
        interlocutionDAO.removeInformation(getQaid());
        result = JsonMessage.returnMessage(true, "success");
        return SUCCESS;
    }
}
