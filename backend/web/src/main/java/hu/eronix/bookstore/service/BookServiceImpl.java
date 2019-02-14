package hu.eronix.bookstore.service;

import com.sun.istack.internal.NotNull;
import hu.eronix.bookstore.converter.BookConverter;
import hu.eronix.bookstore.exceptions.BookAlreadyExistsException;
import hu.eronix.bookstore.exceptions.CodeDictionaryGroupNotFoundException;
import hu.eronix.bookstore.exceptions.CodeDictionaryItemNotFoundException;
import hu.eronix.bookstore.model.CodeDictionaryGroupType;
import hu.eronix.bookstore.model.dto.BookCreationDto;
import hu.eronix.bookstore.model.dto.BookCreationSelectListsDto;
import hu.eronix.bookstore.model.dto.BookDetailsDto;
import hu.eronix.bookstore.model.dto.CodeDictionaryItemDto;
import hu.eronix.bookstore.model.entity.Book;
import hu.eronix.bookstore.model.entity.User;
import hu.eronix.bookstore.repository.BookRepository;
import hu.eronix.bookstore.repository.UserRepository;
import hu.eronix.bookstore.util.ListUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    private final CodeDictionaryGroupService codeDictionaryGroupService;

    private final BookConverter bookConverter;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, UserRepository userRepository,
                           CodeDictionaryGroupService codeDictionaryGroupService,
                           BookConverter bookConverter) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.codeDictionaryGroupService = codeDictionaryGroupService;
        this.bookConverter = bookConverter;
    }

    public List<BookDetailsDto> findAll(@NotNull User user) {
        return bookRepository.findAll()
                .stream()
                .map(book -> bookConverter.toBookDetailsDto(user, book))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<BookDetailsDto> findAllAvailable(@NotNull User user) {
        return bookRepository.findAllAvailable()
                .stream()
                .map(book -> bookConverter.toBookDetailsDto(user, book))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BookCreationSelectListsDto createBookCreationSelectListsDto() throws CodeDictionaryGroupNotFoundException {
        List<CodeDictionaryItemDto> writers =
                codeDictionaryGroupService.getCodeDictionaryGroupItems(CodeDictionaryGroupType.Writer);
        List<CodeDictionaryItemDto> categories =
                codeDictionaryGroupService.getCodeDictionaryGroupItems(CodeDictionaryGroupType.Category);
        List<CodeDictionaryItemDto> publishers =
                codeDictionaryGroupService.getCodeDictionaryGroupItems(CodeDictionaryGroupType.Publisher);

        return BookCreationSelectListsDto.builder()
                .categories(categories)
                .writers(writers)
                .publishers(publishers)
                .build();
    }

    @Override
    @Transactional
    public List<BookDetailsDto> findAllRented(@NotNull User user) {
        return user.getRents()
                .stream()
                .map(book -> bookConverter.toBookDetailsDto(user, book))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void create(@NotNull BookCreationDto bookCreationDto)
            throws CodeDictionaryItemNotFoundException, BookAlreadyExistsException {
        if(isBookExists(bookCreationDto.getTitle(), bookCreationDto.getWriters())) {
            throw new BookAlreadyExistsException();
        }
        Book book = bookConverter.toBook(bookCreationDto);
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void delete(long bookId) {
        Book book = getBook(bookId);
        bookRepository.delete(book);
    }

    @Override
    @Transactional
    public synchronized void rent(@NotNull User user, long bookId) {
        Book book = getBook(bookId);
        if (canRent(user, book)) {
            user.addRent(book);
            userRepository.save(user);
        }
    }

    @Override
    @Transactional
    public void deleteRent(@NotNull User user, long bookId) {
        Book book = getBook(bookId);
        user.deleteRent(book);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public boolean canRent(User user, Book book) {
        return !book.isRenter(user) && book.hasAvailablePieces();
    }

    @Override
    @Transactional
    public boolean isBookExists(String title, List<Long> writerIds) {
        for (Book book : bookRepository.findByTitle(title)) {
            List<Long> bookWriterIds = book.getWriters().stream()
                    .map(writer -> writer.getId())
                    .collect(Collectors.toList());

            if (ListUtil.listEqualsIgnoreOrder(bookWriterIds, writerIds)) {
                return true;
            }
        }
        return false;
    }

    private Book getBook(long bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (!bookOptional.isPresent()) {
            logger.warn("Book is null, id=" + bookId);
            throw new IllegalArgumentException("Book cannot be null");
        }
        return bookOptional.get();
    }
}
