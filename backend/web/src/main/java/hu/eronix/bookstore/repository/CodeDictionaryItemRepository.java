package hu.eronix.bookstore.repository;

import hu.eronix.bookstore.model.entity.CodeDictionaryItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeDictionaryItemRepository extends CrudRepository<CodeDictionaryItem, Long> {

    List<CodeDictionaryItem> findAll();
}
