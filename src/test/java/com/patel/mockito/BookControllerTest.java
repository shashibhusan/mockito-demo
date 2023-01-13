package com.patel.mockito;

import com.patel.mockito.controller.BookController;
import com.patel.mockito.model.Book;
import com.patel.mockito.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.internal.util.MockUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerTest {
    Book record_1 = new Book(1l, "book1", "summary1", 5);
    Book record_2 = new Book(2l, "book2", "summary2", 4);
    Book record_3 = new Book(3l, "book3", "summary3", 3);
    Book record_4 = new Book(4l, "book4", "summary4", 4);
    @Mock
    Book book;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookRepository bookRepository;

    @Test
    public void getAllRecord_success() throws Exception {
        System.out.println("===========>>>>>>>>> " + MockUtil.isMock(book));
        List<Book> records = new ArrayList<>(Arrays.asList(record_1, record_2, record_3, record_4));
        when(bookRepository.findAll()).thenReturn(records);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/book")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[2].name", is("book3")));
    }

}
