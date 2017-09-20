package beans;


import model.Test;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SessionScoped
@Named
public class QuizRunBean implements Serializable {

    public static final int NUMBER_OF_TESTS = 5;
    @Inject
    private QuizBean quizBean;

    private List<Test> selectedTests;
    private int questionNum;
    private Test selectedTest;
    //    private List<Integer> lastAnswers;
    private List<Integer> answers;
    private int answered;
    String lastAnswer;

    @PostConstruct
    public void init() {
        List<Test> tests = quizBean.getTests();
        Collections.shuffle(tests);
        selectedTests = tests.subList(0, NUMBER_OF_TESTS);
        questionNum = 1;
        selectedTest = selectedTests.get(0);
        lastAnswer = "";
        answers = new ArrayList<>();
        for (int i = 0; i < selectedTests.size(); i++) {
            answers.add(-1);
        }
        answered = 0;
//        if (tests!=null) {
//            lastAnswers = new ArrayList<>(answers);
//        }
    }

    public Test getSelectedTest() {
        return selectedTest;
    }

    public void setSelectedTest(Test selectedTest) {
        this.selectedTest = selectedTest;
    }


    public int getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(int questionNum) {
        this.questionNum = questionNum;
    }

    public void fixAnswer() {
        if (lastAnswer.isEmpty() && !selectedTest.getAnswer().isEmpty()) {
            answered++;
        }
        selectedTest = selectedTests.get(questionNum % NUMBER_OF_TESTS);
        lastAnswer = selectedTest.getAnswer();
        questionNum++;
        if (questionNum > NUMBER_OF_TESTS) questionNum = 1;
    }

    public void next() {
        selectedTest.setAnswer(lastAnswer);
        selectedTest = selectedTests.get(questionNum % NUMBER_OF_TESTS);
        lastAnswer = selectedTest.getAnswer();
        questionNum++;
        if (questionNum > NUMBER_OF_TESTS) questionNum = 1;
    }

    public int getAnswered() {
        return answered;
    }

    public int getNumberOfTests() {
        return NUMBER_OF_TESTS;
    }

    public void prev() {
        // FIXME Доработать prev(задания в программе сами, произвольно меняются)
        selectedTest.setAnswer(lastAnswer);
        selectedTest = selectedTests.get(questionNum % NUMBER_OF_TESTS);
        lastAnswer = selectedTest.getAnswer();
        questionNum--;
        if (questionNum <= 0) questionNum = 5;
    }

    public String finish() {
        //TODO Завершение и подсчет
        return "finish";
    }
}
