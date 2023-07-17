package ru.itsjava.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itsjava.domain.Notebook;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Класс ProgrammerServiceImpl")
public class ProgrammerServiceImplTest {

    @Configuration
    static class MyConfiguration {

        @Bean
        public IOService ioService() {
            IOService mockIOService = Mockito.mock(IOServiceImpl.class);
            when(mockIOService.input()).thenReturn("pavel");
            return mockIOService;
        }

        @Bean
        public NotebookService notebookService() {
            NotebookService mockNoteBookService = Mockito.mock(NotebookServiceImpl.class);
            when(mockNoteBookService.getNotebook()).thenReturn(new Notebook("Asus", "XL544B", 2015));
            return mockNoteBookService;
        }

        @Bean
        public ProgrammerService programmerService(NotebookService notebookService, IOService ioService) {
            return new ProgrammerServiceImpl(notebookService, ioService);
        }
    }

    @Autowired
    private ProgrammerService programmerService;

    @DisplayName("корректный метод Привет-программист")
    @Test
    public void shouldHaveCorrectSayHiToNewProgrammer() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        programmerService.hiToNewProgrammer();
        Assertions.assertEquals("Enter your name: \n" +
                "Hello pavel\n" +
                "Your computer: Notebook{ Asus XL544B 2015 }\n", out.toString());
    }
}