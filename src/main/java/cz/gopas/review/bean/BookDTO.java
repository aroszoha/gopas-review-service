package cz.gopas.review.bean;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
/**
 * Class used for REST communication.
 * Not all fields are exposed from persisted entity.
 */
public class BookDTO {

    private String title;
    private String author;

}
