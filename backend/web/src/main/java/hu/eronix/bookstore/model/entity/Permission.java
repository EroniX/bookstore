package hu.eronix.bookstore.model.entity;

import hu.eronix.bookstore.auth.model.AuthPermission;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "permissions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Permission extends BaseEntity implements AuthPermission {

    @Column(name = "value", nullable = false, unique = true)
    private String value;

    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles;
}
