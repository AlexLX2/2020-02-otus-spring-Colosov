package ru.otus.spring04.dao;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

@Component
public class PersonDaoImpl implements PersonDao {

    private final String questionFile;

    public PersonDaoImpl() {
        Locale.setDefault(new Locale("ru", "RU"));
        ResourceBundle bundle = ResourceBundle.getBundle("bundle", Locale.getDefault());
        this.questionFile = bundle.getString("questionFile");
    }


    @Override
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
}
