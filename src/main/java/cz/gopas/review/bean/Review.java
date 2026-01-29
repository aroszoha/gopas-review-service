package cz.gopas.review.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Review {

    @Id
    private String id;
    private Long bookId;
    private String author;
    // allowed values: 1 - 10
    private Integer rating;

}
