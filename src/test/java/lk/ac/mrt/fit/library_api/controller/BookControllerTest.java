package lk.ac.mrt.fit.library_api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import lk.ac.mrt.fit.library_api.model.Book;
import lk.ac.mrt.fit.library_api.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

/** Database-independent tests: the service is mocked, so MySQL is not needed. */
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    private Book sample() {
        Book b = new Book();
        b.setId(1L);
        b.setTitle("235083B - Clean Code");
        b.setAuthor("Robert C. Martin");
        b.setIsbn("9780132350884");
        b.setPrice(4500.0);
        return b;
    }

    private static final String VALID_JSON =
            "{\"title\":\"235083B - Clean Code\",\"author\":\"Robert C. Martin\",\"isbn\":\"9780132350884\",\"price\":4500.00}";

    @Test
    void createReturns201() throws Exception {
        when(bookService.createBook(any(Book.class))).thenReturn(sample());
        mockMvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON).content(VALID_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void emptyTitleReturns400() throws Exception {
        String json = "{\"title\":\"\",\"author\":\"A\",\"price\":10}";
        mockMvc.perform(post("/api/books").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fieldErrors.title").exists());
    }

    @Test
    void listReturns200() throws Exception {
        when(bookService.getAllBooks()).thenReturn(List.of(sample()));
        mockMvc.perform(get("/api/books")).andExpect(status().isOk());
    }

    @Test
    void getMissingReturns404() throws Exception {
        when(bookService.getBookById(99L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/books/99")).andExpect(status().isNotFound());
    }

    @Test
    void updateReturns200AndMissingReturns404() throws Exception {
        when(bookService.updateBook(eq(1L), any(Book.class))).thenReturn(Optional.of(sample()));
        when(bookService.updateBook(eq(99L), any(Book.class))).thenReturn(Optional.empty());
        mockMvc.perform(put("/api/books/1").contentType(MediaType.APPLICATION_JSON).content(VALID_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(put("/api/books/99").contentType(MediaType.APPLICATION_JSON).content(VALID_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteReturns204AndMissingReturns404() throws Exception {
        when(bookService.deleteBook(1L)).thenReturn(true);
        when(bookService.deleteBook(99L)).thenReturn(false);
        mockMvc.perform(delete("/api/books/1")).andExpect(status().isNoContent());
        mockMvc.perform(delete("/api/books/99")).andExpect(status().isNotFound());
    }
}
