package ru.otus.spring04.service;


import java.util.Map;

public interface PersonService {

    String getLastName();

    String getFirstName();

    int getScore();

    String getRowFromConsole();

    int getAnswers(Map<String, String> questionMap);

    int answer(String question, String answer);

    void init();

}
