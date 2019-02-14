package hu.eronix.bookstore.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookDetailsDto {

    private long id;

    private String title;

    private String preface;

    private String publisher;

    private String category;

    private String content;

    private List<String> writers;

    private int maxPieces;

    private int availablePieces;

    private boolean isRented;
}
