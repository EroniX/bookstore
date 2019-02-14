package hu.eronix.bookstore.service;

import hu.eronix.bookstore.exceptions.CodeDictionaryGroupNotFoundException;
import hu.eronix.bookstore.exceptions.CodeDictionaryItemNotFoundException;
import hu.eronix.bookstore.model.dto.BookCreationDto;
import hu.eronix.bookstore.model.dto.BookCreationSelectListsDto;
import hu.eronix.bookstore.model.dto.BookDetailsDto;
import hu.eronix.bookstore.model.entity.Book;
import hu.eronix.bookstore.model.entity.User;

import java.util.List;

public interface BookService {
    List<BookDetailsDto> findAllAvailable(User user);

    List<BookDetailsDto> findAllRented(User user);

    BookCreationSelectListsDto createBookCreationSelectListsDto() throws CodeDictionaryGroupNotFoundException;

    void create(BookCreationDto bookCreationDto) throws CodeDictionaryItemNotFoundException;

    void delete(long bookId);

    void rent(User user, long bookId);

    void deleteRent(User user, long bookId);

    boolean canRent(User user, Book book);

    boolean isBookExists(String title, List<Long> writerIds);
}
