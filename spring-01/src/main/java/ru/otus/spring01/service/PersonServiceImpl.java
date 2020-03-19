package ru.otus.spring01.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring01.dao.PersonDao;
import ru.otus.spring01.domain.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonDao dao;

    private final int scoreToPass;

    @Autowired
    public PersonServiceImpl(PersonDao dao, @Value("${scoreToPass}") int scoreToPass) {
        this.dao = dao;
        this.scoreToPass = scoreToPass;
    }

    @Override
    public String getRowFromConsole() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String row = null;
        try {
            row = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public String getLastName() {
        return getRowFromConsole();
    }

    @Override
    public String getFirstName() {
        return getRowFromConsole();
    }

    @Override
    public int getScore() {
        return getAnswers(readQuestions());
    }

    public Map<String, String> readQuestions() {
        return dao.readQuestions();
    }

    @Override
    public int getAnswers(Map<String, String> questionMap) {
        int score = 0;
        for (Map.Entry<String, String> entry : questionMap.entrySet()) {
            score += answer(entry.getKey(), entry.getValue());
        }
        return score;
    }

    @Override
    public int answer(String question, String answer) {
        System.out.println(question);
        String actualAnswer = getRowFromConsole();
        if (actualAnswer.equals(answer)) {
            return 1;
        }
        return 0;
    }

    @Override
    public void init() {

        ResourceBundle bundle = ResourceBundle.getBundle("bundle", Locale.getDefault());
        System.out.println("Locale: " + Locale.getDefault());
        Person person = new Person();
        System.out.println(bundle.getString("firstName"));
        person.setFirstName(getFirstName());
        System.out.println(bundle.getString("lastName"));
        person.setLastName(getLastName());
        person.setScore(getScore());
        System.out.print(bundle.getString("yourScore"));
        System.out.println(person.getScore());
        if (person.getScore() < scoreToPass) {
            System.out.println(bundle.getString("youLost"));
        } else {
            System.out.println(bundle.getString("youWon"));
        }
    }
}
