package org.kj6682.book.api;

public class BookDetailsDto {
    private String title;
    private String authorName;
    private String location;

    // Constructors, getters, and setters

    public BookDetailsDto(String title, String authorName, String location) {
        this.title = title;
        this.authorName = authorName;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
