package model;

import java.util.List;

public class Test {
    private String question;
    private String answer;
    private String coranswer;

    public Test(List<String> txt) {
        question = txt.get(0);
        coranswer = txt.get(1);
        answer = "";
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCoranswer() {
        return coranswer;
    }

}
