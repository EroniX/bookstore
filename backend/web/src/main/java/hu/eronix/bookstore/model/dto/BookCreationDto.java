package hu.eronix.bookstore.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class BookCreationDto {

    @NotBlank
    private String title;

    @NotBlank
    @Length(max = 200)
    private String preface;

    @NotBlank
    @Length(max = 5000)
    private String content;

    @NotNull
    private Long publisher;

    @NotNull
    private Long category;

    @Size(min = 1)
    private List<Long> writers;

    @Max(10)
    private int maxPieces;

    private int availablePieces;

    private boolean isRented;
}
