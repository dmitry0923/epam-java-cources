package com.epam.university.java.project.service;

import com.epam.university.java.project.core.state.machine.manager.StateMachineManager;
import com.epam.university.java.project.domain.Book;
import com.epam.university.java.project.domain.BookImpl;
import com.epam.university.java.project.domain.BookStatus;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * Author Dmitry Novikov 12-Oct-20.
 */
public class BookServiceImpl implements BookService {
    private final Collection<Book> bookCollection;
    private StateMachineManager stateMachineManager;

    public BookServiceImpl() {
        this.bookCollection = new ArrayList<>();
    }

    @Override
    public Book createBook() {
        Book book = new BookImpl();
        // исправить, возможно нужно подтягивать из stateMachineManager

        book.setState(BookStatus.DRAFT);
        return book;
    }

    @Override
    public Book getBook(int id) {
        Optional<Book> book = bookCollection.stream()
                .filter(x -> x.getId() == id)
                .findFirst();
        if (book.isPresent()) {
            return book.get();
        } else {
            return null;
        }
    }

    @Override
    public Collection<Book> getBooks() {
        return bookCollection;
    }

    @Override
    public void remove(Book book) {
        Optional<Book> isBook = bookCollection.stream()
                .filter(x -> x.getId() == book.getId())
                .findFirst();
        if (isBook.isPresent()) {
            bookCollection.remove(isBook.get());
        } else {
            throw new RuntimeException("Can't remove book " + book.getTitle()
                    + " with id " + book.getId() + ". Not found.");
        }
    }

    @Override
    public Book save(Book book) {
        bookCollection.add(book);
        return book;
    }

    @Override
    public Book accept(Book book, String number) {
        book.setSerialNumber(number);
        // при срабатывании этого метода, статус книги должен менять на accept
        book.setState(BookStatus.ACCOUNTED);
        return book;
    }

    @Override
    public Book issue(Book book, LocalDate returnDate) {
        book.setReturnDate(returnDate);
        return book;
    }

    @Override
    public Book returnFromIssue(Book book) {
        return book;
    }
}