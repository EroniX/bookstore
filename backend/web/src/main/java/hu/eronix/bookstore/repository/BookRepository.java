package hu.eronix.bookstore.repository;

import hu.eronix.bookstore.model.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @Query("SELECT book FROM Book book WHERE book.maxPieces > size(book.renters)")
    List<Book> findAllAvailable();

    List<Book> findAll();

    List<Book> findByTitle(String title);
}
