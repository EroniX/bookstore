package hu.eronix.bookstore.converter;

import hu.eronix.bookstore.exceptions.CodeDictionaryItemNotFoundException;
import hu.eronix.bookstore.model.dto.BookCreationDto;
import hu.eronix.bookstore.model.dto.BookDetailsDto;
import hu.eronix.bookstore.model.entity.Book;
import hu.eronix.bookstore.model.entity.CodeDictionaryItem;
import hu.eronix.bookstore.model.entity.User;
import hu.eronix.bookstore.repository.CodeDictionaryItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BookConverter {

    private final ModelMapper mapper;
    private final CodeDictionaryItemRepository codeDictionaryItemRepository;

    @Autowired
    public BookConverter(ModelMapper mapper, CodeDictionaryItemRepository codeDictionaryItemRepository) {
        this.mapper = mapper;
        this.codeDictionaryItemRepository = codeDictionaryItemRepository;
    }

    public BookDetailsDto toBookDetailsDto(User user, Book book) {
        List<String> writers = book.getWriters()
                .stream()
                .map(writer -> writer.getValue())
                .collect(Collectors.toList());

        BookDetailsDto bookDetailsDto = BookDetailsDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .preface(book.getPreface())
                .content(book.getContent())
                .publisher(book.getPublisher().getValue())
                .category(book.getCategory().getValue())
                .isRented(user.isRented(book))
                .availablePieces(book.getAvailablePieces())
                .writers(writers)
                .build();

        return bookDetailsDto;
    }

    public Book toBook(BookCreationDto bookCreationDto) throws CodeDictionaryItemNotFoundException {
        Book book = mapper.map(bookCreationDto, Book.class);

        Optional<CodeDictionaryItem> categoryOptional =
                codeDictionaryItemRepository.findById(bookCreationDto.getCategory());
        categoryOptional.orElseThrow(CodeDictionaryItemNotFoundException::new);

        Optional<CodeDictionaryItem> publisherOptional =
                codeDictionaryItemRepository.findById(bookCreationDto.getPublisher());
        publisherOptional.orElseThrow(CodeDictionaryItemNotFoundException::new);

        List<Optional<CodeDictionaryItem>> writersOptional =
                bookCreationDto.getWriters().stream()
                        .map(writer -> codeDictionaryItemRepository.findById(writer))
                        .collect(Collectors.toList());
        for (Optional<CodeDictionaryItem> writer : writersOptional) {
            writer.orElseThrow(CodeDictionaryItemNotFoundException::new);
        }

        book.setCategory(categoryOptional.get());
        book.setPublisher(publisherOptional.get());
        book.setWriters(writersOptional.stream().map(writer -> writer.get()).collect(Collectors.toList()));
        book.setId(null);
        return book;
    }
}
