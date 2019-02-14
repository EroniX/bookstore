package hu.eronix.bookstore.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class BookCreationSelectListsDto {

    private List<CodeDictionaryItemDto> publishers = new ArrayList<>();
    private List<CodeDictionaryItemDto> categories = new ArrayList<>();
    private List<CodeDictionaryItemDto> writers = new ArrayList<>();
}
