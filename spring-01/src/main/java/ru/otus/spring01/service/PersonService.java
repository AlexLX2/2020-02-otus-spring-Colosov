package ru.otus.spring01.service;


import java.io.BufferedReader;

public interface PersonService {

    String getLastName();

    String getFirstName();

    int getScore();

    void save(String lastName, String firstName, int score);

    String getRowFromConsole(BufferedReader reader);

}
