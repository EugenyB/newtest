package beans;

import model.Test;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Named
@SessionScoped
public class QuizBean implements Serializable {

    public static final String FILE_NAME = "D:/Programs/IdeaProjects/newtest/tests.txt";
    private List<Test> tests;

    @PostConstruct
    public void init() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            List<String> strings = reader.lines().collect(toList());
            tests = createTest(strings);
            printDebug();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void printDebug() {
        System.out.println(">>>> numOfQuestions = " + tests.size());
    }

    private List<Test> createTest(List<String> strings) {
        List<Test> result = new ArrayList<>();
        for (int i = 0; i < strings.size() / 3; i++) {
            result.add(new Test(strings.subList(i * 3, (i + 1) * 3)));
        }
        return result;
    }

    public List<Test> getTests() {

        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
    public String start() {
        return "quiz";
    }
}