package com.ccjjltx.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ccjjltx on 2017/11/23.
 * 常见问题
 *
 * @author ccj
 * @version 1.0
 */
@Entity
@Table
public class Interlocution implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int qaid;
    private String question;
    private String answer;
    private int isSelect;

    //无参构造函数
    public Interlocution() {
    }

    //有参构造函数
    public Interlocution(String question, String answer) {
        this.question = question;
        this.answer = answer;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Interlocution that = (Interlocution) o;

        if (qaid != that.qaid) return false;
        if (isSelect != that.isSelect) return false;
        if (question != null ? !question.equals(that.question) : that.question != null) return false;
        if (answer != null ? !answer.equals(that.answer) : that.answer != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = qaid;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + isSelect;
        return result;
    }
}
