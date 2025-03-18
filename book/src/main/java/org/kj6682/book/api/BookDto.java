package org.kj6682.book.api;

import java.util.List;

public class BookDto {
    private Long id;
    private String isbn;
    private String title;
    private String location;
    private List<AuthorDto> authors;

    public BookDto() {
    }

    public BookDto(Long id, String isbn, String title, String location) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<AuthorDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDto> authors) {
        this.authors = authors;
    }
}