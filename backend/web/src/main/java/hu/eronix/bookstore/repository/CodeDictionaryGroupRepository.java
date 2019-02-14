package hu.eronix.bookstore.repository;

import hu.eronix.bookstore.model.entity.CodeDictionaryGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeDictionaryGroupRepository extends CrudRepository<CodeDictionaryGroup, Long> {
}
