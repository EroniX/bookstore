package hu.eronix.bookstore.model.entity;

import hu.eronix.bookstore.auth.model.AuthRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity implements AuthRole<Permission> {

    @ManyToMany
    @JoinTable(name = "roles_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<Permission> permissions;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}
