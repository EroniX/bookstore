package hu.eronix.bookstore.model.entity;

import hu.eronix.bookstore.auth.model.AuthUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity implements AuthUser<Role> {

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(name = "users_rents",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "rent_id"))
    private List<Book> rents;

    @ManyToOne(targetEntity = Role.class)
    @JoinColumn(name = "role_id")
    private Role role;

    public boolean isRented(Book book) {
        return rents.contains(book);
    }

    public void addRent(Book book) {
        rents.add(book);
    }

    public void deleteRent(Book book) {
        rents.remove(book);
    }
}
