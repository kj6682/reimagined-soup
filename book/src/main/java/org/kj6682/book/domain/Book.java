package org.kj6682.book.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity(name = "Book")
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String isbn;
    private String title;

    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BooksAuthors> booksAuthors;

    private String location;

    public Book() {
    }

    public Book(String isbn, String title, String location) {
        this.isbn = isbn;
        this.title = title;
        this.location = location;
    }

    public Long getId() {
        return id;
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

    public Set<BooksAuthors> getBooksAuthors() {
        return booksAuthors;
    }
    public void setBooksAuthors(Set<BooksAuthors> booksAuthors) {
        this.booksAuthors = booksAuthors;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void addAuthor(Author author) {
        BooksAuthors booksAuthors = new BooksAuthors(this, author);
        this.booksAuthors.add(booksAuthors);
        author.getBooksAuthors().add(booksAuthors);
    }
    public void removeAuthor(Author author) {
        BooksAuthors booksAuthors = new BooksAuthors(this, author);
        this.booksAuthors.remove(booksAuthors);
        author.getBooksAuthors().remove(booksAuthors);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;   // 1
        Book book = (Book) o;                                        // 2
        return Objects.equals(isbn, book.isbn);                      // 3
    }
    @Override
    public int hashCode() {                                         // 4
        return Objects.hash(isbn);
    }
}