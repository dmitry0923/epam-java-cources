package com.epam.university.java.project.service;

import com.epam.university.java.project.domain.Book;
import com.epam.university.java.project.domain.BookImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * Author Dmitry Novikov 12-Oct-20.
 */
public class BookDaoXmlImpl implements BookDao {
    private final Collection<Book> bookCollection;

    public BookDaoXmlImpl() {
        this.bookCollection = new ArrayList<>();
    }

    @Override
    public Book createBook() {
        return new BookImpl();
    }

    @Override
    public Book getBook(int id) {
        Optional<Book> book = bookCollection.stream()
                .filter(x -> x.getId() == id)
                .findFirst();
        if (book.isPresent()) {
            return book.get();
        } else {
            throw new RuntimeException("There is no book with id " + id);
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
}