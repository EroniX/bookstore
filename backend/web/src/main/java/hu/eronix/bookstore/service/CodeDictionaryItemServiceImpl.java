package hu.eronix.bookstore.service;

import hu.eronix.bookstore.converter.CodeDictionaryItemConverter;
import hu.eronix.bookstore.exceptions.CodeDictionaryGroupNotFoundException;
import hu.eronix.bookstore.model.CodeDictionaryGroupType;
import hu.eronix.bookstore.model.dto.CodeDictionaryItemDto;
import hu.eronix.bookstore.model.entity.CodeDictionaryGroup;
import hu.eronix.bookstore.model.entity.CodeDictionaryItem;
import hu.eronix.bookstore.repository.CodeDictionaryGroupRepository;
import hu.eronix.bookstore.repository.CodeDictionaryItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CodeDictionaryItemServiceImpl implements CodeDictionaryGroupService {

    private static Logger logger = LoggerFactory.getLogger(CodeDictionaryItemServiceImpl.class);

    private final CodeDictionaryGroupRepository codeDictionaryGroupRepository;
    private final CodeDictionaryItemRepository codeDictionaryItemRepository;
    private final CodeDictionaryItemConverter codeDictionaryItemConverter;

    @Autowired
    public CodeDictionaryItemServiceImpl(CodeDictionaryGroupRepository codeDictionaryGroupRepository,
                                         CodeDictionaryItemRepository codeDictionaryItemRepository,
                                         CodeDictionaryItemConverter codeDictionaryItemConverter) {

        this.codeDictionaryGroupRepository = codeDictionaryGroupRepository;
        this.codeDictionaryItemRepository = codeDictionaryItemRepository;
        this.codeDictionaryItemConverter = codeDictionaryItemConverter;
    }

    @Override
    @Transactional
    public List<CodeDictionaryItemDto> getCodeDictionaryGroupItems(CodeDictionaryGroupType codeDictionaryGroupType)
            throws CodeDictionaryGroupNotFoundException {

        Optional<CodeDictionaryGroup> group =
                codeDictionaryGroupRepository.findById(codeDictionaryGroupType.getValue());
        if (!group.isPresent()) {
            throw new CodeDictionaryGroupNotFoundException();
        }

        List<CodeDictionaryItem> items = group.get().getItems();
        return items.stream()
                .map(item -> codeDictionaryItemConverter.toCodeDictionaryItemDto(item))
                .collect(Collectors.toList());
    }
}
