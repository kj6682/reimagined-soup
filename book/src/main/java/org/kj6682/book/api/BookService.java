package org.kj6682.book.api;


import org.kj6682.book.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> fetchAllBooks() {
        return (List<Book>) bookRepository.fetchAllBooks();
    }

    public List<Book> findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Book book) {
        Optional<Book> existingBook = bookRepository.findById(book.getId());
        if (existingBook.isPresent()) {
            Book updatedBook = existingBook.get();
            updatedBook.setIsbn(book.getIsbn());
            updatedBook.setTitle(book.getTitle());
            // Update other fields as needed
            return bookRepository.save(updatedBook);
        } else {
            throw new RuntimeException("Book not found with id: " + book.getId());
        }
    }


    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private BookDto convertToDto(Book book) {
        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setIsbn(book.getIsbn());
        dto.setTitle(book.getTitle());
        dto.setLocation(book.getLibraryRegisterEntries().iterator().next().getLocation());
        dto.setAuthors(book.getLibraryRegisterEntries().stream()
                .map(this::convertAuthorToDto)
                .collect(Collectors.toList()));
        return dto;
    }

    private AuthorDto convertAuthorToDto(LibraryRegisterEntry register) {
        AuthorDto dto = new AuthorDto();
        dto.setId(register.getAuthor().getId());
        dto.setName(register.getAuthor().getName());
        return dto;
    }

}//:)