package hu.eronix.bookstore.service;

import hu.eronix.bookstore.exceptions.CodeDictionaryGroupNotFoundException;
import hu.eronix.bookstore.model.CodeDictionaryGroupType;
import hu.eronix.bookstore.model.dto.CodeDictionaryItemDto;

import java.util.List;

public interface CodeDictionaryGroupService {

    List<CodeDictionaryItemDto> getCodeDictionaryGroupItems(CodeDictionaryGroupType codeDictionaryGroupType) throws CodeDictionaryGroupNotFoundException;
}
