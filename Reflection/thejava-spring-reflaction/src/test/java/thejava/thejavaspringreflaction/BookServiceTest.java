package thejava.thejavaspringreflaction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookServiceTest {

    @Autowired
    BookService bookService;

    @Test
    void di() {
        Assertions.assertNotNull(bookService.bookRepository); //스프링 컨테이너가 DI해줌
    }

}