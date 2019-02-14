package hu.eronix.bookstore.converter;

import hu.eronix.bookstore.model.dto.CodeDictionaryItemDto;
import hu.eronix.bookstore.model.entity.CodeDictionaryItem;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CodeDictionaryItemConverter {

    private final ModelMapper mapper;

    @Autowired
    public CodeDictionaryItemConverter(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public CodeDictionaryItem toCodeDictionaryItem(CodeDictionaryItemDto codeDictionaryItemDto) {
        CodeDictionaryItem codeDictionaryItem = mapper.map(codeDictionaryItemDto, CodeDictionaryItem.class);
        return codeDictionaryItem;
    }

    public CodeDictionaryItemDto toCodeDictionaryItemDto(CodeDictionaryItem codeDictionaryItem) {
        CodeDictionaryItemDto codeDictionaryItemDto = mapper.map(codeDictionaryItem, CodeDictionaryItemDto.class);
        return codeDictionaryItemDto;
    }
}
