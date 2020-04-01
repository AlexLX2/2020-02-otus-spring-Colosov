package ru.otus.spring04.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring04.dao.PersonDao;
import ru.otus.spring04.domain.Person;

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
    private MessageSource messageSource;

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

        System.out.println(messageSource.getMessage("firstName", null, Locale.getDefault()));
        String firstName = getFirstName();
        System.out.println(messageSource.getMessage("lastName", null, Locale.getDefault()));
        String lastName = getLastName();
        Person person = new Person(firstName, lastName);
        person.setScore(getScore());
        System.out.println(messageSource.getMessage("yourScore", null, Locale.getDefault()));
        System.out.println(person.getScore());
        if (person.getScore() < scoreToPass) {
            System.out.println(messageSource.getMessage("youLost", null, Locale.getDefault()));
        } else {
            System.out.println(messageSource.getMessage("youWon", null, Locale.getDefault()));
        }
    }
}
