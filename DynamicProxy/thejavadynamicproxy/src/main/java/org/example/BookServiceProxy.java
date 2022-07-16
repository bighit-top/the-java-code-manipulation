package org.example;

public class BookServiceProxy implements BookService {

    BookService bookService;

    public BookServiceProxy(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void rent(Book book) {
        System.out.println("##### start compile proxy message #####");
        bookService.rent(book); //call real instance
        System.out.println("##### end compile proxy message #####");
    }

    @Override
    public void responseBook(Book book) {
        System.out.println("proxy message");
        bookService.responseBook(book);
    }
}
