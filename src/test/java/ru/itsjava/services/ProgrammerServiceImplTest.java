package ru.itsjava.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itsjava.domain.Notebook;

import static org.mockito.Mockito.when;
@SpringBootTest
@DisplayName("Класс ProgrammerService")
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


    @DisplayName("Корректный метод Привет-программист")
    @Test
    public void shouldHaveCorrectSayHiToNewProgrammer() {
        programmerService.hiToNewProgrammer();
    }
}


