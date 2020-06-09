package pl.nullpointerexception.restapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author jazy
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

    private long id;
    private String title;
    private String content;
    private LocalDateTime created;
}
