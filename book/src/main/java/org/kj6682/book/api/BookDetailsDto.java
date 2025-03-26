package org.kj6682.book.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookDetailsDto {
    private String title;
    private List<String> authorNames;
    private String location;

    // Constructors, getters, and setters

    public BookDetailsDto(String title, String authorNames, String location) {
        this.title = title;
        List<String> trimmedAuthorList = Arrays.asList(authorNames.split(","));
        for (int i = 0; i < trimmedAuthorList.size(); ++i) {
            trimmedAuthorList.set(i, trimmedAuthorList.get(i).trim());
        }
        this.authorNames = new ArrayList<>(trimmedAuthorList); // assign the trimmed list to the authorNames field
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthorNames() {
        return authorNames;
    }

    public void setAuthorNames(List<String> authorNames) {
        this.authorNames = authorNames;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
