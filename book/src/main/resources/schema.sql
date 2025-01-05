CREATE TABLE if not exists books (
    id BIGSERIAL,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    published_date DATE,
    isbn VARCHAR(13) UNIQUE,
    primary key (id)
);

