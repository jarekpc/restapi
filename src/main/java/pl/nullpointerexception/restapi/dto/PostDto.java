package pl.nullpointerexception.restapi.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author jazy
 */
@Data
@Builder
public class PostDto {

    private long id;
    private String title;
    private String content;
    private LocalDateTime created;
}
