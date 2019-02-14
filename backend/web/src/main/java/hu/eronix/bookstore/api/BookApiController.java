package hu.eronix.bookstore.api;

import hu.eronix.bookstore.exceptions.BookAlreadyExistsException;
import hu.eronix.bookstore.exceptions.CodeDictionaryGroupNotFoundException;
import hu.eronix.bookstore.exceptions.CodeDictionaryItemNotFoundException;
import hu.eronix.bookstore.exceptions.NotAuthenticatedException;
import hu.eronix.bookstore.model.dto.BookCreationDto;
import hu.eronix.bookstore.model.dto.BookCreationSelectListsDto;
import hu.eronix.bookstore.model.dto.BookDetailsDto;
import hu.eronix.bookstore.service.BookService;
import hu.eronix.bookstore.service.SecurityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestScope
@RequestMapping("/api/books")
public class BookApiController {

    private final SecurityService securityService;
    private final BookService bookService;

    public BookApiController(SecurityService securityService, BookService bookService) {
        this.securityService = securityService;
        this.bookService = bookService;
    }

    @GetMapping("/findAllAvailable")
    public ResponseEntity<Iterable<BookDetailsDto>> findAllAvailable() throws NotAuthenticatedException {
        Iterable<BookDetailsDto> bookDetailsDtos = bookService.findAllAvailable(securityService.getUser());
        return ResponseEntity.ok(bookDetailsDtos);
    }

    @GetMapping("/findAll")
    public ResponseEntity<Iterable<BookDetailsDto>> findAll() throws NotAuthenticatedException {
        Iterable<BookDetailsDto> bookDetailsDtos = bookService.findAll(securityService.getUser());
        return ResponseEntity.ok(bookDetailsDtos);
    }

    @GetMapping("/findAllRented")
    public ResponseEntity<Iterable<BookDetailsDto>> findAllRented() throws NotAuthenticatedException {
        Iterable<BookDetailsDto> bookDetailsDtos = bookService.findAllRented(securityService.getUser());
        return ResponseEntity.ok(bookDetailsDtos);
    }

    @GetMapping("/createBookCreationSelectLists")
    public ResponseEntity<BookCreationSelectListsDto> createBookCreationSelectLists()
            throws CodeDictionaryGroupNotFoundException {
        BookCreationSelectListsDto bookCreationSelectListsDto = bookService.createBookCreationSelectListsDto();
        return ResponseEntity.ok(bookCreationSelectListsDto);
    }

    @PostMapping("/create")
    public ResponseEntity create(@Valid @RequestBody BookCreationDto bookCreationDto)
            throws CodeDictionaryItemNotFoundException, BookAlreadyExistsException {
        bookService.create(bookCreationDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/rent")
    public ResponseEntity rent(@RequestBody long bookId) throws NotAuthenticatedException {
        bookService.rent(securityService.getUser(), bookId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity deleteRent(@PathVariable long bookId) throws NotAuthenticatedException {
        bookService.deleteRent(securityService.getUser(), bookId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/isBookExists")
    public ResponseEntity<Boolean> isBookExists(@RequestParam("title") String title,
                                                @RequestParam("writerIds") List<Long> writerIds) {
        return ResponseEntity.ok(bookService.isBookExists(title, null));
    }
}
