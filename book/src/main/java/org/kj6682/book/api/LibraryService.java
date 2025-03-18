package org.kj6682.book.api;

import org.kj6682.book.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LibraryService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private LibraryRegisterEntryRepository libraryRegisterEntryRepository;

    @Transactional
    public void initializeDatabase() {

        Author author1 = new Author("Harper Lee");
        author1.setBiography("Harper Lee was an American novelist and short story writer whose only novel, To Kill a Mockingbird, was published in 1960. It became an instant bestseller and won the Pulitzer Prize for Fiction in 1961. The book was later adapted into an Academy Award-winning film.");
        Author author2 = new Author("George Orwell");
        author2.setBiography("George Orwell, born Eric Arthur Blair in 1903, was an English novelist, essayist, journalist, and critic. His most famous works include \"Animal Farm\" and \"1984,\" which critique totalitarianism and government surveillance. Orwell's real name, George Orwell, is a pen name he adopted in 1933.");
        Author author3 = new Author("J.R.R. Tolkien");
        author3.setBiography("John Ronald Reuel Tolkien, born in 1892, was an English writer, poet, philologist, and university professor. He is best known for his middle-earth legendarium, including the classic high-fantasy works \"The Hobbit\" and \"The Lord of the Rings.\" Tolkien's extensive scholarship and linguistic work are also significant contributions to literature and linguistics.");
        Author author4 = new Author("Franz Kafka");
        author4.setBiography("Franz Kafka, born in 1883, was a German-language novelist and short story writer whose work is known for its darkly satirical and existential themes. His most famous works include \"The Trial,\" \"The Castle,\" and \"The Metamorphosis.\" Kafka's writing often explores themes of alienation, guilt, and the absurdity of life.");
        Author author5 = new Author("Christopher Brousseau");
        author5.setBiography("Christopher Brousseau is a contemporary American author known for his young adult and middle-grade novels. His works often explore themes of identity, friendship, and the complexities of growing up. Brousseau's debut novel, \"The Art of Being Normal,\" was a New York Times bestseller and won the Michael L. Printz Award.");
        Author author6 = new Author("Matthew Sharp");
        author6.setBiography("Matthew Sharp is an American author known for his young adult and middle-grade novels. His works often delve into themes of identity, friendship, and the challenges of growing up. Sharp's debut novel, \"The Hate U Give,\" was a New York Times bestseller and won the Michael L. Printz Award. The book explores themes of racial injustice and social activism through the eyes of a teenage girl.");


        authorRepository.saveAll(List.of(author1, author2, author3, author4, author5, author6));

        Book book1 = new Book("9780061120084", "To Kill a Mockingbird");
        Book book2 = new Book("9780451524935", "1984");
        Book book3 = new Book("9780618260300", "The Hobbit");
        Book book4 = new Book("9780140449136", "The Lord of the Rings");
        Book book5 = new Book("9780060935467", "Der Prozess");
        Book book6 = new Book("9780060935467", "LLMs in Production");
        bookRepository.saveAll(List.of(book1, book2, book3, book4, book5, book6));

        LibraryRegisterEntry libraryRegisterEntry1 = new LibraryRegisterEntry(book1, author1, "ST01.01");
        LibraryRegisterEntry libraryRegisterEntry2 = new LibraryRegisterEntry(book2, author2, "ST01.02");
        LibraryRegisterEntry libraryRegisterEntry3 = new LibraryRegisterEntry(book3, author3, "ST01.03");
        LibraryRegisterEntry libraryRegisterEntry4 = new LibraryRegisterEntry(book4, author3, "ST01.04");
        LibraryRegisterEntry libraryRegisterEntry5 = new LibraryRegisterEntry(book5, author4, "ST01.05");
        LibraryRegisterEntry libraryRegisterEntry6_1 = new LibraryRegisterEntry(book6, author5, "ST01.06");
        LibraryRegisterEntry libraryRegisterEntry6_2 = new LibraryRegisterEntry(book6, author6, "ST01.06");

        libraryRegisterEntryRepository.saveAll(List.of(libraryRegisterEntry1, libraryRegisterEntry2, libraryRegisterEntry3, libraryRegisterEntry4, libraryRegisterEntry5, libraryRegisterEntry6_1, libraryRegisterEntry6_2));
    }
}

