package com.epam.university.java.project.service;

import com.epam.university.java.project.domain.Book;
import com.epam.university.java.project.domain.BookImpl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookDaoXmlImpl implements BookDao {
    private int lastBookId = 0;
    private final List<Book> library = new ArrayList<>();

    @Override
    public Book createBook() {
        return new BookImpl();
    }

    @Override
    public Book getBook(int id) {
        for (Book book : library) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    @Override
    public Collection<Book> getBooks() {
        return library;
    }

    @Override
    public void remove(Book book) {
        library.remove(book);
    }

    @Override
    public Book save(Book book) {
        book.setId(++lastBookId);
        library.add(book);
        return book;
    }
}