package ru.otus.bookdbmongo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
@EnableConfigurationProperties
class BookdbmongoApplicationTests {

    @Test
    void contextLoads() {
    }

}
