package org.example;

public class DefaultBookService implements BookService {

    BookRepository bookRepository;

    public DefaultBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void rent(Book book) {
        System.out.println("rent: " + book.getTitle());
    }

    @Override
    public void responseBook(Book book) {
        System.out.println("responseBook: " + book.getTitle());
    }

    public void save(Book book) {
        Book saveBook = bookRepository.save(book);
        System.out.println(saveBook.getTitle());
    }
}
