package hu.eronix.bookstore.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Book extends BaseEntity {

    @Column
    private String title;

    @Column
    private String preface;

    @Column
    private String content;

    @ManyToOne(targetEntity = CodeDictionaryItem.class)
    @JoinColumn(name = "publisher_id")
    private CodeDictionaryItem publisher;

    @ManyToOne(targetEntity = CodeDictionaryItem.class)
    @JoinColumn(name = "category_id")
    private CodeDictionaryItem category;

    @ManyToMany
    @JoinTable(name = "books_writers",
            joinColumns = @JoinColumn(name = "code_dictionary_group_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<CodeDictionaryItem> writers;

    @ManyToMany(mappedBy = "rents")
    private List<User> renters;

    @Column(name = "max_pieces")
    private int maxPieces;

    public int getAvailablePieces() {
        return maxPieces - writers.size();
    }

    public boolean hasAvailablePieces() {
        return getAvailablePieces() > 0;
    }

    public void addWriter(CodeDictionaryItem codeDictionaryItem) {
        writers.add(codeDictionaryItem);
    }

    public void removeWriter(CodeDictionaryItem codeDictionaryItem) {
        writers.remove(codeDictionaryItem);
    }

    public boolean isWriter(CodeDictionaryItem codeDictionaryItem) {
        return writers.contains(codeDictionaryItem);
    }

    public void addRenter(User user) {
        renters.add(user);
    }

    public void removeRenter(User user) {
        renters.remove(user);
    }

    public boolean isRenter(User user) {
        return renters.contains(user);
    }
}
