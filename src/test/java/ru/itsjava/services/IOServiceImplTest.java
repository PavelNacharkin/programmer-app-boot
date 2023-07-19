package ru.itsjava.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.io.ByteArrayInputStream;


@SpringBootTest
public class IOServiceImplTest {


    @Configuration
    static class MyConfiguration {
        public static final String PRIVET = "privet";
        private final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(PRIVET.getBytes());

        @Bean
        public IOService ioService() {
            return new IOServiceImpl(byteArrayInputStream);
        }

    }

    @Autowired
    private IOService ioService;

    @DisplayName("Корректно работает метод Input")
    @Test
    public void shouldHaveCorrectInput() {
        Assertions.assertEquals(MyConfiguration.PRIVET, ioService.input());
    }

}
