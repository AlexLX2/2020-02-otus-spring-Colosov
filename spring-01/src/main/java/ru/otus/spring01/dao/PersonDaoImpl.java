package ru.otus.spring01.dao;

import ru.otus.spring01.domain.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonDaoImpl implements PersonDao{

    public List<Person> personList = new ArrayList<>();
    @Override
    public void savePerson(Person person) {
        personList.add(person);
    }
}
