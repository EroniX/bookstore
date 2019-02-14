package hu.eronix.bookstore.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "code_dictionary_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CodeDictionaryItem extends BaseEntity {

    @Column(name = "value", nullable = false)
    private String value;

    @ManyToOne(targetEntity = CodeDictionaryGroup.class)
    @JoinColumn(name = "code_dictionary_group_id")
    private CodeDictionaryGroup group;
}
