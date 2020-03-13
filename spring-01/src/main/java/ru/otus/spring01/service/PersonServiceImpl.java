package ru.otus.spring01.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import ru.otus.spring01.dao.PersonDao;
import ru.otus.spring01.domain.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class PersonServiceImpl implements PersonService {

    private final PersonDao dao;

    private String questionFile;

    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    BufferedReader getReader(){
        return reader;
    }

    public PersonServiceImpl(PersonDao dao) {
        this.dao = dao;
    }

    @Override
    public void save(String lastName, String firstName, int score) {
        dao.savePerson(new Person(lastName, firstName, score));
    }

    public void setQuestionFile(String questionFile) {
        this.questionFile = questionFile;
    }

    public String getRowFromConsole(BufferedReader reader){
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
        return getRowFromConsole(getReader());
    }

    @Override
    public String getFirstName() {
        return getRowFromConsole(reader);
    }

    @Override
    public int getScore() {
        return getAnswers(readQuestions());
    }

    public Map<String, String> readQuestions() {
        Map<String, String> questionMap = new HashMap<>();
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(questionFile);
        assert inputStream != null;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        CSVParser csvParser;
        try {
            csvParser = CSVFormat.EXCEL.parse(inputStreamReader);
            for (CSVRecord record : csvParser) {
                String question = record.get(0);
                String answer = record.get(1);
                questionMap.put(question, answer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return questionMap;
    }

    public int getAnswers(Map<String, String> questionMap) {
        int score = 0;
        for(Map.Entry<String, String> entry : questionMap.entrySet()){
            try {
                score += answer(entry.getKey(), entry.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return score;
    }

    public int answer(String question, String answer) throws IOException {
        System.out.println(question);
        String actualAnswer = getRowFromConsole(reader);
        if (actualAnswer.equals(answer)) {
            return 1;
        }
        return 0;
    }
}
