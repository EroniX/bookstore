package hu.eronix.bookstore.model;

import lombok.Getter;

public enum CodeDictionaryGroupType {

    Writer(1l),
    Publisher(2l),
    Category(3l);

    @Getter
    private long value;

    CodeDictionaryGroupType(long value) {
        this.value = value;
    }
}