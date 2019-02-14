package hu.eronix.bookstore;

import hu.eronix.bookstore.model.entity.Book;
import hu.eronix.bookstore.model.entity.CodeDictionaryGroup;
import hu.eronix.bookstore.model.entity.CodeDictionaryItem;
import hu.eronix.bookstore.model.entity.User;
import hu.eronix.bookstore.repository.BookRepository;
import hu.eronix.bookstore.repository.CodeDictionaryGroupRepository;
import hu.eronix.bookstore.repository.CodeDictionaryItemRepository;
import hu.eronix.bookstore.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookStoreApplicationTests {

    @Autowired
    private CodeDictionaryItemRepository codeDictionaryItemRepository;

    @Autowired
    private CodeDictionaryGroupRepository codeDictionaryGroupRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void codeDictionaryRepositoryTest() {
        CodeDictionaryGroup countryGroup = new CodeDictionaryGroup();
        countryGroup.setValue("Countries");

        codeDictionaryGroupRepository.save(countryGroup);

        CodeDictionaryItem hungaryItem = new CodeDictionaryItem();
        hungaryItem.setGroup(countryGroup);
        hungaryItem.setValue("Hungary");

        codeDictionaryItemRepository.save(hungaryItem);

        CodeDictionaryItem ukItem = new CodeDictionaryItem();
        ukItem.setGroup(countryGroup);
        ukItem.setValue("UK");

        codeDictionaryItemRepository.save(ukItem);
    }

    @Test
    public void userRepositoryTest() {
        User user = new User();
        //user.setRole(RoleType.USER);
        user.setEmail("vaczi8@gmail.com");
        user.setUsername("vaczi8");
        user.setPassword("jelszo123");

        userRepository.save(user);
    }

    @Test
    public void bookRepositoryTest() {
        Book book = new Book();

        book.setMaxPieces(10);

        CodeDictionaryGroup group = new CodeDictionaryGroup();
        group.setValue("group");

        codeDictionaryGroupRepository.save(group);

        CodeDictionaryItem item = new CodeDictionaryItem();
        item.setGroup(group);
        item.setValue("item");

        codeDictionaryItemRepository.save(item);

        book.setCategory(item);
        book.setPublisher(item);

        ArrayList<CodeDictionaryItem> writers = new ArrayList<>();
        writers.add(item);
        book.setWriters(writers);

        bookRepository.save(book);
    }
}
