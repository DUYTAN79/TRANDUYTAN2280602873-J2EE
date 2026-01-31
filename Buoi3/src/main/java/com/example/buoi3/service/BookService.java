package com.example.buoi3.service;

import com.example.buoi3.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private List<Book> books = new ArrayList<>();

    public BookService() {
        books.add(new Book(1L, "Java cơ bản", "Nguyễn Văn A", 50000));
        books.add(new Book(2L, "Spring Boot", "Trần Văn B", 75000));
        books.add(new Book(3L, "Lập trình Web", "Lê Văn C", 65000));
    }

    // Lấy danh sách sách
    public List<Book> getAllBooks() {
        return books;
    }

    // Lấy sách theo id
    public Optional<Book> getBookById(Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    // Thêm sách
    public void addBook(Book book) {
        books.add(book);
    }

    // Cập nhật sách
    public void updateBook(Book updatedBook) {
        getBookById(updatedBook.getId()).ifPresent(book -> {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setPrice(updatedBook.getPrice());
        });
    }

    // Xóa sách
    public void deleteBook(Long id) {
        books.removeIf(book -> book.getId().equals(id));
    }
}
