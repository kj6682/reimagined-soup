package org.kj6682.book.api;

import org.kj6682.book.domain.Author;
import org.kj6682.book.domain.AuthorRepository;
import org.kj6682.book.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public List<AuthorCompleteDto> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private AuthorCompleteDto convertToDto(Author author) {
        AuthorCompleteDto dto = new AuthorCompleteDto();
        dto.setId(author.getId());
        dto.setName(author.getName());
        dto.setBiography(author.getBiography());
        return dto;
    }
}
